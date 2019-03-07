package br.com.sis.webflux.documents;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="persons")
@TypeAlias("person")
public class Person
  extends AbstractDocument
{
  private String name;
  @ApiModelProperty
  private CountryEnum country;
  
  public Person(String name, CountryEnum country)
  {
    setName(name);
    setCountry(country);
  }
  
  public Person(String name)
  {
    setName(name);
    setCountry(CountryEnum.WORLD);
  }
  
  public Person() {}
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public void setCountry(CountryEnum country)
  {
    this.country = country;
  }
  
  public CountryEnum getCountry()
  {
    return this.country;
  }
  
  @ApiModelProperty(accessMode = AccessMode.READ_ONLY)
  public String getSayHello()
  {
    return String.format("I'm  %s, %s", getName(), getCountry().getValue());
  }
  
  public String toString()
  {
    return "Person [name=" + this.name + ", country=" + this.country + "]";
  }
}