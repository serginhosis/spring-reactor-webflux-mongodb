package br.com.sis.webflux.controllers;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sis.webflux.documents.Person;
import br.com.sis.webflux.service.PersonService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class PersonController {
    @Autowired
    PersonService personService;

    @ApiOperation(value="Save a person on database")
    @PostMapping(value={"/person"}, consumes={"application/json"})
    public Mono<ResponseEntity<Person>> save(@RequestBody Person person, HttpServletRequest request) {
        person.setId(null);
        return personService.save(person)
        					.flatMap(p -> Mono.just(ResponseEntity.created(URI.create(request.getRequestURI() + "/" + p.getId()))
        					.build()));
    }

    @ApiOperation(value="Find a person by name")
    @ApiResponses(value={@ApiResponse(response=Person.class, code=200, message="We found a person")})
    @GetMapping(value={"/person/name/{name}"}, produces={"application/json"})
    public Mono<ResponseEntity<Person>> getPersonByName(@PathVariable(value="name") @ApiParam(value="Name of person") String name) {
       return personService.findByName(Mono.just(name))
       					   .map(p -> ResponseEntity.ok(p))
       					   .defaultIfEmpty(ResponseEntity.notFound().build());
       					   
    }

    @ApiOperation(value="Find a person by id")
    @ApiResponses(value={@ApiResponse(response=Person.class, code=200, message="We found a person")})
    @GetMapping(value={"/person/{id}"}, produces={"application/json"})
    public Mono<ResponseEntity<Person>> getPerson(@PathVariable(value="id") @ApiParam(value="Id of person") String id) {
      return  personService.find(Mono.just(id))
						   .map(p -> ResponseEntity.ok(p))
						   .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @ApiOperation(value="List all persons")
    @ApiResponses(value={@ApiResponse(response=Person.class, responseContainer="List", code=200, message="All persons on system")})
    @GetMapping(value={"/person/list"}, produces={"application/json"})
    public Flux<Person> getPersons() {
        return this.personService.findAll();
    }

    @ApiOperation(value="Remove one person by id")
    @DeleteMapping(value={"/person/{id}"})
    public Mono<Void> delete(@PathVariable(value="id") @ApiParam(value="Id of person") String id) {
       return personService.delete(id);
    }
}
