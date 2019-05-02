package de.svemass.rotomat.view;

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

import static javafx.application.Application.launch;

public class View extends Application {
    private boolean isFieldEditable;
    private GridPane gridPane;


    public static void displayRotomat(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(createGrid(5, 5)); //TODO
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
        saveButton.setOnAction(actionEvent -> {
        });

        BorderPane buttonBox = new BorderPane();
        buttonBox.setLeft(editButton);
        buttonBox.setRight(saveButton);

        vBox.getChildren().add(buttonBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createGrid(int amountShelves, int amountCompartmentsPerShelf) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(1);
        grid.setPadding(new Insets(25, 25, 25, 25));
        for (int i = 0; i < amountShelves; i++) {
            grid.add(new Label("Fach " + (i + 1) + ""), 0, i + 1);
            for (int j = 0; j < amountCompartmentsPerShelf; j++) {
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
}
