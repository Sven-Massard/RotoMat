package de.svemass.rotomat.view;

import de.svemass.rotomat.controller.RotomatController;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

class InventoryTextField extends TextField {

  private ChangeListener<Boolean> focusListener;

  InventoryTextField(String name, boolean isGridEditable, RotomatController controller) {
    super(name);

    setEditable(isGridEditable);

    EventHandler<KeyEvent> keyEventEventHandler =
        keyEvent -> {
          if (isEditable() && keyEvent.getCode().equals(KeyCode.ENTER)) {
            controller.updateField(
                getNodePosition()[0], getNodePosition()[1], getText(), getCaretPosition());
          }
        };
    setOnKeyPressed(keyEventEventHandler);

    focusListener =
        (observableValue, oldPropertyValue, newPropertyValue) -> {
          if (isEditable() && oldPropertyValue && !newPropertyValue) {
            controller.updateField(getNodePosition()[0], getNodePosition()[1], getText(), null);
          }
        };

    focusedProperty().addListener(focusListener);
  }

  void removeListeners() {
    focusedProperty().removeListener(focusListener);
  }

  private Integer[] getNodePosition() {
    return new Integer[] {GridPane.getRowIndex(this), GridPane.getColumnIndex(this)};
  }
}
