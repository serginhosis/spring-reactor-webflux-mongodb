package br.com.sis.webflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import br.com.sis.webflux.documents.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public abstract interface PersonRepository
  extends ReactiveMongoRepository<Person, String>
{
  Flux<Person> findByNameIgnoreCaseContaining(Mono<String> name);
}
