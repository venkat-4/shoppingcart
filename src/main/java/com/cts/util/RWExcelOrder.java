package com.cts.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cts.model.Order;

public class RWExcelOrder {

	String filePath = "./src/main/resources/excel/order.xlsx";

	public List<Order> readExcel() {
		FileInputStream fileInputStream = null;
		ArrayList<Order> orderList = null;
		try {
			fileInputStream = new FileInputStream(new File("./src/main/resources/excel/order.xlsx"));
			File file = new File(filePath);
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			orderList = new ArrayList<>();
			for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
				Order order = new Order();
				Row ro = sheet.getRow(i);
				for (int j = ro.getFirstCellNum(); j <= ro.getLastCellNum(); j++) {
					Cell ce = ro.getCell(j);
					if (j == 0) {
						String tempId = ce.getStringCellValue();
						order.setOrderId(tempId);
					}
					if (j == 1) {
						order.setProdId(ce.getStringCellValue());
					}
					if (j == 2) {
						order.setUserID(ce.getStringCellValue());
					}
					if (j == 3) {
						order.setOrderDate(ce.getStringCellValue());
					}
				}
				orderList.add(order);
			}
			return orderList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return orderList;
	}

	public Order writeExcel(Order order) {
		File file = new File("./src/main/resources/excel/order.xlsx");
		XSSFWorkbook workbook = null;
		XSSFSheet sheet = null;
		int rownum = 0;
		int cellnum = 0;
		if (file.exists() == false) {
			workbook = new XSSFWorkbook();
			sheet = workbook.createSheet("order");
		} else {
			try (InputStream is = new FileInputStream(file)) {
				workbook = new XSSFWorkbook(is);
				sheet = workbook.getSheetAt(0);
				rownum = sheet.getLastRowNum() + 1;
			} catch (FileNotFoundException e) {
				System.err.println("File not found");
			} catch (IOException e) {
				System.err.println("Input/Output exception happened");
			}
		}

		Row row = sheet.createRow(rownum++);

		Cell cell = row.createCell(cellnum++);
		cell.setCellValue(order.getOrderId());

		Cell cell2 = row.createCell(cellnum++);
		cell2.setCellValue(order.getOrderDate());

		Cell cell3 = row.createCell(cellnum++);
		cell3.setCellValue(order.getUserID());

		Cell cell4 = row.createCell(cellnum++);
		cell4.setCellValue(order.getProdId());

		Cell cell5 = row.createCell(cellnum++);
		cell5.setCellValue(order.getProdId());
		try {
			file = new File("./src/main/resources/excel/order.xlsx");
			FileOutputStream out = new FileOutputStream(file);
			workbook.write(out);
			out.close();
			return order;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void cancelOrder(String orderId) {
		int removeRowIndex = 0;
		try {
			FileInputStream excelFile = new FileInputStream(new File("./src/main/resources/excel/order.xlsx"));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = sheet.iterator();
			while (iterator.hasNext()) {
				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				while (cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();
					int columnIndex = currentCell.getColumnIndex();
					int rowIndex = currentCell.getRowIndex();
					if (rowIndex >= 0) {
						if (columnIndex == 0 && currentCell.getStringCellValue().equalsIgnoreCase(orderId)) {
							removeRowIndex = rowIndex;
						}
					}
				}
			}
			removeOrder(sheet, removeRowIndex);
			File outFile = new File(filePath);
			OutputStream out = new FileOutputStream(outFile);
			workbook.write(out);
			out.flush();
			out.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void removeOrder(Sheet sheet, int rowIndex) {
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
