package de.svemass.rotomat.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CompartmentTest {

  Compartment compartment;
  @BeforeEach
  void beforeEach(){
    compartment = new Compartment("Test");
  }

  @Test
  void testGetName(){
    assertEquals("Test", compartment.getName());
  }
  @Test
  void testSetName() {
    compartment.setName("Changed Name");
    assertEquals("Changed Name", compartment.getName());
  }

  @Test
  void testToString() {
    assertEquals("Test", compartment.toString());
  }
}