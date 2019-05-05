package de.svemass.rotomat.view;

import de.svemass.rotomat.model.RotomatModel;

public interface RotomatModelObserver {
    void updateGridView(RotomatModel rotomatModel);

    void updateGridIsEditable(boolean isEditable);
}
