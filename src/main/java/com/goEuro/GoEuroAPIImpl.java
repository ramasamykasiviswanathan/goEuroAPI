package com.goEuro;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.goEuro.DAO.CityInformation;

/**
 * @author RamasamyKasiviswanathan
 *
 */
public class GoEuroAPIImpl {
	
	private static final String currentDirectory = System.getProperty("user.dir");
	
	static {
		System.setProperty("LogFileLocation", currentDirectory);
	}
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GoEuroAPIImpl.class);

	/**
	 * Java main method to the goEuro Rest API. Takes CityName as argument.
	 * @param args
	 * @throws IllegalAccessException
	 */
	public static void main(String[] args) throws IllegalAccessException {
		
		LOGGER.debug("Main Method.");
		
		if(ArrayUtils.isNotEmpty(args)){
			System.out.println(System.getProperty("user.dir"));
			String cityName = StringUtils.trimToEmpty(args[0]);
			List<CityInformation> cityInformations = new RestServiceImplementation().restServiceCall(cityName);
			if(cityInformations != null && !cityInformations.isEmpty())
			{
				String fileLocation = currentDirectory+"/goEuro.csv";
				new JavaBeansToCSV().beanToCSVWriter(cityInformations , fileLocation);
			}
			else
			{
				LOGGER.error("City Information is either empty or null");
			}
		} else {
			LOGGER.error("Input argument is either null or empty.");
		}
		 
	}
}
