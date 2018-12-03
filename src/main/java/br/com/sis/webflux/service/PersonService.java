package br.com.sis.webflux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sis.webflux.documents.Person;
import br.com.sis.webflux.repository.PersonRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonService
{
  @Autowired
  PersonRepository personRepository;
  
  public Mono<Person> save(Person person)
  {
    return personRepository.save(person);
  }
  
  public Flux<Person> findByName(Mono<String> name)
  {
    return personRepository.findByNameIgnoreCaseContaining(name);
  }
  
  public Mono<Person> find(Mono<String> id)
  {
    Mono<Person> person = personRepository.findById(id);
    return person;
  }
  
  public Flux<Person> findAll()
  {
    return personRepository.findAll();
  }
  
  public Mono<Void> delete(String id)
  {
    return personRepository.deleteById(id);
  }

  public Mono<Person> update(Person person, Mono<String> id) 
  {
	   Mono<Person> found = personRepository.findById(id)
		   					.map(foundPerson -> {	foundPerson.setCountry(person.getCountry());
													foundPerson.setName(person.getName());
													return foundPerson;});
	  
	  Person blocked = found.block();
	  if(blocked !=null && blocked.getId() !=null && !blocked.getId().isEmpty())
	  {
		  return personRepository.save(blocked);
	  }
	  
	  return found;
  }
}