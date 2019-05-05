package de.svemass.rotomat.view;

import de.svemass.rotomat.controller.RotomatController;
import de.svemass.rotomat.model.RotomatModel;
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

import java.util.ArrayList;


public class RotomatView extends Application implements RotomatModelObserver {
    private boolean isFieldEditable;
    private GridPane gridPane;
    private ScrollPane scrollPane;
    private Scene scene;
    private Stage stage;
    private static RotomatView instance;
    private RotomatController controller;

    public RotomatView() {
        instance = this;
    }

    public static RotomatView getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        controller = new RotomatController(this);
        stage = primaryStage;
        scrollPane = new ScrollPane();
        scrollPane.setContent(gridPane);
        scene = new Scene(new Group());
        VBox vBox = new VBox();
        vBox.getChildren().addAll(scrollPane);
        scene.setRoot(vBox);

        // Buttons
        Button editButton = new Button("Felder editierbar machen");
        editButton.setOnAction(
                actionEvent -> {

                    controller.toggleGridEditable();

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
        saveButton.setOnAction(actionEvent -> {
        });

        BorderPane buttonBox = new BorderPane();
        buttonBox.setLeft(editButton);
        buttonBox.setRight(saveButton);
        vBox.getChildren().add(buttonBox);

        stage.setScene(scene);
        stage.show();

    }

    private GridPane buildRotomatGrid(RotomatModel rotomatModel) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(1);
        grid.setPadding(new Insets(25, 25, 25, 25));
        if (!rotomatModel.getShelves().isEmpty()) {
            for (int i = 0; i < rotomatModel.getShelves().get(0).size(); i++) {
                grid.add(new Label("Sektion " + (i + 1) + ""), i + 1, 0);
            }
        } else {
            return grid;
        }
        for (int i = 0; i < rotomatModel.getShelves().size(); i++) {
            grid.add(new Label("Regal " + (i + 1) + ""), 0, i + 1);
            ArrayList<String> shelf = rotomatModel.getShelves().get(i);
            for (int j = 0; j < shelf.size(); j++) {
                TextField currentTextField = new TextField(shelf.get(j));
                currentTextField.setEditable(rotomatModel.isModelIsEditable());
                grid.add(currentTextField, j + 1, i + 1);
            }

        }
        return grid;
    }

    @Override
    public void updateGridView(RotomatModel model) {
        gridPane = buildRotomatGrid(model);

    }

    @Override
    public void updateGridIsEditable(boolean isEditable) {
        this.isFieldEditable = isEditable;
    }
}
