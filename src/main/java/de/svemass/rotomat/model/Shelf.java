package de.svemass.rotomat.model;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;

public class Shelf {
  @XmlElement private ArrayList<Compartment> shelf;

  @SuppressWarnings("unused")
  public Shelf() {} // Do not remove, needed for JAXB

  Shelf(int amountCompartments) {
    shelf = new ArrayList<>(amountCompartments);
    for (int i = 0; i < amountCompartments; i++) {
      shelf.add(new Compartment(""));
    }
  }

  public Compartment getCompartment(int index) {
    return shelf.get(index);
  }

  public int getAmountComparments() {
    return shelf.size();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (Compartment compartment : shelf) {
      sb.append(compartment.toString()).append(", ");
    }
    sb.setLength(sb.length() - 2);
    sb.append("]");
    return sb.toString();
  }
}
