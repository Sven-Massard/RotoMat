package de.svemass.rotomat.model;

import de.svemass.rotomat.view.RotomatModelObserver;

public abstract class ObservableRotomatModel {
    private RotomatModelObserver observer;

    public void setObserver(RotomatModelObserver observer) {
        this.observer = observer;
    }

    void updateGridView(RotomatModel model) {
        observer.updateGridView(model);
    }

    void updateGridIsEditable(boolean isEditable){
        observer.updateGridIsEditable(isEditable);
    }

}
