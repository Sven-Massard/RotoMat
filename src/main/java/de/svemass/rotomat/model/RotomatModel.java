package de.svemass.rotomat.model;

import de.svemass.rotomat.view.RotomatView;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class RotomatModel extends ObservableRotomatModel {

  @XmlElement private ArrayList<Shelf> shelves;
  @XmlElement private boolean modelIsEditable;

  @SuppressWarnings("unused")
  public RotomatModel() {} // Do not remove! Needed for Xml builder.

  public RotomatModel(int amountShelves, int amountCompartmentsPerShelf, RotomatView view) {
    this(amountShelves, amountCompartmentsPerShelf);
    this.setObserver(view);
    this.updateGridView(this);
  }

  RotomatModel(int amountShelves, int amountCompartmentsPerShelf) {
    this.shelves = new ArrayList<>(amountShelves);
    for (int i = 0; i < amountShelves; i++) {
      shelves.add(new Shelf(amountCompartmentsPerShelf));
    }
    this.modelIsEditable = false;
  }

  public boolean renameSlot(
      int shelfIndex, int compartmentIndex, String newName, Integer caretPosition) {

    if (shelves.size() <= shelfIndex || shelves.get(0).getAmountComparments() <= compartmentIndex) {
      return false;
    }
    Shelf shelf = shelves.get(shelfIndex);
    if (!shelf.getCompartment(compartmentIndex).getName().equals(newName)) {
      shelf.getCompartment(compartmentIndex).setName(newName);
      updateTextField(
          (shelfIndex + 1),
          (compartmentIndex + 1),
          shelf.getCompartment(compartmentIndex).getName(),
          caretPosition);
    }
    return true;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Shelf shelf : shelves) {
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

  public ArrayList<Shelf> getShelves() {
    return shelves;
  }
}
