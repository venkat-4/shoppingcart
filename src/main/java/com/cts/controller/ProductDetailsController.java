package com.cts.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.model.Order;
import com.cts.model.Product;
import com.cts.service.OrderService;
import com.cts.service.ProductDetailsService;

@RestController
@RequestMapping("/products")
public class ProductDetailsController {

	@Autowired
	private ProductDetailsService productDetailsService;
	
	@Autowired
	private OrderService orderService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping
	public ResponseEntity<String> addItem(@RequestBody Product product) {
		String addItemResponse = productDetailsService.addItem(product);
		if(addItemResponse != null) {
			return new ResponseEntity(addItemResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity(addItemResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@DeleteMapping(path="/{prodId}")
	public ResponseEntity<String> removeItem(@PathVariable("prodId")String prodId){
		String proId = productDetailsService.removeItem(prodId);
		if (proId == null) {
			return new ResponseEntity("Product not found in the system", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity("Product with prodId : " + prodId + " removed successfully", HttpStatus.OK);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productDetailsService.getAllProducts();
		return new ResponseEntity(products, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/{prodId}")
	public ResponseEntity<Product> getProductById(@PathVariable("prodId") String prodId) {
		Product product = productDetailsService.getProductById(prodId);
		if (product == null) {
			return new ResponseEntity("Product not found", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity(product, HttpStatus.OK);
		}
	}
	@RequestMapping(value ="/orders",method = RequestMethod.POST)
	public ResponseEntity<String> save(@Valid @RequestBody Order order) {
		String response= orderService.save(order);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
