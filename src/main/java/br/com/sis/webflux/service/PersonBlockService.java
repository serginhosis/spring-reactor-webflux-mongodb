package br.com.sis.webflux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sis.webflux.documents.Person;
import br.com.sis.webflux.repository.PersonBlockRepository;

@Service
public class PersonBlockService
{
  @Autowired
  PersonBlockRepository personRepository;
  
  public Person save(Person person)
  {
    return personRepository.save(person);
  }
  
  public List<Person> findByName(String name)
  {
    return personRepository.findByNameIgnoreCaseContaining(name);
  }
  
  public Person find(String id)
  {
    return personRepository.findById(id).get();
  }
  
  public List<Person> findAll()
  {
    return personRepository.findAll();
  }
  
  public void delete(String id)
  {
     personRepository.deleteById(id);
  }

  public Person update(Person person, String id) 
  {
	   return personRepository.findById(id)
		   					  .map(foundedPerson -> 
		   										{	
		   											foundedPerson.setCountry(person.getCountry());
													foundedPerson.setName(person.getName());
													personRepository.save(foundedPerson);
													return foundedPerson;
												}).get();
	  
  }
}