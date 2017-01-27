package sample;
/**
 * Author Demondre Livingston
 * Version 1.01
 * I am very proud of myself for this accomplishment with little to no help at all
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {

    TextField row = new TextField();
    TextField column = new TextField();

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gridPane = new GridPane();
        Scanner keyboard = new Scanner(System.in);
        Rectangle[][] rectangles = new Rectangle[10][10];
        for (int i = 0; i < rectangles.length; i++) {
            for (int j = 0; j < rectangles[i].length; j++) {
                Rectangle rectangle = new Rectangle();
                rectangles[i][j] = rectangle;

            }

        }
        Label prompt1 = new Label();
        Label prompt2 = new Label();
        Button next = new Button("Next Gen");
        Button check = new Button("Check");
        Button set = new Button("set cells");
        Button reset = new Button("Reset");
        reset.setVisible(false);
        reset.setOnAction(event -> {
            set.setVisible(true);
            for (int i = 0; i < rectangles.length; i++) {
                for (int j = 0; j < rectangles[i].length; j++) {
                    if (rectangles[i][j].getFill() == Paint.valueOf("Green") || rectangles[i][j].getFill() == Paint.valueOf("Red")) {
                        rectangles[i][j].setFill(Paint.valueOf("Black"));
                    }

                }
            }
            reset.setVisible(false);
        });

        //checks purpose is to check if the cells should die or come to life in the next generation
        check.setOnAction(event -> {
            set.setVisible(false);

            for (int i = 0; i < rectangles.length; i++) {
                for (int j = 0; j < rectangles[i].length; j++) {
                    rectangles[i][j].setWidth(40);
                    if (rectangles[i][j].getFill() == Paint.valueOf("red")) {
                        rectangles[i][j].setFill(Paint.valueOf("black"));
                    }
                }
            }
            for (int i = 0; i < rectangles.length; i++) {//black means not alive in previous gen. red means died in last gen. Green means alive.
                for (int j = 0; j < rectangles[i].length; j++) {//for loop to check if the cell should die in the next gen
                    int count2 = 0;// keeps count of black rectangles being touched by green
                    int count = 0;//keeps count of how many green rectangles are touches each other
                    if (rectangles[i][j].getFill() == Paint.valueOf("Black") && i != 0 && j != 0 && rectangles[i - 1][j - 1].getFill() == Paint.valueOf("Green")) {
                        count2++;
                    }
                    if (rectangles[i][j].getFill() == Paint.valueOf("Black") && j != 0 && rectangles[i][j - 1].getFill() == Paint.valueOf("Green")) {
                        count2++;
                    }
                    if (rectangles[i][j].getFill() == Paint.valueOf("Black") && i != 9 && j != 0 && rectangles[i + 1][j - 1].getFill() == Paint.valueOf("Green")) {
                        count2++;
                    }
                    if (rectangles[i][j].getFill() == Paint.valueOf("Black") && i != 0 && rectangles[i - 1][j].getFill() == Paint.valueOf("Green")) {
                        count2++;
                    }
                    if (rectangles[i][j].getFill() == Paint.valueOf("Black") && i != 9 && rectangles[i + 1][j].getFill() == Paint.valueOf("Green")) {
                        count2++;
                    }
                    if (rectangles[i][j].getFill() == Paint.valueOf("Black") && i != 0 && j != 9 && rectangles[i - 1][j + 1].getFill() == Paint.valueOf("Green")) {
                        count2++;
                    }
                    if (rectangles[i][j].getFill() == Paint.valueOf("Black") && j != 9 && rectangles[i][j + 1].getFill() == Paint.valueOf("Green")) {
                        count2++;
                    }
                    if (rectangles[i][j].getFill() == Paint.valueOf("Black") && i != 9 && j != 9 && rectangles[i + 1][j + 1].getFill() == Paint.valueOf("Green")) {
                        count2++;
                    }
                    if (count2 > 2 && count2 < 4 && rectangles[i][j].getFill() == Paint.valueOf("Black")) {//sets the black squares to green if touched three green
                        rectangles[i][j].setWidth(40.0005);

                    }
                    if (rectangles[i][j].getFill() == Paint.valueOf("Green") && i > 0 && j > 0 && rectangles[i - 1][j - 1].getFill() == Paint.valueOf("Green")) {
                        count++;
                    }
                    if (rectangles[i][j].getFill() == Paint.valueOf("Green") && j > 0 && rectangles[i][j - 1].getFill() == Paint.valueOf("Green")) {
                        count++;
                    }
                    if (rectangles[i][j].getFill() == Paint.valueOf("Green") && i < 9 && j > 0 && rectangles[i + 1][j - 1].getFill() == Paint.valueOf("Green")) {
                        count++;
                    }
                    if (rectangles[i][j].getFill() == Paint.valueOf("Green") && i > 0 && rectangles[i - 1][j].getFill() == Paint.valueOf("Green")) {
                        count++;
                    }
                    if (rectangles[i][j].getFill() == Paint.valueOf("Green") && i < 9 && rectangles[i + 1][j].getFill() == Paint.valueOf("Green")) {
                        count++;
                    }
                    if (rectangles[i][j].getFill() == Paint.valueOf("Green") && j < 9 && i > 0 && rectangles[i - 1][j + 1].getFill() == Paint.valueOf("Green")) {
                        count++;
                    }
                    if (rectangles[i][j].getFill() == Paint.valueOf("Green") && j < 9 && rectangles[i][j + 1].getFill() == Paint.valueOf("Green")) {
                        count++;
                    }
                    if (rectangles[i][j].getFill() == Paint.valueOf("Green") && i < 9 && j < 9 && rectangles[i + 1][j + 1].getFill() == Paint.valueOf("Green")) {
                        count++;
                    }

                    if (count >= 4 && rectangles[i][j].getFill() == Paint.valueOf("Green")) {
                        rectangles[i][j].setWidth(40.002);
                    }
                    if (count < 2 && rectangles[i][j].getFill() == Paint.valueOf("Green")) {
                        rectangles[i][j].setWidth(40.002);
                    }

                }
            }
        });
        //set to create the live cells
        set.setOnAction(event -> {
            reset.setVisible(true);
            int finalColumn = Integer.parseInt(column.getText());
            int finalRow = Integer.parseInt(row.getText());
            rectangles[finalRow][finalColumn].setFill(Paint.valueOf("Green"));
            row.setText(null);
            column.setText(null);


        });
        //shows the result of the next generation e.g. who died and who lived
        next.setOnAction(event -> {
            for (int i = 0; i < rectangles.length; i++) {
                for (int j = 0; j < rectangles[i].length; j++) {
                    if (rectangles[i][j].getWidth() == 40.002) {
                        rectangles[i][j].setFill(Paint.valueOf("Red"));
                    }
                    if (rectangles[i][j].getWidth() == 40.0005) {
                        rectangles[i][j].setFill(Paint.valueOf("Green"));
                    }
                }
            }
        });

        prompt1.setText("Use the right and left box for row and column of life cells >>");
        prompt1.setVisible(true);
        //   prompt2.setText("Simulation Complete");

        for (int i = 0; i < rectangles.length; i++) {
            for (int j = 0; j < rectangles[i].length; j++) {
                rectangles[i][j].setFill(Paint.valueOf("Black"));//meaning it wasn't alive in the previous gen
                rectangles[i][j].setHeight(40);
                rectangles[i][j].setWidth(40);
                gridPane.add(rectangles[i][j], i, j);
            }

        }

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 50, 50, 30));
        vBox.setSpacing(10);
        vBox.getChildren().addAll(set, check, next, reset);
        gridPane.add(row, 10, 11);
        gridPane.add(column, 11, 11);
        BorderPane borderPane = new BorderPane();
        gridPane.setStyle("-fx-border-color: red");
        gridPane.setGridLinesVisible(true);
        primaryStage.setTitle("Game of LIFE");
        borderPane.setCenter(gridPane);
        borderPane.setBottom(prompt1);
        borderPane.setBottom(prompt2);
        borderPane.setRight(vBox);
        primaryStage.setScene(new Scene(borderPane, 700, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
