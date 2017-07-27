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

//	private POIFSFileSystem fileSystem;//文档对象
	private XSSFWorkbook workbook;//工作簿
	private XSSFSheet sheet;//工作表
	private XSSFRow row;//工作表中的行对象
	
	
	/**
	 * 获取Excel表格第一行标题
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
		//从工作簿中获取工作表（sheet1)
		sheet=workbook.getSheetAt(0);
		//获取第一行
		row=sheet.getRow(0);
		//标题总列数
		int colNum=row.getPhysicalNumberOfCells();
		System.out.println("colNum"+colNum);
		String[] titles=new String[colNum];
		for(int i=0;i<colNum;i++){
			titles[i]=getCellFormatValue(row.getCell(i));
		}
		return titles;
	}

	/**
	 * 读取excel内容
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
		//总行数
		int rowNum=sheet.getPhysicalNumberOfRows();
		//总列数
		row=sheet.getRow(0);
		int colNum=row.getPhysicalNumberOfCells();
		for(int i=0;i<rowNum;i++){
			contents=new HashMap<>();
			row=sheet.getRow(i);
			int j=0;
			while(j<colNum){
				if(isValid(row.getCell(j))){
					contents.put("message", "第"+i+"行"+"第"+j+"列格式错误！请重新上传");
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
	 * 根据类型判断
	 * @param cell
	 * @return
	 */
	private String getCellFormatValue(XSSFCell cell) {
		String cellValue="";
		if(cell !=null){
			//判断当前cell的Type
			switch (cell.getCellType()){
			case XSSFCell.CELL_TYPE_NUMERIC:
			case XSSFCell.CELL_TYPE_FORMULA:{//公式计算
				//Date
				if(HSSFDateUtil.isCellDateFormatted(cell)){
					//带时分秒
					cellValue=cell.getDateCellValue().toString();
					//不带时分秒
//					Date date=cell.getDateCellValue();
//					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//					cellValue=sdf.format(date);
				}else{//纯数字
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
