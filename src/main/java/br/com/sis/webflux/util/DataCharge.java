package br.com.sis.webflux.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.sis.webflux.documents.CountryEnum;
import br.com.sis.webflux.documents.Person;
import br.com.sis.webflux.repository.PersonRepository;
import reactor.core.publisher.Flux;

@Component
public class DataCharge implements CommandLineRunner {

	private final PersonRepository personRepository;

	DataCharge(PersonRepository personRepository) {
	     this.personRepository = personRepository;
	  }

	@Override
	public void run(String... args) throws Exception {
		personRepository.saveAll(
								Flux.just(
											new Person("Thomas Müller", CountryEnum.GERMANY),
											new Person("João Silva", CountryEnum.BRAZIL),
											new Person("Jundi Kajishima", CountryEnum.JAPAN),
											new Person("Richard Nicholson", CountryEnum.USA),
											new Person("Gerrad d'Bovoir", CountryEnum.FRANCE)
								))
						.flatMap(personRepository::save)
						.subscribe(System.out::println);
	}

}
