package br.com.sis.webflux.documents;

import java.util.Arrays;
import java.util.Optional;

public enum CountryEnum {
	JAPAN("こんにちは、これは日本です！ / Kon'nichiwa, kore wa Nihondesu!"),
	BRAZIL("Coé rapaziada, aqui é Brasil!"),
	USA("Hi friends, this is USA!"),
	FRANCE("Salut les amis, c'est Franca ici!"),
	GERMANY("Hallo Freunde, das ist Deutschland!"),
	WORLD("Hello World!");
	
	private String value;

	private CountryEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public static CountryEnum getEnum(String value)
	{
		Optional<CountryEnum> optCountryEnum = Arrays.asList(CountryEnum.values())
												.stream()
												.filter(e-> e.getValue().toLowerCase().contains(value.toLowerCase()))
												.findFirst();
		return optCountryEnum.isPresent() ? optCountryEnum.get() : CountryEnum.WORLD;
	}
	
}
