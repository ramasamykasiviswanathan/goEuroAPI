# goEuroAPI
REST Service API for the goEuro; the JAR can be run directly using Java command "Java -jar GoEuroTest.jar cityName".
The csv file and logs are present in the directory from where the Java command is executed.
Morover the logs can be seen inside "goEuroAPILogs" folder, each logs can log 30 days of information in it.

The technology stack used is:  Spring (for making Rest calls),
                              Jackson (for JSON mapping),
                              Slf4j (for Logging),
                              Apache Commons Lang (for string and Array data validation and manipulation),
                              Apache commons-csv (for saving the data from Java bean objects to CSV file), and
                              Maven (Built Script).
                              UUID (an unquique ID to track request and response, for a particular service call).
