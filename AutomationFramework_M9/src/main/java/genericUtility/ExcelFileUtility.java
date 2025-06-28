package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class consists of methods related to read data from Excel file
 */
public class ExcelFileUtility {
	/**
	 * This method is used to read data from Excel file provided with sheet, row,
	 * cell
	 * 
	 * @param sheet
	 * @param row
	 * @param cell
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String excelFileData(String sheet, int row, int cell) throws EncryptedDocumentException, IOException {
		FileInputStream file = new FileInputStream(".\\src\\test\\resources\\testDataM9.xlsx");
		Workbook wb = WorkbookFactory.create(file);
		String value = wb.getSheet(sheet).getRow(row).getCell(cell).toString();
		return value;
	}
}
