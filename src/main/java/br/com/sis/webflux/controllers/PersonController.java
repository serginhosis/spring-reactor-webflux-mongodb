package br.com.sis.webflux.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sis.webflux.documents.Person;
import br.com.sis.webflux.service.PersonBlockService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    PersonBlockService personService;

	/*
	 * @ApiOperation(value="Save a person on database")
	 * 
	 * @PostMapping public Mono<ResponseEntity<Person>> save(@RequestBody Person
	 * person, HttpServletRequest request) { person.setId(null); return
	 * personService.save(person) .flatMap(p ->
	 * Mono.just(ResponseEntity.created(URI.create(request.getRequestURI() + "/" +
	 * p.getId())) .build())); }
	 */
    @ApiOperation(value="Save a person on database")
    @PutMapping("/{id}")
    public ResponseEntity<Person> modify(@RequestBody Person person, @PathVariable(value="id") @ApiParam(value="Id of person") String id) {
        return ResponseEntity.ok(personService.update(person, id));
    }

    @ApiOperation(value="Find a person by name")
    @ApiResponses(value={@ApiResponse(response=Person.class, code=200, message="We found a person")})
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Person>> getPersonByName(@PathVariable(value="name") @ApiParam(value="Name of person") String name) {
       return ResponseEntity.ok(personService.findByName(name));				   
    }

    @ApiOperation(value="Find a person by id")
    @ApiResponses(value={@ApiResponse(response=Person.class, code=200, message="We found a person")})
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable(value="id") @ApiParam(value="Id of person") String id) {
      return  ResponseEntity.ok(personService.find(id));
    }

    @ApiOperation(value="List all persons")
    @ApiResponses(value={@ApiResponse(response=Person.class, responseContainer="List", code=200, message="All persons on system")})
    @GetMapping
    public ResponseEntity<List<Person>> getPersons() {
        return ResponseEntity.ok(personService.findAll());
    }

    @ApiOperation(value="Remove one person by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value="id") @ApiParam(value="Id of person") String id) {
       personService.delete(id);
       return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
