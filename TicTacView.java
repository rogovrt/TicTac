import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.ImageCursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class TicTacView extends HBox {

    private GridPane gameField;
    private GridPane statField;
    private TicTacViewModel ticTacViewModel;

    public TicTacView(TicTacViewModel ticTacViewModel) {
        gameField = new GridPane();
        statField = new GridPane();
        this.ticTacViewModel = ticTacViewModel;

        initGameField();
        initStatField();

        //setHgrow(gameField, Priority.ALWAYS);
        getChildren().addAll(gameField, statField);
    }

    private void initStatField() { //покруче??
        statField.setPadding(new Insets(5));
        statField.setHgap(20);
        statField.setVgap(10);

        Label title = new Label("Statistic");
        title.setContentDisplay(ContentDisplay.CENTER);
        Label p1 = new Label("Player1");
        Label p2 = new Label("Player2");
        Label res1 = new Label("0");
        res1.setContentDisplay(ContentDisplay.CENTER);
        Label res2 = new Label("0");
        res2.setContentDisplay(ContentDisplay.CENTER);

        statField.add(title, 0, 0);
        statField.add(p1, 0, 1);
        statField.add(p2, 1, 1);
        statField.add(res1, 0, 2);
        statField.add(res2, 1, 2);

        GridPane.setColumnSpan(title, 2);

    }

    private void initGameField() {
        gameField.setPadding(new Insets(9));

        Rectangle r00 = initRectangle(0, 0);
        gameField.add(r00, 0, 0);
        r00.setOnMouseClicked(mouseEvent -> {
            ticTacViewModel.onClick(gameField, 0, 0);
        });

        Rectangle r01 = initRectangle(160, 0);
        gameField.add(r01, 0, 1);
        r01.setOnMouseClicked(mouseEvent -> {
            ticTacViewModel.onClick(gameField, 0, 1);
        });

        Rectangle r02 = initRectangle(320, 0);
        gameField.add(r02, 0, 2);
        r02.setOnMouseClicked(mouseEvent -> {
            ticTacViewModel.onClick(gameField, 0, 2);
        });

        Rectangle r10 = initRectangle(0, 160);
        gameField.add(r10, 1, 0);
        r10.setOnMouseClicked(mouseEvent -> {
            ticTacViewModel.onClick(gameField, 1, 0);
        });

        Rectangle r11 = initRectangle(160, 160);
        gameField.add(r11, 1, 1);
        r11.setOnMouseClicked(mouseEvent -> {
            ticTacViewModel.onClick(gameField, 1, 1);
        });

        Rectangle r12 = initRectangle(320, 160);
        gameField.add(r12, 1, 2);
        r12.setOnMouseClicked(mouseEvent -> {
            ticTacViewModel.onClick(gameField, 1, 2);
        });

        Rectangle r20 = initRectangle(0, 320);
        gameField.add(r20, 2, 0);
        r20.setOnMouseClicked(mouseEvent -> {
            ticTacViewModel.onClick(gameField, 2, 0);
        });

        Rectangle r21 = initRectangle(160, 320);
        gameField.add(r21, 2, 1);
        r21.setOnMouseClicked(mouseEvent -> {
            ticTacViewModel.onClick(gameField, 2, 1);
        });

        Rectangle r22 = initRectangle(320, 320);
        gameField.add(r22, 2, 2);
        r22.setOnMouseClicked(mouseEvent -> {
            ticTacViewModel.onClick(gameField, 2, 2);
        });

        gameField.setGridLinesVisible(true);
    }

    private Rectangle initRectangle(double x, double y) {
        Rectangle r = new Rectangle(160, 160, Color.WHITE);
        r.setX(x);
        r.setY(y);
        return r;
    }
}
