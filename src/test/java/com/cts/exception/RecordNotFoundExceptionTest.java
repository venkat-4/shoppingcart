package com.cts.exception;

import static org.junit.Assert.assertNotNull;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RecordNotFoundExceptionTest {

	@Mock
	RecordNotFoundException recordNotFoundException;
	@Rule
	public ExpectedException exception = ExpectedException.none();

	public RecordNotFoundExceptionTest() {

		recordNotFoundException = new RecordNotFoundException("RecordNotFoundException");
		exception.expect(InternalServerError.class);
		exception.expectMessage("recordNotFoundException");
		assertNotNull(recordNotFoundException);
	}

}
