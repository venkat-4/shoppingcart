package com.cts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.model.Order;
import com.cts.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping
	public ResponseEntity<Object> placeOrder(@RequestBody Order order) {
		Order savedOrder = orderService.placeOrder(order);
		if (savedOrder != null) {
			return new ResponseEntity("Order placed successfully. Order id is:"+order.getOrderId(), HttpStatus.OK);
		} else {
			return new ResponseEntity("Unable to place order", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(value = "/cancel/{orderId}")
	public ResponseEntity<Object> cancelOrder(@PathVariable("orderId") String orderId) {
		Order order = orderService.cancelOrder(orderId);
		if (order == null) {
			return new ResponseEntity("Order not found in the system", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity("Order with orderId : " + orderId + " cancelled successfully", HttpStatus.OK);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping
	public ResponseEntity<List<Order>> getAllOrders() {
		List<Order> orders = orderService.getAllOrders();
		return new ResponseEntity(orders, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/{orderId}")
	public ResponseEntity<Order> getOrderById(@PathVariable("orderId") String orderId) {
		Order order = orderService.getOrderById(orderId);
		if (order == null) {
			return new ResponseEntity("Order not found", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity(order, HttpStatus.OK);
		}
	}

}

