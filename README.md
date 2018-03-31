The validator service is a simple example of a camel route exposing a REST service consuming json orders.  
One could POST an order, and the service will validate that the customer exist, and that products are available.

# HOW SHOULD I RUN THIS
get this repository  
cd "path to project"  
mvn clean install  
java -jar target/rest-dsl-spring-boot-xstream-example-0.0.1-SNAPSHOT.jar  

Then launch a REST client (why not postman)  
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
