package com.cts.exception;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RunWith(SpringRunner.class)
public class CustomExceptionHandlerTest extends ResponseEntityExceptionHandler {

	@Mock
	public CustomExceptionHandler customExceptionHandler;

	ResponseEntity<Object> mockResponse;

	@Before
	public void setUp() {
		mockResponse = null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void handleAllExceptionTest() {
		mockResponse = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		when(customExceptionHandler.handleAllException(Mockito.any(Exception.class), Mockito.any()))
				.thenReturn(mockResponse);

		ResponseEntity<Object> ErrorResponse = customExceptionHandler.handleAllException(new Exception("sadad"), null);
		assertThat(ErrorResponse, notNullValue());
		assertThat(ErrorResponse.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void handleInternalServerExceptionTest() {
		mockResponse = new ResponseEntity(HttpStatus.NOT_FOUND);
		when(customExceptionHandler.handleInternalServerException(Mockito.any(InternalServerError.class),
				Mockito.any())).thenReturn(mockResponse);

		ResponseEntity<Object> ErrorResponse = customExceptionHandler
				.handleInternalServerException(new InternalServerError("sadad"), null);
		assertThat(ErrorResponse, notNullValue());
		assertThat(ErrorResponse.getStatusCode(), is(HttpStatus.NOT_FOUND));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void handleRecordNotFoundExceptionTest() {
		mockResponse = new ResponseEntity(HttpStatus.NOT_FOUND);
		when(customExceptionHandler.handleRecordNotFoundException(Mockito.any(RecordNotFoundException.class),
				Mockito.any())).thenReturn(mockResponse);

		ResponseEntity<Object> ErrorResponse = customExceptionHandler
				.handleRecordNotFoundException(new RecordNotFoundException(""), null);
		assertThat(ErrorResponse, notNullValue());
		assertThat(ErrorResponse.getStatusCode(), is(HttpStatus.NOT_FOUND));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void methodArgumentNotValid() {
		mockResponse = new ResponseEntity(HttpStatus.BAD_REQUEST);
		Mockito.doReturn(mockResponse).when(customExceptionHandler).handleMethodArgumentNotValid(Mockito.any(),
				Mockito.any(HttpHeaders.class), Mockito.any(HttpStatus.class), Mockito.any());

		ResponseEntity<Object> ErrorResponse = customExceptionHandler.handleMethodArgumentNotValid(null,
				HttpHeaders.EMPTY, HttpStatus.BAD_REQUEST, null);
		assertThat(ErrorResponse, notNullValue());
		assertThat(ErrorResponse.getStatusCode(), is(HttpStatus.BAD_REQUEST));
	}
}
