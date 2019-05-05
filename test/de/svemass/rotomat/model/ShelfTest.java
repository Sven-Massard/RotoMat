package de.svemass.rotomat.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShelfTest {

  Shelf shelf;
  @BeforeEach
  void beforeEach(){
    shelf = new Shelf(6);
  }

  @Test
  void getCompartment() {}

  @Test
  void getAmountComparments() {}

  @Test
  void testToString() {
    String expected = "[Leeres Fach, Leeres Fach, Leeres Fach, Leeres Fach, Leeres Fach, Leeres " +
        "Fach]";


    assertEquals(expected, shelf.toString());
  }
}