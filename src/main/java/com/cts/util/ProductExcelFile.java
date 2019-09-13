package com.cts.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.cts.model.Product;

@Component
public class ProductExcelFile {

	private int rownum;
	private int cellnum;
	File file;

	public ProductExcelFile() {
		cellnum = 0;
		file = new File("./src/main/resources/excel/product.xlsx");
	}

	public String addItemInExcel(String fileName,Product pro) {
		
		try {
			FileInputStream fileInput = new FileInputStream(new File(fileName));
			Workbook workbook = new XSSFWorkbook(fileInput);
			Sheet sheet = workbook.getSheetAt(0);
			rownum = sheet.getLastRowNum();

			cellnum = 0;

			Row row = sheet.createRow(++rownum);

			Cell cell = row.createCell(cellnum++);

			cell.setCellValue(pro.getProdId());

			Cell cell2 = row.createCell(cellnum++);
			cell2.setCellValue(pro.getProdName());

			Cell cell3 = row.createCell(cellnum++);
			cell3.setCellValue(pro.getPrice());

			FileOutputStream out = new FileOutputStream(new File(fileName));
			workbook.write(out);
			out.close();
			return "Product Added Successfully,Product Id:  "+pro.getProdId();

		} catch (IOException e) {
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
}
