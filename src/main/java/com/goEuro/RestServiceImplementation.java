package com.goEuro;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goEuro.DAO.CityInformation;

/**
 * @author RamasamyKasiviswanathan
 *
 */
public class RestServiceImplementation {
	private static final Logger LOGGER = LoggerFactory.getLogger(RestServiceImplementation.class);
	
	/**
	 * Method to make Rest Service call to the constructed URI
	 * @param cityName
	 * @return
	 */
	public List<CityInformation> restServiceCall(final String cityName) {
		CityInformation[] cityInformations = null;

		try {
			RestTemplate restTemplate = new RestTemplate();
			
			restTemplate.getMessageConverters().forEach(converter -> {
				if (converter instanceof MappingJackson2HttpMessageConverter) {
					MappingJackson2HttpMessageConverter jsonConverter = (MappingJackson2HttpMessageConverter) converter;
					jsonConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
				}
			});
			restTemplate.setInterceptors(Arrays.asList(new RestServiceInterceptor()));
			ResponseEntity<CityInformation[]> responseEntity = restTemplate
					.getForEntity("http://api.goeuro.com/api/v2/position/suggest/en/"+cityName, CityInformation[].class);
			
			if (responseEntity.getStatusCode().is2xxSuccessful()) {
				cityInformations = responseEntity.getBody();
			} else {
				LOGGER.error("An Error has occurred while making the call. HttpStatus Code:"
						+ responseEntity.getStatusCode());
			}
			LOGGER.debug("Response is :"
					+ new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(cityInformations));
		} catch (Exception e) {
			LOGGER.error("An Error has occurred in RestServiceCall:", e);
		}
		return Arrays.asList(cityInformations);
	}
}
