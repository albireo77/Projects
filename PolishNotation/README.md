**API application to evaluate expressions in Polish Notation**

Build: mvn clean install  
Execute: mvn spring-boot:run  
Base URL: localhost:8080/api.pn  

Example:  
Send POST request to localhost:8080/api.pn/evaluate with request body (raw text):  
\+ + 0.5 1.5 * 4 10  
\- 2e3 - 700 + 7 * 2 15  
\- -1.5 * 3.1415 / -7 -2  

API description: localhost:8080/api.pn/swagger-ui.html   
To execute console application, run main method in org.dmx.pn.utils.PolishNotation.java file.
