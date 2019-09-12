package com.cts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.model.Order;
import com.cts.model.Product;
import com.cts.service.ProductDetailsService;

@RestController
@RequestMapping("/products")
public class ProductDetailsController {

	@Autowired
	private ProductDetailsService serv;

	@PostMapping(path = "/")
	public String addItem(@RequestBody Product product) {
		String a = serv.addItem(product);
		return a;
	}
	
	@DeleteMapping(path="/{prodId}")
	public void removeItem(@PathVariable("prodId")int prodId){
		serv.removeItem(prodId);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(path="/")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = serv.getAllProducts();
		return new ResponseEntity(products, HttpStatus.OK);
	}
}
