/**
 * This class is used to give the order details.
 * 
 * @author 764432
 *
 */
package com.cts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.model.Order;
import com.cts.repository.OrderRepository;

@Service("orderService")
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	/**
	 * It is used to place the order.
	 * 
	 * @param order
	 * @return
	 */
	public Order placeOrder(Order order) {
		return orderRepository.placeOrder(order);

	}

	/**
	 * It is used to cancel the order.
	 * 
	 * @param orderId
	 * @return
	 */
	public String cancelOrder(String orderId) {
		return orderRepository.cancelOrder(orderId);
	}

	/**
	 * It is used to getting the list of orders.
	 * 
	 * @return
	 */
	public List<Order> getAllOrders() {
		return orderRepository.getAllOrders();
	}

	/**
	 * It is used to get the product detail.
	 * 
	 * @param orderId
	 * @return
	 */
	public Order getOrderById(String orderId) {
		return orderRepository.getOrderById(orderId);
	}

	/**
	 * It is used to save the order details into excel.
	 * 
	 * @param placeOrder
	 * @return
	 */
	public String save(Order placeOrder) {
		return orderRepository.save(placeOrder);
	}

}
