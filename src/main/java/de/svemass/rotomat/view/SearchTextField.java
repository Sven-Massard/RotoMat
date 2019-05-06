package de.svemass.rotomat.view;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

class SearchTextField extends TextField {

  SearchTextField(RotomatView view) {
    super();
    setPromptText("Suchen");

    setOnKeyPressed(
        keyEvent -> {
          if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            view.search(this.getText());
          }
        });
  }
}
