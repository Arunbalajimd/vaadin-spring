In this place you put all your repositories/DAOs. Use this separate data access layer to encapsulate
your queries and enable them ready for unit/integration testing. You can generate test data in an extra
JUnit test and check if all your queries are working as expected to be sure an error is not caused by a
misspelled query.

For simplification, the repository uses Spring-Data to provide a basic CRUD-Layer and allows the usage
of QueryDSL for more sophisticated queries. Please refer to the spring data jpa documentation for more
details: http://www.springsource.org/spring-data/jpa