package de.svemass.rotomat.controller;

import de.svemass.rotomat.model.RotomatModel;

public class Main {
    private static final int amountShelves = 46;
    private static final int amountCompartmentsPerShelf = 6;

    public static void main(String[] args) {
        RotomatModel rotomatModel = new RotomatModel(amountShelves, amountCompartmentsPerShelf);
        System.out.println(rotomatModel.toString());
    }
}
