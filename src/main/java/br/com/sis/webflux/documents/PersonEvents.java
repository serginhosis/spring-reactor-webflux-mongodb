package br.com.sis.webflux.documents;

import java.time.LocalDate;

public class PersonEvents {
	private LocalDate when;
	private Person person;

	public PersonEvents() {
	}

	public PersonEvents(LocalDate when, Person person) {
		super();
		this.when = when;
		this.person = person;
	}

	public LocalDate getWhen() {
		return when;
	}

	public void setWhen(LocalDate when) {
		this.when = when;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
