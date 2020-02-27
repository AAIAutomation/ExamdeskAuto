package Selenium_with_maven.Automation.helper.excel;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.uiframework.proctur.eLearn.logger.LoggerHelper;
import com.uiframework.proctur.eLearn.logger.resource.ResourceHelper;

public class ExcelHelper {

	private Logger log = LoggerHelper.getLogger(ExcelHelper.class);

	public Object[][] getExcelData(String excelLocation, String sheetName) {

		try {
			Object dataSets[][] = null;
			FileInputStream file = new FileInputStream(new File(excelLocation));
			// create workbook instance
			XSSFWorkbook workbook;
			workbook = new XSSFWorkbook(file);

			// Get sheet name from workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);

			// count number of active rows(row which has data) in excel sheet.
			int totalRow = sheet.getLastRowNum();
			log.info(totalRow);

			// count active column in row

			int totalColumn = sheet.getRow(0).getLastCellNum() + 1;
			log.info(totalColumn);

			dataSets = new Object[totalRow][totalColumn - 1];
//			log.info(dataSets.toString());

			// iterate throw row one by one using iterator.
			Iterator<Row> rowIterator = sheet.iterator();
			int i = 0;
			while (i < totalRow) {
				i++;
				// for every row we need to iterate over column.
				Row row = rowIterator.next();

				Iterator<Cell> cellIterator = row.cellIterator();
				int j = 0;
				while (j+1 < totalColumn) {

					Cell cell = cellIterator.next();
					if (cell.getStringCellValue().contains("start")) {
						i = 0;
						break;
					}
					switch (cell.getCellTypeEnum()) {
					case STRING:
						dataSets[i - 1][j++] = cell.getStringCellValue();
						break;
					case NUMERIC:
						dataSets[i - 1][j++] = cell.getNumericCellValue();
						break;
					case BOOLEAN:
						dataSets[i - 1][j++] = cell.getBooleanCellValue();
						break;

					default:
						System.out.println("no matching enum datatype found");
						break;
					}
				}

			}
			return dataSets;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
//
	public static void main(String[] args) {
		ExcelHelper excelhelper = new ExcelHelper();
		String excelLocation = ResourceHelper.getResourcePath("\\src\\main\\resources\\configfile\\TestData.xlsx");
		Object[][] data = excelhelper.getExcelData(excelLocation, "matchTheFollowing");
		System.out.println(data);

	}
}
