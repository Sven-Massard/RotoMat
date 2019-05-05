package de.svemass.rotomat.controller;

import de.svemass.rotomat.model.RotomatModel;
import de.svemass.rotomat.view.RotomatView;

public class RotomatController {
  private static final int amountShelves = 46; // TODO Move elsewhere
  private static final int amountCompartmentsPerShelf = 6; // TODO Move elsewhere
  private RotomatModel model;

  public void initializeModel(RotomatView view) {
    model = new RotomatModel(amountShelves, amountCompartmentsPerShelf, view);
  }

  public void toggleGridEditable() {
    model.toggleGridEditable();
  }

  public void updateField(
      Integer rowIndex, Integer columnIndex, String text, Integer caretPosition) {
    if (!model.isModelEditable()) {
      System.out.println("Error! Model not editable!"); // TODO Make real log entry
    }
    if (!model.renameSlot(rowIndex - 1, columnIndex - 1, text, caretPosition)) {
      // TODO Make real log entry
      System.out.println("Error! Index out of  bounds!");
    }
  }
}
