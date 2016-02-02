# goEuroAPI
REST Service API for the goEuro; the JAR can be run directly using Java command "Java -jar GoEuroTest.jar cityName".
The csv file and logs are present in the directory from where the Java command is executed.
Moreover the logs can be seen inside "goEuroAPILogs" folder, each logs can log 30 days of information in it.

In case a failure happens while making the WebService call, an Unique ID would be generated, which helps to keep track of the failure service call.

The technology stack used is:  Spring (for making Rest calls),
                              Jackson (for JSON mapping),
                              Slf4j (for Logging),
                              Apache Commons Lang (for string and Array data validation and manipulation),
                              Apache commons-csv (for saving the data from Java bean objects to CSV file), and
                              Maven (Built Script).
                              UUID (an unquique ID to track request and response, for a particular service call).

Removed all unwanted Jar's which comes as dependency of declared dependent Jar's in maven.
By this way the size of final Jar would be reduced and reduces space complexity.

The Test Jar can be downloaded from https://drive.google.com/file/d/0B5F6crg_0vRlVGlPdWc2dzlPMWc/view?usp=sharing
