package br.com.sis.webflux.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.sis.webflux.documents.Person;

@Repository
public abstract interface PersonBlockRepository
  extends MongoRepository<Person, String>
{
  List<Person> findByNameIgnoreCaseContaining(String name);
}
