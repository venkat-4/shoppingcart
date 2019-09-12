package com.cts.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cts.model.Order;
import com.cts.util.RWExcelOrder;

@Repository("OrderRepository")
public class OrderRepository {

	public Order placeOrder(Order order) {
		RWExcelOrder rwExcelOrder = new RWExcelOrder();
		Order response = rwExcelOrder.writeExcel(order);
		return response;
		
	}

	public Order cancelOrder(String orderId) {
		RWExcelOrder rwExcelOrder = new RWExcelOrder();
		rwExcelOrder.cancelOrder(orderId);
		return new Order();
	}

	public List<Order> getAllOrders() {
		RWExcelOrder rwExcelOrder = new RWExcelOrder();
		return rwExcelOrder.readExcel();
	}
}
