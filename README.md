# phone-number-validator
A small project to list some customers phone numbers and validate it, SQLite practice 


# How to run 
- Clone repository.
- Make sure you have docker and docker-compose installed
- using terminal run in project root directory and run `docker-compose up --build`, this will create 2 docker images one for backend and other for frontend.
- To stop both applications in the same project root directory where `docker-compose.yml` exist run `docker-compose down` this will kill application instances. 
- Frontend is served using `nginx` server on port `80`.
- Backend is using port `8080`.
- No environment variables required to run the application, default one is used `application.properties`. 

# Warnigns
- Backend application is tightly coupled with DB in the `phone-validator/sample.db` file removal will cause application error, replace with same file name.

# Extra Info
- Application is using both server side pagination and server side filtering (searching) 
- Using custom `SQLiteDialct` open source to integrate spring boot with SQLite for easy data read and write.
- Using `Drivermanager` to get a connection and adding implementation for `regexp` functionality to search data. 

# Project directory sturcture 
- **phone-validator-frontend** : Contains the angular project files
- **phone-validator** : Java - Spring boot application for backend


# Technologies 
- Angular 12 with ng-zorro for frontend 
- Spring framework for backend
- SQLite for DB.
- Docker and Docker-Compose for building and running applicaiton.

