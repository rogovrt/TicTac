import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import javafx.event.EventHandler;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.management.PlatformLoggingMXBean;
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

    public TicTacViewModel() throws IOException {
        FileInputStream is = new FileInputStream("E:\\work\\tic-tac\\out\\production\\tic-tac\\cross.png");
        cross = new Image(is, 160.0d, 160.0d, false, false);

        FileInputStream is1 = new FileInputStream("E:\\work\\tic-tac\\out\\production\\tic-tac\\circle.png");
        circle = new Image(is1, 160.0d, 160.0d, false, false);

        flag = true;

        control = new ArrayList<Integer>(9);
        for (int i = 0; i < 9; ++i)
            control.add(i, -1);

        count = 0;

        crs = new ArrayList<Integer>(9);
        cir = new ArrayList<Integer>(9);

        w = new ArrayList<Integer>(15);
        for (int i = 0; i < 9; ++i) {
            w.add(i);
        }
        w.add(2); w.add(4); w.add(6); w.add(0); w.add(4); w.add(8);
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

    private void whoWin() {
        for (int i = 0; i < 15; i += 3) {
            if (crs.containsAll(w.subList(i, i + 3))) {
                System.out.println("crs win");
                Platform.exit();
            }
            if (cir.containsAll(w.subList(i, i + 3))) {
                System.out.println("cir win");
                Platform.exit();
            }
        }
    }
}
