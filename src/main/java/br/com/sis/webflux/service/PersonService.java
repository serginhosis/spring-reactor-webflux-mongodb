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
  
  public Mono<Person> findByName(Mono<String> name)
  {
    return personRepository.findFirstByName(name);
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
}