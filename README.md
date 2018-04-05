The validator service is a simple example bringing together the [Apache Camel REST DSL](https://github.com/apache/camel/blob/master/camel-core/src/main/docs/rest-dsl.adoc), [Jackson](https://github.com/FasterXML/jackson-core) and [Spring Boot](https://projects.spring.io/spring-boot/).  
One could POST a JSON order, the service will validate that the customer exist and that products are available.

# How Could I Start The Validator Service ?
git clone https://github.com/aldettinger/validator-service  
cd validator-service  
mvn clean install  
java -jar target/validator-service-*.jar  

# How Could I Invoke The Validator Service ?

cd validator-service/src/test/resources/json-orders  
cat ok.json  
curl -X POST -d @ok.json http://localhost:8080/camel/order/validation --header 'Content-Type: application/json'  
  
You can now experiment what happen in other scenarios, for instance:  
curl -X POST -d @out-of-stock.json http://localhost:8080/camel/order/validation --header 'Content-Type: application/json'  
