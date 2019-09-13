package com.cts.controller;

import java.lang.reflect.Array;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cts.model.Order;
import com.cts.model.Product;
import com.cts.model.User;

@RestController
public class BuyProductController {

	private RestTemplate template;

	@RequestMapping(value = "/placeorder", method = RequestMethod.POST)
	public String placeOrder(@RequestBody User user) {

		String userId = "";
		String productId = "";
		template = new RestTemplate();

		String response = template.postForObject("http://localhost:9090/auth/login", user, String.class);

		String[] arr = response.split(":");
		if (arr.length >= 1) {
			userId = (String) Array.get(arr, 1);
		}
		Product product = new Product();
		product.setProdId("102");
		product.setProdName("nokia");
		product.setPrice("999");
		String productResponse = template.postForObject("http://localhost:9090/products", product, String.class);

		String[] productarr = productResponse.split(":");
		if (productarr.length >= 1) {
			productId = (String) Array.get(productarr, 1);
		}

		Product getProduct = template.getForObject("http://localhost:9090/products/" + productId, Product.class);

		Order order = new Order();
		order.setUserID(userId);
		order.setOrderDate("2019-Sep-13");
		order.setOrderId("od-856429");
		order.setProdId(getProduct.getProdId());

		String orderResponse = template.postForObject("http://localhost:9090/products/orders", order, String.class);

		return orderResponse;

	}

}
