package com.goEuro;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.goEuro.DAO.CityInformation;

/**
 * @author RamasamyKasiviswanathan
 *
 */
public class JavaBeansToCSV {
	private static final Logger LOGGER = LoggerFactory.getLogger(JavaBeansToCSV.class);
	
	/**
	 * Method to Write the bean information to CSV file.
	 * @param informations
	 */
	public void beanToCSVWriter(final List<CityInformation> informations, final String fileLocation){
		LOGGER.debug("BeanToCSVWriter method");
		String[] headers = new String[]{"_id","name","type","latitude","longitude"};
		try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(fileLocation), CSVFormat.EXCEL.withHeader(headers).withRecordSeparator("\n"))) {
			informations.stream().forEach(information -> {
					try {
						csvPrinter.printRecord(populateListValues(information));
					} catch (Exception e) {
						LOGGER.error("an error has occurred in beanToCSVWriter method:",e);
					}
			});
			LOGGER.debug("The path where CSV file generated is:{}",fileLocation);
		} catch (Exception e) {
			LOGGER.error("an error has occurred in beanToCSVWriter method:",e);
		}
	}
	
	private List<Object> populateListValues(final CityInformation information){
		List<Object> values = new ArrayList<>(3);
		values.add(information.get_id());
		values.add(stringSanitizing(information.getFullName()));
		values.add(stringSanitizing(information.getType()));
		if(information.getGeo_position() != null){
			values.add(information.getGeo_position().getLatitude());
			values.add(information.getGeo_position().getLongitude());
		}
		return values;
	}
	
	private String stringSanitizing(final Object value)
	{
		return StringUtils.trimToEmpty(String.valueOf(value));
	}
}
