package com.cts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.exception.InternalServerError;
import com.cts.exception.RecordNotFoundException;
import com.cts.model.Order;
import com.cts.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PutMapping(value = "/")
	public ResponseEntity<Object> placeOrder(@RequestBody Order order) {
		Order savedOrder = orderService.placeOrder(order);
		if(savedOrder != null) {
			return new ResponseEntity("Order placed successfully", HttpStatus.OK);
		} else {
			throw new InternalServerError("Internal Server error occurred");
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(value="/cancel/{orderId}")
	public ResponseEntity<Object> cancelOrder(@PathVariable("orderId") String orderId) {
		Order order = orderService.cancelOrder(orderId);
		if(order == null) {
			throw new RecordNotFoundException("Order not founf, orderId-"+orderId);
		} else {
			return new ResponseEntity("Order with orderId "+order.getOrderId()+" cancelled", HttpStatus.OK);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(value = "/")
	public ResponseEntity<List<Order>> getAllOrders() {
		List<Order> orders = null;
		try {
			orders = orderService.getAllOrders();
		} catch (Exception e) {
			throw new RecordNotFoundException("No record found");
		}
		if(orders == null || orders.isEmpty()) {
			throw new RecordNotFoundException("No record found");
		}
		return new ResponseEntity(orders, HttpStatus.OK);
	}

}
