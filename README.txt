All licenses in this repository are copyrighted by their respective authors. Everything else is released under CC0.

This application is tested on MySQL server.
You need to change application.properties file for connection to database.

Run project: 
Navigate to project root directory and run following command
mvn spring-boot:run

Get all beers
curl -X GET localhost:8080/beers

Get beer by id
curl -X GET localhost:8080/beer/{id}

Fill database with beers
curl -X POST localhost:8080/beer/fill

Delete beer by id
curl -X DELETE localhost:8080/beer/{id}


NOTE:
After first runing the application, go to application.properties and change "spring.jpa.hibernate.ddl-auto=create" to "spring.jpa.hibernate.ddl-auto=none"