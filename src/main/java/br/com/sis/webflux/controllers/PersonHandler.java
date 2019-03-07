package br.com.sis.webflux.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import br.com.sis.webflux.documents.Person;
import br.com.sis.webflux.documents.PersonEvents;
import br.com.sis.webflux.service.PersonService;
import reactor.core.publisher.Mono;

@Component
public class PersonHandler {

	private final PersonService personService;

	public PersonHandler(PersonService personService) {
		this.personService = personService;
	}

	
	/*
	 * @PutMapping("/{id}") public Mono<ServerResponse> modify(ServerRequest
	 * request) { return personService.update(person, Mono.just(id)).flatMap(p ->
	 * Mono.just(ResponseEntity.ok(p)))
	 * .defaultIfEmpty(ResponseEntity.notFound().build()); }
	 */


	public Mono<ServerResponse> allPersons(ServerRequest request) {
		return ServerResponse.ok()
							.body(personService.findAll(), Person.class)
							.doOnError(throwable -> new IllegalStateException());
	}
	
	public Mono<ServerResponse> findPersonById(ServerRequest request){
		String id = request.pathVariable("id");
		return ServerResponse.ok()
							 .body(personService.find(Mono.just(id)), Person.class)
							 .doOnError(throwable -> new IllegalStateException());
	}
	
	public Mono<ServerResponse> findPersonEvents(ServerRequest request){
		String id = request.pathVariable("id");
		return ServerResponse.ok()
							 .contentType(MediaType.TEXT_EVENT_STREAM)
							 .body(personService.streams(id), PersonEvents.class)
							 .doOnError(throwable -> new IllegalStateException());
	}

}
