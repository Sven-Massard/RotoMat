package de.svemass.rotomat.model;

import javax.xml.bind.annotation.XmlElement;

public class Compartment {
  private String name;

  Compartment(String name) {
    setName(name);
  }

  public String getName() {
    return this.name;
  }

  @XmlElement
  void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return getName();
  }
}
