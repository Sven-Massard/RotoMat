package de.svemass.rotomat.view;

import de.svemass.rotomat.controller.RotomatController;
import de.svemass.rotomat.model.RotomatModel;
import de.svemass.rotomat.model.Shelf;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RotomatView extends Application implements RotomatModelObserver {
  private static RotomatController controller;
  private static RotomatView instance;
  private boolean isGridEditable;
  private GridPane gridPane;
  private ScrollPane scrollPane;
  private Stage stage;

  public static void main(String[] args) {
    launch();
  }

  private static RotomatView getInstance() {
    return instance;
  }

  public static void setController(RotomatController rotomatController) {
    controller = rotomatController;
  }

  @Override
  public void start(Stage primaryStage) {
    instance = this;
    stage = primaryStage;
    scrollPane = new ScrollPane();
    Scene scene = new Scene(new Group());
    VBox vBox = new VBox();
    vBox.getChildren().addAll(scrollPane);
    scene.setRoot(vBox);

    // Buttons
    Button editButton = new Button("Felder editierbar machen");
    editButton.setOnAction(
        actionEvent -> {
          controller.toggleGridEditable();

          if (isGridEditable) {
            editButton.setText("Felder nicht mehr editierbar machen");
          } else {
            editButton.setText("Felder editierbar machen");
          }
          toggleGridEditable(isGridEditable);
        });
    Button saveButton = new Button("Speichern");
    saveButton.setOnAction(actionEvent -> controller.saveToFile());

    BorderPane buttonBox = new BorderPane();
    buttonBox.setLeft(editButton);
    buttonBox.setRight(saveButton);
    vBox.getChildren().add(buttonBox);
    stage.setScene(scene);
    controller.initializeModel(getInstance());
  }

  @Override
  public void updateGridView(RotomatModel model) {
    gridPane = buildRotomatGrid(model);
    scrollPane.setContent(gridPane);
    stage.show();
  }

  @Override
  public void updateGridIsEditable(boolean isEditable) { // TODO remove?
    this.isGridEditable = isEditable;
  }

  @Override
  public void updateTextField(int row, int column, String newName, Integer caretPosition) {
    for (Node node : gridPane.getChildren()) {
      if (node instanceof TextFieldWithListener
          && GridPane.getColumnIndex(node) == column
          && GridPane.getRowIndex(node) == row) {
        ((TextFieldWithListener) node).removeListeners();
        Platform.runLater(
            () -> {
              gridPane.getChildren().remove(node);
              TextField newTextField = createTextField(newName);
              gridPane.add(newTextField, column, row);
              if (caretPosition != null) {
                newTextField.requestFocus();
                newTextField.positionCaret(caretPosition);
              }
            });
        break;
      }
    }
  }

  private GridPane buildRotomatGrid(RotomatModel rotomatModel) {
    isGridEditable = rotomatModel.isModelEditable();
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(1);
    grid.setPadding(new Insets(25, 25, 25, 25));
    if (!rotomatModel.getShelves().isEmpty()) {
      for (int i = 0; i < rotomatModel.getShelves().get(0).getAmountComparments(); i++) {
        grid.add(new Label("Sektion " + (i + 1) + ""), i + 1, 0);
      }
    } else {
      return grid;
    }
    for (int i = 0; i < rotomatModel.getShelves().size(); i++) {
      grid.add(new Label("Regal " + (i + 1) + ""), 0, i + 1);
      Shelf shelf = rotomatModel.getShelves().get(i);
      for (int j = 0; j < shelf.getAmountComparments(); j++) {
        TextField currentTextField = createTextField(shelf.getCompartment(j).getName());
        grid.add(currentTextField, j + 1, i + 1);
      }
    }
    return grid;
  }

  private void toggleGridEditable(boolean isFieldEditable) {
    if (!(gridPane == null)) {
      for (Node node : gridPane.getChildren()) {
        if (node instanceof TextField) {
          ((TextField) node).setEditable(isFieldEditable);
        }
      }
    }
  }

  private TextField createTextField(String name) {
    return new TextFieldWithListener(name, isGridEditable, controller);
  }
}
