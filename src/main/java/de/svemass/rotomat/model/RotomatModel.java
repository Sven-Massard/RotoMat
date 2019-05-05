package de.svemass.rotomat.model;

import de.svemass.rotomat.view.RotomatView;

import java.util.ArrayList;

public class RotomatModel extends ObservableRotomatModel {

  private ArrayList<ArrayList<String>> shelves;
  private boolean modelIsEditable;

  public RotomatModel(int amountShelves, int amountCompartmentsPerShelf, RotomatView view) {
    this(amountShelves, amountCompartmentsPerShelf);
    this.setObserver(view);
    this.updateGridView(this);
  }

  RotomatModel(int amountShelves, int amountCompartmentsPerShelf) {
    this.shelves = new ArrayList<>(amountShelves);
    for (int i = 0; i < amountShelves; i++) {
      shelves.add(createNewShelf(amountCompartmentsPerShelf));
    }
    this.modelIsEditable = false;
  }

  private static ArrayList<String> createNewShelf(int amountCompartments) {
    ArrayList<String> shelf = new ArrayList<>(amountCompartments);
    for (int i = 0; i < amountCompartments; i++) {
      shelf.add("Leeres Fach");
    }
    return shelf;
  }

  public boolean renameSlot(
      int shelfIndex, int compartmentIndex, String newName, Integer caretPosition) {

    if (shelves.size() <= shelfIndex || shelves.get(0).size() <= compartmentIndex) {
      return false;
    }
    ArrayList<String> shelf = shelves.get(shelfIndex);
    if (!shelf.get(compartmentIndex).equals(newName)) {
      shelf.set(compartmentIndex, newName);
      updateTextField(
          (shelfIndex + 1), (compartmentIndex + 1), shelf.get(compartmentIndex), caretPosition);
    }
    return true;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (ArrayList<String> shelf : shelves) {
      sb.append(shelf.toString()).append("\n");
    }
    return sb.toString();
  }

  public void toggleGridEditable() {
    this.modelIsEditable = !isModelEditable();
    updateGridIsEditable(this.modelIsEditable);
  }

  public boolean isModelEditable() {
    return modelIsEditable;
  }

  public ArrayList<ArrayList<String>> getShelves() {
    return shelves;
  }
}
