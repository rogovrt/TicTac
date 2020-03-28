import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UncheckedIOException;

public class CongratsWindowView extends BorderPane {
    private int i;
    private Label title;
    private Image im;
    private ButtonBar bb;
    private CongratsWindowViewModel congratsWindowViewModel;
    private Label winner;

    public CongratsWindowView(int i, CongratsWindowViewModel congratsWindowViewModel){
        this.i = i;
        this.congratsWindowViewModel = congratsWindowViewModel;

        if (i != -1) {
            title = new Label("We had a winner! Congrats to ");
            title.setFont(new Font(24));
            winner = new Label();
            winner.setFont(new Font(24));
            Label l = new Label("!");
            l.setFont(new Font(24));
            HBox h = new HBox(title, winner, l);

            try (FileInputStream is = new FileInputStream("src/win.gif")) {
                im = new Image(is);
                ImageView iv = new ImageView(im);
                setCenter(iv);
            }
            catch (IOException e) {
                throw new UncheckedIOException(e);
            }

            bb = new ButtonBar();
            initButtonBar();

            setTop(h);
            setBottom(bb);
            setPadding(new Insets(4));
        }

        if (i == -1) {
            title = new Label("Sorry, guys. It's draw!");
            title.setFont(new Font(24));

            try (FileInputStream is = new FileInputStream("src/draw.gif")) {
                im = new Image(is);
                ImageView iv = new ImageView(im);
                setCenter(iv);
            }
            catch (IOException e) {
                throw new UncheckedIOException(e);
            }

            bb = new ButtonBar();
            initButtonBar();

            setTop(title);
            setBottom(bb);
            setPadding(new Insets(4));
        }
    }

    private void initButtonBar() {
        Button newGame = new Button("New Game");
        Button cancel = new Button("Exit");

        cancel.setOnMouseClicked(mouseEvent -> Platform.exit());
        newGame.setOnMouseClicked(mouseEvent -> congratsWindowViewModel.actionOnNewGameButton());

        bb.getButtons().addAll(newGame, cancel);
    }

    public void setWinner(String winner) {
        this.winner.setText(winner);
    }
}
