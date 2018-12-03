package br.com.sis.webflux.repository;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.sis.webflux.WebFluxApplication;
import br.com.sis.webflux.documents.CountryEnum;
import br.com.sis.webflux.documents.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = WebFluxApplication.class)
public class PersonRepositoryTest {

	@Autowired
	PersonRepository repository;
	
	
	@Test
	public void shouldSaveAPersonPerson() {
		 Mono<Person> monoPerson = repository.save(new Person("Bill", CountryEnum.USA));
	      StepVerifier.create(monoPerson)
	                .assertNext(person -> {
	                    assertEquals("Bill", person.getName());
	                    assertEquals(CountryEnum.USA , person.getCountry());
	                    assertNotNull(person.getId());
	                })
	                .expectComplete()
	                .verify();
	}
	
	@Test
	public void shouldGivenAPersonByID() {
		   Person blockedPerson = repository.save(new Person("Bill", CountryEnum.BRAZIL)).block();
	       Mono<Person> monoPerson = repository.findById(Mono.just(blockedPerson.getId()));

	       StepVerifier.create(monoPerson)
	                .assertNext(person -> {
	                    assertEquals("Bill", person.getName());
	                    assertEquals(CountryEnum.BRAZIL , person.getCountry());
	                    assertNotNull(person.getId());
	                })
	                .expectComplete()
	                .verify();
	}
	
	@Test
	public void shouldGivenAListWithTwoPersons() {
		   repository.save(new Person("Bill", CountryEnum.BRAZIL)).block();
		   repository.save(new Person("Midorya", CountryEnum.JAPAN)).block();
		   Flux<Person> findAll = repository.findAll();
		   
	       StepVerifier.create(findAll)
	                .assertNext(person -> person.getName().equals("Bill"))
	                .assertNext(person -> person.getName().equals("Midorya"))
	                .expectComplete()
	                .verify();
	}
	
	@Test
	public void shouldFoundAListByName() {
		   repository.save(new Person("Zeca", CountryEnum.BRAZIL)).block();
		   repository.save(new Person("zeca", CountryEnum.JAPAN)).block();
		   Mono<List<Person>> monoList = repository.findByNameIgnoreCaseContaining(Mono.just("zeca"))
		   			 							   .collect(toList());
		   
	       StepVerifier.create(monoList)
			       .assertNext(list -> assertEquals(2,list.size()))
	               .expectComplete()
	               .verify();
	}
	
	@Test
	public void shouldRemovePerson() {
		 Person blockedPerson = repository.save(new Person("Bill", CountryEnum.USA)).block();
		 repository.delete(blockedPerson).block();
		 Mono<Person> monoPerson = repository.findById(Mono.just(blockedPerson.getId()));
	     StepVerifier.create(monoPerson)
	     			 .verifyComplete();
	}

}
