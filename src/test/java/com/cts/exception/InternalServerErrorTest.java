package com.cts.exception;

import static org.junit.Assert.assertNotNull;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class InternalServerErrorTest {

	@Mock
	InternalServerError internalServer;

	public InternalServerErrorTest() {
		internalServer = new InternalServerError("INTERNAL_SERVER_ERROR");

		assertNotNull(internalServer);

	}
}
