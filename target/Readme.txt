1.I used Tomcat as  the Webserver 
2. Java 7 is used to build the applicaion
3. Maven is used as the build tool
4. No Database connection is needed
5. http://api.openweathermap.org/ is used to retrieve the weather details. REST webservice is used
6. http://localhost:8080/weather/webapi/weathers/ will retrive all the listed cities weather details
7. http://localhost:8080/weather/webapi/weathers/$cityname,country (Chennai,in) - will retrive weather details for the requested city
8. http://localhost:8080/weather/webapi/geo.jsp will provide the map with most of the cities and will provide temperature and weather conditions details on click on the partiular city.
9. Conversion of the response object to csv is yet to be implemented.
10. deploy the weather.war under taget in the web server to access the application,
11. The log4j.properties has entry log4j.appender.file.File=F:\\log4j-weather.log. please change it to appropriate directory to write the logs.	