package com.cts.exception;

import static org.junit.Assert.assertNotNull;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RecordNotFoundExceptionTest {

	@Mock
	RecordNotFoundException recordNotFoundException;

	public RecordNotFoundExceptionTest() {

		recordNotFoundException = new RecordNotFoundException("RecordNotFoundException");
		assertNotNull(recordNotFoundException);
	}

}
