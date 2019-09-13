package com.cts.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cts.model.Order;
import com.cts.util.RWExcelOrder;

@Repository("OrderRepository")
public class OrderRepository {

	@Autowired
	RWExcelOrder rWExcelOrder;
	public Order placeOrder(Order order) {
		Order response = rWExcelOrder.writeExcel(order);
		return response;
		
	}

	public Order cancelOrder(String orderId) {
		rWExcelOrder.cancelOrder(orderId);
		return new Order();
	}

	public List<Order> getAllOrders() {
		return rWExcelOrder.readExcel();
	}

	public Order getOrderById(String orderId) {
		return rWExcelOrder.getOrderById(orderId);
	}
	
	public String save(Order placeOrder) {
		return rWExcelOrder.writeOrderExcel(placeOrder);
	}
}
