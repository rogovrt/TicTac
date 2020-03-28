import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;

public class TicTacViewModel {
    private Image cross;
    private Image circle;
    private boolean flag;
    private ArrayList<Integer> control;
    private int count;
    private ArrayList<Integer> crs;
    private ArrayList<Integer> cir;
    private ArrayList<Integer> w;
    private SimpleIntegerProperty stat1;
    private SimpleIntegerProperty stat2;
    private SimpleStringProperty winner1;
    private SimpleStringProperty winner2;
    private Stage mainStage;

    public TicTacViewModel(Stage stage) {

        mainStage = stage;

        try (FileInputStream is = new FileInputStream("src/cross.png");) {
            cross = new Image(is, 160.0d, 160.0d, false, false);
        }
        catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        try (FileInputStream is1 = new FileInputStream("src/circle.png");) {
            circle = new Image(is1, 160.0d, 160.0d, false, false);
        }
        catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        flag = true;

        initControl();

        count = 0;

        crs = new ArrayList<Integer>(9);
        cir = new ArrayList<Integer>(9);

        w = new ArrayList<Integer>(15);
        for (int i = 0; i < 9; ++i)
            w.add(i);
        w.add(2); w.add(4); w.add(6); w.add(0); w.add(4); w.add(8);
        for (int i = 0; i < 3; ++i) {
            w.add(i); w.add(i + 3); w.add(i + 6);
        }

        winner1 = new SimpleStringProperty();
        winner2 = new SimpleStringProperty();

        initStat();
    }

    private void initStat() {
        stat1 = new SimpleIntegerProperty(0);
        stat2 = new SimpleIntegerProperty(0);
    }

    private void initControl() {
        control = new ArrayList<Integer>(9);
        for (int i = 0; i < 9; ++i)
            control.add(i, -1);
    }

    public void onClick(GridPane gameField, int ci, int ri) {
        int num = ri * 3 + ci;
        if (control.get(num) == -1) {
            count++;
            if (flag) {
                gameField.add(new ImageView(cross), ci, ri);
                control.set(num, 1);
                crs.add(num);
                flag = false;
            }
            else {
                gameField.add(new ImageView(circle), ci, ri);
                control.set(num, 0);
                cir.add(num);
                flag = true;
            }
            if (count >= 5)
                whoWin();
        }
    }

    private void clearData() {
        crs.clear();
        cir.clear();
        count = 0;
        flag = true;
        initControl();
    }

    private void whoWin() {
        for (int i = 0; i < 24; i += 3) {
            if (crs.containsAll(w.subList(i, i + 3))) {
                initEndOfParty(1);
            }
            if (cir.containsAll(w.subList(i, i + 3))) {
                initEndOfParty(0);
            }
        }
        if (count == 9)
            initEndOfParty(-1);
    }

    private void initEndOfParty(int c) {
        Stage primaryStage = new Stage();
        CongratsWindowView congratsWindowView = new CongratsWindowView(c, new CongratsWindowViewModel(primaryStage, this));
        if (c == -1) {
            stat1.set(stat1.getValue() + 1);
            stat2.set(stat2.getValue() + 1);
        }
        if (c == 0) {
            congratsWindowView.setWinner(getWinner1());
            stat2.set(stat2.getValue() + 3);
        }
        if (c == 1) {
            congratsWindowView.setWinner(getWinner2());
            stat1.set(stat1.getValue() + 3);
        }
        Scene scene = new Scene(congratsWindowView, 500, 400);
        primaryStage.setMinHeight(400);
        primaryStage.setMaxHeight(400);
        primaryStage.setMinWidth(500);
        primaryStage.setMaxWidth(500);
        primaryStage.setTitle("End");
        primaryStage.setScene(scene);
        primaryStage.show();
        mainStage.close();
        clearData();
    }

    public String getWinner1() {
        return winner1.get( );
    }

    public SimpleStringProperty winner1Property() {
        return winner1;
    }

    public String getWinner2() {
        return winner2.get( );
    }

    public SimpleStringProperty winner2Property() {
        return winner2;
    }

    public SimpleIntegerProperty stat1Property() {
        return stat1;
    }

    public SimpleIntegerProperty stat2Property() {
        return stat2;
    }

    public Stage getMainStage() {
        return mainStage;
    }
}