**UserInfo** is app utilizing Spring Security and OAuth 2 to retrieve user data from Facebook, GitHub or Google.

Build: mvn clean install  
Execute: mvn spring-boot:run  
Home page: http://localhost:8080/UserInfo/login

When registering this app on authorization server use:

GitHub callback URL:
http://localhost:8080/UserInfo

Facebook redirect URI:
http://localhost:8080/UserInfo/

Google redirect URI:
http://localhost:8080/UserInfo/login/oauth2/code/google