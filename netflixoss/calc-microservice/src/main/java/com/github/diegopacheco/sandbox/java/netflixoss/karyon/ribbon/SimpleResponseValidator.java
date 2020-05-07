package com.github.diegopacheco.sandbox.java.netflixoss.karyon.ribbon;


import com.github.diegopacheco.sandbox.java.netflixoss.karyon.rest.CalcResource;
import io.reactivex.netty.protocol.http.client.HttpClientResponse;

import com.netflix.ribbon.ResponseValidator;
import com.netflix.ribbon.ServerError;
import com.netflix.ribbon.UnsuccessfulResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SuppressWarnings("rawtypes")
public class SimpleResponseValidator implements ResponseValidator<HttpClientResponse> {

	private static final Logger logger = LoggerFactory.getLogger(CalcResource.class);

	@Override
	public void validate(HttpClientResponse response) throws UnsuccessfulResponseException,ServerError {
		logger.info("Doing validations");
		logger.info("Validated");
	}
}