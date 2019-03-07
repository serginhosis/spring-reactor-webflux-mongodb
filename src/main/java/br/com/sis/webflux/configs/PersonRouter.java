package br.com.sis.webflux.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import br.com.sis.webflux.controllers.PersonHandler;

@Configuration
public class PersonRouter {

	@Bean
	RouterFunction<ServerResponse> route(PersonHandler handler){
		return RouterFunctions.route(RequestPredicates.GET("/personas"), handler::allPersons)
							  .andRoute(RequestPredicates.GET("/personas/{id}"), handler::findPersonById);
	}
}
