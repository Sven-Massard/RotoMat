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
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;

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
  public void start(Stage primaryStage) throws JAXBException {
    instance = this;
    stage = primaryStage;
    stage.setTitle("Rotomat Inventar");
    scrollPane = new ScrollPane();
    scrollPane.setFitToWidth(true);
    Scene scene = new Scene(new Group());
    VBox vbox = new VBox();
    vbox.getChildren().addAll(scrollPane);

    scene.setRoot(vbox);

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

    // Search Button
    TextField searchTextField = createSearchTextField();

    BorderPane buttonAndSearchPane = new BorderPane();
    buttonAndSearchPane.setLeft(editButton);
    buttonAndSearchPane.setRight(saveButton);
    buttonAndSearchPane.setCenter(searchTextField);
    vbox.getChildren().add(buttonAndSearchPane);
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
      if (node instanceof InventoryTextField
          && GridPane.getColumnIndex(node) == column
          && GridPane.getRowIndex(node) == row) {
        ((InventoryTextField) node).removeListeners();
        Platform.runLater(
            () -> {
              gridPane.getChildren().remove(node);
              TextField newTextField = createInventoryTextField(newName);
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
        TextField currentTextField = createInventoryTextField(shelf.getCompartment(j).getName());
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

  private TextField createInventoryTextField(String name) {
    InventoryTextField textField = new InventoryTextField(name, isGridEditable, controller);
    GridPane.setHgrow(textField, Priority.ALWAYS);
    return textField;
  }

  private TextField createSearchTextField() {

    return new SearchTextField(this);
  }

  void search(String text) {
    for (Node node : gridPane.getChildren()) {
      if (node instanceof InventoryTextField) {
        InventoryTextField currentTextField = (InventoryTextField) node;
        if (currentTextField.getText().toLowerCase().contains(text.toLowerCase())) {
          currentTextField.setStyle("-fx-background-color: lightgreen;");
        } else {
          currentTextField.setStyle(null);
        }
      }
    }
  }
}
