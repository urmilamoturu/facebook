package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Urmila
 * 
 * This class opens the excel sheet based on the location provided and reads the cells in it
 * and stores in a two dimensional string array
 *
 */
public class ExcelDataProvider {
	/**
	 * @param sheetName name of the sheet in the excel
	 * @return two dimensional string array with data from the excel sheet
	 */
	public static String[][] getExcelData(String sheetName){
		String[][] data;
		XSSFWorkbook wb = null;
		try {
			FileInputStream fs = new FileInputStream(new File("./data/"+sheetName+".xlsx"));
			wb = new XSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Get the full Data table [row and column]
		XSSFSheet sheet = wb.getSheetAt(0);
		int rowCount = sheet.getLastRowNum();
		short columnCount = sheet.getRow(0).getLastCellNum();
		data = new String[rowCount][columnCount];
		String cellValue;
		//Loop through each row, get each column value
		for (int i=1; i<rowCount+1; i++) {
			XSSFRow row = sheet.getRow(i);
			for (int j=0; j<columnCount; j++) {
				cellValue = row.getCell(j).getStringCellValue();
				data[i-1][j] = cellValue;
			}
		}
		return data; 
	}
}
