package de.svemass.rotomat.model;

import de.svemass.rotomat.view.RotomatModelObserver;

abstract class ObservableRotomatModel {
  private RotomatModelObserver observer;

  void setObserver(RotomatModelObserver observer) {
    this.observer = observer;
  }

  void updateGridView(RotomatModel model) {
    observer.updateGridView(model);
  }

  void updateGridIsEditable(boolean isEditable) {
    observer.updateGridIsEditable(isEditable);
  }

  void updateTextField(int row, int column, String newName) {
    observer.updateTextField(row, column, newName);
  }
}
