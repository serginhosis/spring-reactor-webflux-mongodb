package br.com.sis.webflux.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.sis.webflux.WebFluxApplication;
import br.com.sis.webflux.documents.CountryEnum;
import br.com.sis.webflux.documents.Person;
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
	                    assertEquals(CountryEnum.BRAZIL , person.getCountry());
	                    assertNotNull(person.getId());
	                })
	                .expectComplete()
	                .verify();
	}
	
	@Test
	public void shouldGivenAPersonByName() {
		   repository.save(new Person("Bill", CountryEnum.BRAZIL)).block();
	       Mono<Person> monoPerson = repository.findFirstByName(Mono.just("Bill"));

	       StepVerifier.create(monoPerson)
	                .assertNext(person -> {
	                    assertEquals("Bill", person.getName());
	                    assertEquals(CountryEnum.BRAZIL , person.getCountry());
	                    assertNotNull(person.getId());
	                })
	                .expectComplete()
	                .verify();
	}

}
