package com.cts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.model.Product;
import com.cts.repository.ProductDetailssRepo;

@Service("productDetailsService")
public class ProductDetailsService {
	
	@Autowired
	ProductDetailssRepo repo;

	public String addItem(Product pro) {
		String a = repo.addItemInExcel(pro);
		return a;
	}
	public void removeItem(int id){
		repo.removeItemFromExcel("./src/main/resources/excel/product.xlsx", id);
	}
	public List<Product> getAllProducts() {
		return repo.getAllProducts();
	}
}
