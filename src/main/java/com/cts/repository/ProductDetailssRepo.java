package com.cts.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cts.model.Product;
import com.cts.util.ProductExcelFile;
import com.cts.util.RWExcelProduct;

@Repository("productDetailssRepo")
public class ProductDetailssRepo {

	//String filePath = "./src/main/resources/excel/product.xlsx";

	public String addItem(Product pro) {
		ProductExcelFile productExcelFile = new ProductExcelFile();
		String response = productExcelFile.addItemInExcel(pro);
		return response;
	}

	public void removeItem(int id) {
		ProductExcelFile productExcelFile = new ProductExcelFile();
		productExcelFile.removeItemFromExcel("./src/main/resources/excel/product.xlsx", id);
	}

	public List<Product> getAllProducts() {
		RWExcelProduct rwExcelProduct = new RWExcelProduct();
		return rwExcelProduct.getAllProducts();
	}

	public Product getProductById(String productId) {
		RWExcelProduct rwExcelProduct = new RWExcelProduct();
		return rwExcelProduct.getProductById(productId);
	}

	
}
