package de.svemass.rotomat.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import de.svemass.rotomat.view.RotomatModelObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
class RotomatModelTest {

  private static final int amountShelves = 3;
  private static final int amountCompartmentsPerShelf = 6;
  @Mock RotomatModelObserver rotomatModelObserver;
  private RotomatModel cut;
  private RotomatModel mySpy;

  @BeforeEach
  public void beforeEach() {
    cut = new RotomatModel(amountShelves, amountCompartmentsPerShelf);
    mySpy = spy(cut);
    doNothing().when(mySpy).updateTextField(anyInt(), anyInt(), anyString(), anyInt());
    doNothing().when(mySpy).updateGridView(cut);
    doNothing().when(mySpy).updateGridIsEditable(anyBoolean());
  }

  @Test
  public void renameSlotTest() {

    mySpy.renameSlot(1, 3, "Test", 0);
    String expectedResult =
        "[Leeres Fach, Leeres Fach, Leeres Fach, Leeres Fach, Leeres Fach, Leeres Fach]\n"
            + "[Leeres Fach, Leeres Fach, Leeres Fach, Test, Leeres Fach, Leeres Fach]\n"
            + "[Leeres Fach, Leeres Fach, Leeres Fach, Leeres Fach, Leeres Fach, Leeres Fach]\n";
    assertEquals(expectedResult, mySpy.toString());
  }

  @Test
  public void toStringTest() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < amountShelves; i++) {
      sb.append("[");
      sb.append("Leeres Fach, ".repeat(amountCompartmentsPerShelf));
      sb.setLength(sb.length() - 2);
      sb.append("]");
      sb.append("\n");
    }
    assertEquals(sb.toString(), cut.toString());
  }

  @Test
  public void setObserver() {

    MockitoAnnotations.initMocks(this);
    cut.setObserver(rotomatModelObserver);
    assertEquals(rotomatModelObserver, cut.getObserver());
  }

  @Test
  public void toggleGridEditable() {
    boolean before = mySpy.isModelEditable();
    mySpy.toggleGridEditable();
    assertEquals(!before, mySpy.isModelEditable());
  }

  @Test
  public void getShelves() {
    assertEquals(createShelves().toString(), cut.getShelves().toString());
  }

  private ArrayList<Shelf> createShelves() {
    ArrayList<Shelf> shelves = new ArrayList<>(amountShelves);
    for (int i = 0; i < amountShelves; i++) {
      shelves.add(new Shelf(amountCompartmentsPerShelf));
    }
    return shelves;
  }
}
