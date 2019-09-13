package com.cts.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cts.model.Product;
import com.cts.util.ProductExcelFile;
import com.cts.util.RWExcelProduct;

@Repository("productDetailssRepo")
public class ProductDetailssRepo {

	//String filePath = "./src/main/resources/excel/product.xlsx";

	@Autowired
	private ProductExcelFile productExcelFile;
	
	@Autowired
	private RWExcelProduct rwExcelProduct;
	
	public String addItem(Product pro) {
		return  productExcelFile.addItemInExcel("./src/main/resources/excel/product.xlsx",pro);
	}

	public void removeItem(int id) {
		productExcelFile.removeItemFromExcel("./src/main/resources/excel/product.xlsx", id);
	}

	public List<Product> getAllProducts() {
		return rwExcelProduct.getAllProducts();
	}

	public Product getProductById(String productId) {
		return rwExcelProduct.getProductById(productId);
	}

	
}
