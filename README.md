# westpac

Goals:

This application is solely for the job interview purpose of Johnny Lai


Where is the source code?

https://github.com/fifithecat/westpac for the backend part
https://github.com/fifithecat/westpac_frontend for the frontend part


Unit Test

9 unit test cases

They are located in src/test/jav folder, package com.fifi.java.practise.springhibernate

CommentAPITests

- testControllerAlive test API alive

- commentAPITest insert 2 new comment record and fetch via the API

- testControllerAlive test the error response by not passing postId to the API


CommentCRUDTests

- commentCRUD test the JPA repository, mainly focus on insert/retrieval as this application not involving update/delete


GetRequestTests

- postAPITest test the "post" data request function via jsonplaceholder.typicode.com

- commentAPITest test the "comment" data request function via jsonplaceholder.typicode.com


PostAPITests

- testControllerAlive test API alive

- postAPITest insert 2 new post record and fetch via the API


PostCRUDTests

- postCRUD test the JPA repository, mainly focus on insert/retrieval as this application not involving update/delete


How to execute the unit test cases?

Navigate to the folder with pom.xml and "mvn test"

or run them in eclipse

Documentation

Please find the pdf in the repository
