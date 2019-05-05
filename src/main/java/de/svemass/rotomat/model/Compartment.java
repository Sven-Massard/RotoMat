package de.svemass.rotomat.model;

public class Compartment {
  private String name;

  Compartment(String name) {
    setName(name);
  }

  public String getName() {
    return this.name;
  }

  void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return getName();
  }
}
