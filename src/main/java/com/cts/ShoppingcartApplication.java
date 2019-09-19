package com.cts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * Class that can be used to bootstrap and launch a Spring application from a
 * Java main method. By default class will perform the following steps to
 * bootstrap your application:
 * 
 * @author 788599
 *
 */
@EnableSwagger2
@SpringBootApplication
public class ShoppingcartApplication {

	/**
	 * This is the starting of the shopping cart application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ShoppingcartApplication.class, args);
	}

}
