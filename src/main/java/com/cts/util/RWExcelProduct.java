package com.cts.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.client.RestTemplate;

import com.cts.model.Product;
import com.cts.model.User;

public class RWExcelProduct {

	private int rownum;
	private int cellnum;
	File file;
	String filePath = "./src/main/resources/excel/product.xlsx";

	public RWExcelProduct() {
		rownum = 0;
		cellnum = 0;
		file = new File("./src/main/resources/excel/login.xlsx");
	}

	public String addItemInExcel(Product pro) {
		File file = new File(filePath);
		XSSFWorkbook workbook = null;
		XSSFSheet sheet = null;
		int rownum = 0;
		int cellnum = 0;
		if (file.exists() == false) {
			workbook = new XSSFWorkbook();
			sheet = workbook.createSheet("product");
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
	public void readExcel(String inputFilePath) {
		RestTemplate restTemplate = new RestTemplate();
		// TechDTO techDto = new TechDTO();
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
					if (currentCell.getCellTypeEnum() == CellType.STRING) {
						System.out.print(currentCell.getStringCellValue() + "--");
						int columnIndex = currentCell.getColumnIndex();
						int rowIndex = currentCell.getRowIndex();
						if (rowIndex > 0) {
							if (columnIndex == 1) {
							}
							if (columnIndex == 2) {
								String input = currentCell.getStringCellValue();
								if (input.endsWith(".json")) {
								}
							}
							if (columnIndex == 3) {
							}
							if (columnIndex == 4) {
							}
						}
					} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
						System.out.print(currentCell.getNumericCellValue() + "--");
					}

				}
				System.out.println();

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String writeExcel(User userDto) {
		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheet = workbook.createSheet("User Details");

		int rownum = 0;

		Row row = sheet.createRow(rownum++);

		Cell cell = row.createCell(cellnum++);

		cell.setCellValue(userDto.getUserId());

		Cell cell2 = row.createCell(cellnum++);
		cell2.setCellValue(userDto.getPassword());

		Cell cell3 = row.createCell(cellnum++);
		cell3.setCellValue(userDto.getFirstName());

		Cell cell4 = row.createCell(cellnum++);
		cell4.setCellValue(userDto.getLastName());
		try {
			FileOutputStream out = new FileOutputStream(file);
			workbook.write(out);
			out.close();
			return "User Registered Successfully";
		} catch (Exception e) {
			e.printStackTrace();
			return "Internal Server Error";
		}
	}

	public List<Product> getAllProducts() {
		FileInputStream fileInputStream = null;
		ArrayList<Product> productList = null;
		try {
			fileInputStream = new FileInputStream(new File(filePath));
			File file = new File(filePath);
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			productList = new ArrayList<>();
			for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
				Product product = new Product();
				Row ro = sheet.getRow(i);
				for (int j = ro.getFirstCellNum(); j <= ro.getLastCellNum(); j++) {
					Cell ce = ro.getCell(j);
					if (j == 0) {
						product.setProdId(ce.getStringCellValue());
					}
					if (j == 1) {
						product.setProdName(ce.getStringCellValue());
					}
					if (j == 2) {
						product.setPrice(ce.getStringCellValue());
					}
				}
				productList.add(product);
			}
			return productList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return productList;
	}

}
