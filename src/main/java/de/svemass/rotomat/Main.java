package de.svemass.rotomat;

import javafx.application.Application;
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

public class Main extends Application {
  private static final int amountShelves = 45;
  private static final int amountCompartmentsPerShelf = 6;
  private boolean isFieldEditable;

  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage primaryStage) {
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(createGrid());
    Scene scene = new Scene(new Group());
    VBox vBox = new VBox();
    vBox.getChildren().addAll(scrollPane);
    scene.setRoot(vBox);

    // Buttons
    Button editButton = new Button("Felder editierbar machen");
    editButton.setOnAction(
        actionEvent -> {
          isFieldEditable = !isFieldEditable;
          if (isFieldEditable) {
            editButton.setText("Felder nicht mehr editierbar machen");
          } else {
            editButton.setText("Felder editierbar machen");
          }

          GridPane pane = (GridPane) scrollPane.getContent();
          for (Node node : pane.getChildren()) {
            if (node instanceof TextField) {
              ((TextField) node).setEditable(isFieldEditable);
            }
          }
        });
    Button saveButton = new Button("Speichern");
    saveButton.setOnAction(actionEvent -> {});

    BorderPane buttonBox = new BorderPane();
    buttonBox.setLeft(editButton);
    buttonBox.setRight(saveButton);

    vBox.getChildren().add(buttonBox);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * Erstellt ein grid pane mit Fächern und Sektionen, welche den Rotomaten widerspiegeln sollen.
   * Anzahl der Fächer und Sektionen werden in den statischen Variablen dieser Klasse definiert.
   *
   * @return GridPane mit Fächern und Sektionen wie in den statischen variabeln definiert
   */
  private GridPane createGrid() {
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(1);
    grid.setPadding(new Insets(25, 25, 25, 25));
    for (int i = 0; i < Main.amountShelves; i++) {
      grid.add(new Label("Fach " + (i + 1) + ""), 0, i + 1);
      for (int j = 0; j < Main.amountCompartmentsPerShelf; j++) {
        grid.add(new Label("Sektion " + (j + 1) + ""), j + 1, 0);
        grid.add(new TextField(), j + 1, i + 1);
      }
    }
    for (Node node : grid.getChildren()) {
      if (node instanceof TextField) {
        ((TextField) node).setEditable(false);
        isFieldEditable = false;
      }
    }
    return grid;
  }

  //  private File getPreferences() { // TODO
  //    Preferences prefs = Preferences.userNodeForPackage(Main.class);
  //    String filePath = prefs.get("filePath", null);
  //    if (filePath != null) {
  //      return new File(filePath);
  //    } else {
  //      return null;
  //    }
  //  }
  //
  //  public void setPreferences(File file) { // TODO
  //    Preferences prefs = Preferences.userNodeForPackage(Main.class);
  //    prefs.put("filePath", file.getPath());
  //  }

}
