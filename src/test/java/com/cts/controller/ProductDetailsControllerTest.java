package com.cts.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cts.model.Product;
import com.cts.repository.ProductDetailssRepo;
import com.cts.service.ProductDetailsService;
import com.cts.util.ProductExcelFile;

public class ProductDetailsControllerTest extends AbstractTest {

	@InjectMocks
	ProductDetailsController productDetailsController;

	@Mock
	private ProductDetailsService productDetailsService;

	@Mock
	private ProductExcelFile productExcelFile;

	@Mock
	private ProductDetailssRepo productDetailssRepo;

	@Test
	public void testAddItem() {
		File file;
		file = new File("./src/main/resources/excel/product.xlsx");
		Product product = new Product();
		product.setProdId("1");
		product.setProdName("TV");
		product.setPrice("1000");
		String Response = "Item added Successfully";
		when(productDetailsService.addItem(product)).thenReturn(Response);
		productDetailsController.addItem(product);
	}

	@Test
	public void getProductByIdSuccess() throws Exception {
		String uri = "/products/123";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.OK.value(), status);
	}

	@Test
	public void testRemoveItemFailure() throws Exception {
		String uri = "/products/123";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.NOT_FOUND.value(), status);
	}

}
