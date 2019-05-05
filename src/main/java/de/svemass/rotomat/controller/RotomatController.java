package de.svemass.rotomat.controller;

import de.svemass.rotomat.model.RotomatModel;
import de.svemass.rotomat.view.RotomatView;

public class RotomatController {
    private static final int amountShelves = 46;
    private static final int amountCompartmentsPerShelf = 6;
    private boolean isGridEditable = false;
    private RotomatModel model;

    public RotomatController(RotomatView view){
        model = new RotomatModel(amountShelves, amountCompartmentsPerShelf, view);
    }

    public void toggleGridEditable(){
        model.toggleGridEditable();
    }
}
