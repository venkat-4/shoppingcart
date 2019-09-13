package com.cts;

import java.util.List;

import com.cts.model.Order;
import com.cts.model.Product;
import com.cts.util.RWExcelOrder;
import com.cts.util.RWExcelProduct;

public class TestMain {
	public static void main(String[] args) {
		RWExcelOrder rwExcelOrder = new RWExcelOrder();

		Order order = new Order();
		order.setOrderId("111");
		order.setOrderDate("2018-10-15");
		order.setUserID("333");
		order.setProdId("ABC333");
		order.setProdId("Active");

		Order order1 = new Order();
		order1.setOrderId("222");
		order1.setOrderDate("2018-10-15");
		order1.setUserID("333");
		order1.setProdId("ABC333");
		order1.setProdId("Active");

		Order order2 = new Order();
		order2.setOrderId("333");
		order2.setOrderDate("2018-10-15");
		order2.setUserID("333");
		order2.setProdId("ABC333");
		order2.setProdId("Active");

		Order order3 = new Order();
		order3.setOrderId("444");
		order3.setOrderDate("2018-10-15");
		order3.setUserID("333");
		order3.setProdId("ABC333");
		order3.setProdId("Active");

		/*
		 * rwExcelOrder.writeExcel(order); rwExcelOrder.writeExcel(order1);
		 * rwExcelOrder.writeExcel(order2); rwExcelOrder.writeExcel(order3);
		 */

		/*
		 * List<Order> orders = rwExcelOrder.readExcel(); orders.forEach(o ->
		 * System.out.println(o.toString()));
		 */
		Product p1 = new Product();
		p1.setProdId("123");
		p1.setProdName("Mobile");
		p1.setPrice("85.25");

		Product p2 = new Product();
		p2.setProdId("234");
		p2.setProdName("Data card");
		p2.setPrice("15.25");

		Product p3 = new Product();
		p3.setProdId("345");
		p3.setProdName("USB");
		p3.setPrice("35.25");

		Product p4 = new Product();
		p4.setProdId("456");
		p4.setProdName("Power Cable");
		p4.setPrice("100.50");

		Product p5 = new Product();
		p5.setProdId("567");
		p5.setProdName("Monotor");
		p5.setPrice("1000.00");

		RWExcelProduct rwExcelProduct = new RWExcelProduct();
		/*
		 * rwExcelProduct.addItemInExcel(p1); rwExcelProduct.addItemInExcel(p2);
		 * rwExcelProduct.addItemInExcel(p3); rwExcelProduct.addItemInExcel(p4);
		 * rwExcelProduct.addItemInExcel(p5);
		 */

		List<Product> products = rwExcelProduct.getAllProducts();
		products.forEach(p -> System.out.println(p.toString()));

//		 Product product = rwExcelProduct.getProductById("123")
		System.out.println("DONE");
	}

}
