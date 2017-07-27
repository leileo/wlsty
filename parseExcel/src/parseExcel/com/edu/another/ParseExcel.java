package parseExcel.com.edu.another;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ParseExcel {

//	private POIFSFileSystem fileSystem;//�ĵ�����
	private XSSFWorkbook workbook;//������
	private XSSFSheet sheet;//������
	private XSSFRow row;//�������е��ж���
	
	
	/**
	 * ��ȡExcel����һ�б���
	 * @param is
	 * @return
	 */
	public String[] readExcelTile(InputStream is){
		try {
//			fileSystem=new POIFSFileSystem(is);
			workbook=new XSSFWorkbook(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//�ӹ������л�ȡ������sheet1)
		sheet=workbook.getSheetAt(0);
		//��ȡ��һ��
		row=sheet.getRow(0);
		//����������
		int colNum=row.getPhysicalNumberOfCells();
		System.out.println("colNum"+colNum);
		String[] titles=new String[colNum];
		for(int i=0;i<colNum;i++){
			titles[i]=getCellFormatValue(row.getCell(i));
		}
		return titles;
	}

	/**
	 * ��ȡexcel����
	 * @param is
	 * @return
	 */
	public Map<Object, Object> readExcelContent(InputStream is){
		Map<Object, Object> contents=null;
		String temp="";
		try {
			workbook=new XSSFWorkbook(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet=workbook.getSheetAt(0);
		//������
		int rowNum=sheet.getPhysicalNumberOfRows();
		//������
		row=sheet.getRow(0);
		int colNum=row.getPhysicalNumberOfCells();
		for(int i=0;i<rowNum;i++){
			contents=new HashMap<>();
			row=sheet.getRow(i);
			int j=0;
			while(j<colNum){
				if(isValid(row.getCell(j))){
					contents.put("message", "��"+i+"��"+"��"+j+"�и�ʽ�����������ϴ�");
					break;
				}
				temp=getCellFormatValue(row.getCell(j));
				contents.put(i+""+j, temp);
				j++;
			}
			insertIntoDB(contents,i,colNum);
		}
		return contents;
	}
	
	

	private void insertIntoDB(Map<Object, Object> contents, int i, int colNum) {
			for(int j=0;j<colNum;j++){
				System.out.print(contents.get(i+""+j)+"  ");
		}
		System.out.println();
	}


	private boolean isValid(XSSFCell cell) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * ���������ж�
	 * @param cell
	 * @return
	 */
	private String getCellFormatValue(XSSFCell cell) {
		String cellValue="";
		if(cell !=null){
			//�жϵ�ǰcell��Type
			switch (cell.getCellType()){
			case XSSFCell.CELL_TYPE_NUMERIC:
			case XSSFCell.CELL_TYPE_FORMULA:{//��ʽ����
				//Date
				if(HSSFDateUtil.isCellDateFormatted(cell)){
					//��ʱ����
					cellValue=cell.getDateCellValue().toString();
					//����ʱ����
//					Date date=cell.getDateCellValue();
//					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//					cellValue=sdf.format(date);
				}else{//������
					cellValue=String.valueOf(cell.getNumericCellValue());
				}
				break;
			}
			case XSSFCell.CELL_TYPE_STRING:
				cellValue=cell.getStringCellValue();
				break;
				
			default:
				cellValue="";
			}
		}else{
			cellValue="";
		}
		return cellValue;
	}
	
}
