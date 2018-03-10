# Spring REST Docs sample appliation

[![Ahmedabad Java Meetup](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCUuuCj6gMJ51kXwg0YYaPf_Uq7weOFftbymbzrSua5WrDbynGAA)]()

This is a simple demo app to show how test driven documentation works with spring REST docs. In this app user can tweet and add a commnet on a tweet.

The dependency added to support REST documentation:
```xml
<dependency>
    <groupId>org.springframework.restdocs</groupId>
    <artifactId>spring-restdocs-mockmvc</artifactId>
    <scope>test</scope>
</dependency>
```

The plugin is used to convert the generated asciidoctor (.adoc) file to HTML file:
```xml
<plugin>
    <groupId>org.asciidoctor</groupId>
    <artifactId>asciidoctor-maven-plugin</artifactId>
    <version>1.5.6</version>
</plugin>
```

In DemoDocsIT.java, the annotation `@AutoConfigureRestDocs("target/snippets")` is used to tell spring to generate the documents file at *target/snippets* directory.

The final documentation file is manually created at *src/main/asciidoc/index.adoc* where all the generated snippets are included along with some more details.

To run the application, simply run the following command in project's base directory.

`./mvnw clean verify spring-boot:run`


The application will be started at `localhost:8080`

Generated REST docs will be available at `http://localhost:8080/rest-docs/index.html`

**Documents are ready, Yey!**