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

  public RotomatModel(int amountShelves, int amountCompartmentsPerShelf) {
    this.shelves = new ArrayList<>(amountShelves);
    for (int i = 0; i < amountShelves; i++) {
      shelves.add(createNewShelf(amountCompartmentsPerShelf));
    }
    this.modelIsEditable = false;
  }

  public boolean renameSlot(int shelfIndex, int compartmentIndex, String newName) {
    if (shelves.size() <= shelfIndex || shelves.get(0).size() <= compartmentIndex) {
      return false;
    } else {
      shelves.get(shelfIndex).set(compartmentIndex, newName + "edited!");
      updateTextField(
          (shelfIndex + 1), (compartmentIndex + 1), shelves.get(shelfIndex).get(compartmentIndex));
      return true;
    }
  }

  private static ArrayList<String> createNewShelf(int amountCompartments) {
    ArrayList<String> shelf = new ArrayList<>(amountCompartments);
    for (int i = 0; i < amountCompartments; i++) {
      shelf.add("Leeres Fach");
    }
    return shelf;
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
