package com.cts.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Repository;

import com.cts.model.Product;
import com.cts.util.RWExcelProduct;

@Repository("productDetailssRepo")
public class ProductDetailssRepo {

	String filePath = "./src/main/resources/excel/order.xlsx";

	public String addItemInExcel(Product pro) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("product");
		int rownum = 0;
		int cellnum = 0;
		Row row = sheet.createRow(rownum++);
		Cell cell = row.createCell(cellnum++);
		cell.setCellValue(pro.getProdId());
		Cell cell2 = row.createCell(cellnum++);
		cell2.setCellValue(pro.getProdName());
		Cell cell3 = row.createCell(cellnum++);
		cell3.setCellValue(pro.getPrice());
		try {
			FileOutputStream out = new FileOutputStream(filePath);
			workbook.write(out);
			out.close();
			return "Item added Successfully";
		} catch (Exception e) {
			e.printStackTrace();
			return "Internal Server Error";
		}
	}

	public void removeItemFromExcel(String inputFilePath, int id) {

		int removeRowIndex = 0;
		try {

			FileInputStream excelFile = new FileInputStream(new File(inputFilePath));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();

			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();

				while (cellIterator.hasNext()) {

					Cell currentCell = cellIterator.next();
					if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {

						int columnIndex = currentCell.getColumnIndex();
						int rowIndex = currentCell.getRowIndex();
						if (rowIndex >= 0) {
							if (columnIndex == 0 && currentCell.getNumericCellValue() == id) {
								removeRowIndex = rowIndex;
							}
						}
					}
				}
				System.out.println();

			}
			removeRow(datatypeSheet, removeRowIndex);
			File outWB = new File(inputFilePath);
			OutputStream out = new FileOutputStream(outWB);
			workbook.write(out);
			out.flush();
			out.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void removeRow(Sheet sheet, int rowIndex) {
		int lastRowNum = sheet.getLastRowNum();
		if (rowIndex >= 0 && rowIndex < lastRowNum) {
			sheet.shiftRows(rowIndex + 1, lastRowNum, -1);
		}
		if (rowIndex == lastRowNum) {
			Row removingRow = sheet.getRow(rowIndex);
			if (removingRow != null) {
				sheet.removeRow(removingRow);
			}
		}
	}

	public List<Product> getAllProducts() {
		RWExcelProduct rwExcelProduct = new RWExcelProduct();
		return rwExcelProduct.getAllProducts();
	}

	
}
