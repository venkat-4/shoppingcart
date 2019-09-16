package com.cts.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import com.cts.controller.AbstractTest;
import com.cts.model.Order;
import com.cts.util.RWExcelOrder;

public class OrderRepositoryTest extends AbstractTest{

@InjectMocks
OrderRepository orderRepository;
@Mock
RWExcelOrder rWExcelOrder;
	@Test
	public void testPlaceOrder() {
		Order order=new Order();
		order.setOrderId("1");
		order.setProdId("1");
		order.setOrderDate("1");
		order.setUserID("1");
		when(rWExcelOrder.writeExcel(order)).thenReturn(order);
		orderRepository.placeOrder(order);
	}

	@Test
	public void testCancelOrder(){
		Order order=new Order();
		order.setOrderId("1");
		when(rWExcelOrder.cancelOrder(order.getOrderId())).thenReturn("cancelled");
		String res = orderRepository.cancelOrder(order.getOrderId());
		assertEquals(res, "cancelled");
	}
	
	
	
	
	
}
