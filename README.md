The validator service is a simple example bringing together the [Apache Camel REST DSL](https://github.com/apache/camel/blob/master/camel-core/src/main/docs/rest-dsl.adoc), [Jackson](https://github.com/FasterXML/jackson-core) and [Spring Boot](https://projects.spring.io/spring-boot/).  
One could POST a JSON order, the service will validate that the customer exist and that products are available.

# How Could I Run This ?
get this repository  
cd "path to project"  
mvn clean install  
java -jar target/rest-dsl-spring-boot-xstream-example-0.0.1-SNAPSHOT.jar  

Then launch a REST client (why not postman ?)  
Set the HTTP method to "POST"  
Set the url to "http://localhost:8080/camel/order/validation"  
Set the raw content to:  
```json
{
  "customerId": "aldettinger",
  "products":
   [
   {
    "productId": "Water"
   }
   ]
}
```

You can then experiment other scenarios by sending the content of files located "src/test/resources/json-orders/*"
