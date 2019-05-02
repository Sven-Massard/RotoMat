package de.svemass.rotomat.model;

import java.util.ArrayList;

public class RotomatModel {

    private ArrayList<ArrayList<String>> shelves;

    public RotomatModel(int amountShelves, int amountCompartmentsPerShelf) {
        this.shelves = new ArrayList<>(amountShelves);
        for (int i = 0; i < amountShelves; i++) {
            shelves.add(createNewShelf(amountCompartmentsPerShelf));
        }
    }

    public boolean renameSlot(int shelfIndex, int compartmentIndex, String newName) {
        if (shelves.size() <= shelfIndex || shelves.get(0).size() <= compartmentIndex) {
            return false;
        } else {
            shelves.get(shelfIndex).set(compartmentIndex, newName);
            return true;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ArrayList<String> shelf : shelves) {
            sb.append(shelf.toString()).append("\n");
        }
        return sb.toString();
    }

    private static ArrayList<String> createNewShelf(int amountCompartments){
        ArrayList<String> shelf = new ArrayList<>(amountCompartments);
        for(int i = 0;i < amountCompartments;i++){
            shelf.add("Leeres Fach");
        }
        return shelf;
    }
}
