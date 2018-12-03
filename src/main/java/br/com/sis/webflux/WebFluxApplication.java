package br.com.sis.webflux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.mongodb.reactivestreams.client.MongoClient;

@org.springframework.boot.autoconfigure.SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class WebFluxApplication
{
  @Autowired
  MongoClient mongoClient;
  
  public static void main(String[] args)
  {
    SpringApplication.run(WebFluxApplication.class, args);
  }
  
  @Bean
  public ReactiveMongoTemplate reactiveMongoTemplate()
  {
    return new ReactiveMongoTemplate(this.mongoClient, "test");
  }
}
