package parseExcel.com.edu.another;import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class TestParseExcel {

	public static void main(String[] args) {
		ParseExcel excel=new ParseExcel();
		String path="D:\\study\\mycode\\codings\\group22\\student.xlsx";
		InputStream is;
		try {
			is = new FileInputStream(new File(path));
			Map<Object, Object> map=excel.readExcelContent(is);
			System.out.println(map);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
