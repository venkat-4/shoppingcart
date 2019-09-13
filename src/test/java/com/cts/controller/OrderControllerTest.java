package com.cts.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cts.model.Order;
import com.cts.repository.ProductDetailssRepo;
import com.cts.service.OrderService;
import com.cts.util.RWExcelOrder;

public class OrderControllerTest extends AbstractTest {

	@InjectMocks
	OrderController orderController;

	@Mock
	private OrderService orderService;

	@Mock
	private RWExcelOrder orderExcelFile;

	@Mock
	private ProductDetailssRepo productDetailsRepo;

	@Before
	@Override
	public void setUp() {
		super.setUp();
	}

	@Test
	public void testAddOrder() throws Exception {
		String uri = "/orders";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Order order = (Order) super.mapFromJson(content, Order.class);
		assertTrue(order != null);
	}

	@Test
	public void testCancelOrderSuccess() throws Exception {
		String uri = "/orders/cancel/789";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		assertNotNull(mvcResult.getResponse());
		int status = mvcResult.getResponse().getStatus();
		
		assertEquals(HttpStatus.OK.value(), status);
	}
	
	@Test
	public void testCancelOrderFailure() throws Exception {
		String uri = "/orders/cancel/345";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		assertEquals(HttpStatus.NOT_FOUND.value(), status);
	}

	@Test
	public void testGetAllOrdersSuccess() throws Exception {
		String uri = "/orders";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Order[] orderlist = super.mapFromJson(content, Order[].class);
		assertNotNull(mvcResult.getResponse());
		assertTrue(orderlist.length > 0);
	}
	@Test
	public void testGetAllOrdersFailure() throws Exception {
		String uri = "/orders";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertTrue(content.length() == 2);
	}

	@Test
	public void testGetOrderSuccess() throws Exception {
		String uri = "/orders/123";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(HttpStatus.OK.value(), status);
	}
	@Test
	public void testGetOrderFailure() throws Exception {
		String uri = "/orders/123";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.NOT_FOUND.value(), status);
	}

}
