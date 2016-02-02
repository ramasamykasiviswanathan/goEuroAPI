package com.goEuro;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * @author RamasamyKasiviswanathan
 *
 */
public class RestServiceInterceptor implements ClientHttpRequestInterceptor {
	private static final Logger LOGGER = LoggerFactory.getLogger(RestServiceInterceptor.class);

	/* Method to intercept Request and response call.
	 * @see org.springframework.http.client.ClientHttpRequestInterceptor#intercept(org.springframework.http.HttpRequest, byte[], org.springframework.http.client.ClientHttpRequestExecution)
	 */
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		logRequest(request);
		LOGGER.debug("Body of the request message is: {}", body);
		LOGGER.info("Unique ID for this service call is: {}",UUID.randomUUID());
		long starTime = new Date().getTime();
		ClientHttpResponse response = execution.execute(request, body);
		LOGGER.debug("Total time taken for the service call is: {}ms", (new Date().getTime() - starTime));
		logResponse(response);
		return response;
	}

	/**
	 * Method to Log Request Information
	 * @param httpRequest
	 */
	private void logRequest(final HttpRequest httpRequest) {
		LOGGER.debug("Request Information");
		LOGGER.info("URI is: {}", httpRequest.getURI());
		LOGGER.info("Method is: {}", httpRequest.getMethod());
		LOGGER.info("Header is: {}", httpRequest.getHeaders());
	}

	/**
	 * Method to Log Response Information
	 * @param httpResponse
	 */
	private void logResponse(final ClientHttpResponse httpResponse) {
		LOGGER.debug("Response Information");
		try {
			LOGGER.debug("HTTP Status Code is: {}", httpResponse.getRawStatusCode());
			LOGGER.debug("HTTP Status Text is: {}", httpResponse.getStatusText());
			LOGGER.debug("Header is: {}", httpResponse.getHeaders());
			LOGGER.debug("Body is: {}", httpResponse.getBody());
		} catch (IOException e) {
			LOGGER.error("Exception Occured while logginResponse:", e);
		}
	}
}
