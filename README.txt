Instructions:
1.	Unpack the front-end vote-app_angular, and the back-end vote-app_spring.
2.	Ensure Angular CLI is installed, along with Node.js and npm. https://angular.io/guide/quickstart
3.	Ensure Maven is installed.	https://maven.apache.org/download.cgi
4.	From the SpringBootAngularHttpGet folder, run "mvn spring-boot:run". This will launch the data server.
5.	From the vote-app folder, run "npm start". This will launch the web application and launch the proxy to the data server.
6.	From a web browser, connect to 'localhost:4200'

Notes:
Voting can only be done once per 24 hours per IP address. 
Votes and IP addresses can be cleared by accessing 'localhost:8081/reset' without resetting the data server
The web application is a SPA and will return a 404 if attempting to access the results page by URL
Data is not read/saved to a file and is lost when the data server is terminated