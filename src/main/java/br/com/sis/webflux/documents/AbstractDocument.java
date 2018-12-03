package br.com.sis.webflux.documents;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

public class AbstractDocument
{
  @Id
  private String id;
  
  @ApiModelProperty(readOnly=true)
  public String getId()
  {
    return this.id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
}

