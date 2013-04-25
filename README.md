vaadin7-spring3-archtypes
=========================

Vaadin 7 and Spring 3 maven archetype for WAR/EAR projects

<b>Feel free to contribute!!</b>
If you find something thats missing give me feedback for enhancement.

- The <b>parent</b> project defines all common dependencies and its versions for dependency management. The
    defined dependency groups are:
    - Spring3 stack (with Spring-Data)
    - Vaadin7 stack
    - Hibernate with MySQL und HSQL
    - Apache Common Libs
- The <b>profiles</b> project defines a set of profiles to be used as predefined dependency sets for usage in your
    projects. These profiles are not very fine grained at the moment and should be improved for different
    data providers.
- The <b>war</b> is a fully running spring3 and vaadin7 web application stack. It comes with a little example
    implementation of a vaadin table where you can add and delete entries. There are examples of entites,
    spring-data-jpa repositories, a service layer and junit test setup with hamcrest ready to use for a quick start
    of your application development. It uses the <b>blackboard</b> event addon for easier event distribution and
    the vaadin-spring-integration addon for running spring within vaadin applications.

    The project is structured in 4 sections:
    - <b>model</b> - Place for entities
    - <b>repositories</b> - Place for DAOs with SpringData
    - <b>services</b> - Place for your business layer
    - <b>ui</b> - Place for UI classes of vaadin

    In the test area (src/test/java) is an example for an integration test with spring. For simple unit tests you
    would mock your interfaces but you can also inject the real sevices ans DAOs if you like. The application context
    is setup with an in-memory hsqldb where you can start from.

- The <b>war-build</b> is a multi-module building pom for building the application from scratch.

How to get startet:

1. Get Java und Maven configured on your classpath
2. Clone the repository
3. Go to <b>war-build</b> and run: <b>mvn clean install</b>
4. Go to <b>war</b> and start jetty: <b>mvn jetty:run</b>
5. Open your browser to <b>http://localhost:8080/</b> and test the application

Things i would like to see in this prototype (may be contributed by others :) )

- Selenium test setup (optional with selenium server)
- CI with Jenkins (HowTo)
- Maven-SCM integration for proper release management
- Fine-Grained profiles for different JPA-providers and Databases to be combined freely as needed
- NoSQL-Setup
- Tomcat with load-balanced cluster configuration using Distributed EhCache for sharing spring application context

Some of these can be integrated in the prototype and the other need additional documentation
to describe how to setup these things