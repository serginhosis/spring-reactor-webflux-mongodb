package br.com.sis.webflux.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import br.com.sis.webflux.documents.Person;
import reactor.core.publisher.Mono;

@Repository
public abstract interface PersonRepository
  extends ReactiveCrudRepository<Person, String>
{
  public abstract Mono<Person> findFirstByName(Mono<String> paramMono);
}
