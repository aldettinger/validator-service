The validator service is a simple example bringing together the [Apache Camel REST DSL](https://github.com/apache/camel/blob/master/camel-core/src/main/docs/rest-dsl.adoc), [Jackson](https://github.com/FasterXML/jackson-core) and [Spring Boot](https://projects.spring.io/spring-boot/).  
One could POST a JSON order, the service will validate that the customer exist and that products are available.

# How Could I Run This ?
git clone https://github.com/aldettinger/validator-service  
cd validator-service
mvn clean install  
java -jar target/validator-service-*.jar  

Then launch a REST client (why not postman ?)  
Set the HTTP method to "POST"  
Set the url to "http://localhost:8080/camel/order/validation"  
Set the body raw content to:  
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
Send the HTTP POST and see the response completed with status fields.

You can then experiment other scenarios by sending the content of files located "src/test/resources/json-orders/*"
