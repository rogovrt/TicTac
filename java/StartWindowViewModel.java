import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartWindowViewModel {
    private Stage stage;
    private TicTacView ticTacView;
    private Stage ticTacViewStage;

    public StartWindowViewModel(Stage stage) {
        this.stage = stage;
        ticTacViewStage = new Stage();
        ticTacView = new TicTacView(new TicTacViewModel(ticTacViewStage));
        initTicTacViewStage();
    }

    private void initTicTacViewStage() {
        Scene scene = new Scene(ticTacView, 640, 500);
        ticTacViewStage.setMinHeight(540);
        ticTacViewStage.setMaxHeight(540);
        ticTacViewStage.setMinWidth(680);
        ticTacViewStage.setMaxWidth(680);
        ticTacViewStage.setTitle("Fuck-Tic-Tac");
        ticTacViewStage.setScene(scene);
    }

    public void actionOnCancelButton() {
        Platform.exit();
    }

    public void actionOnPlayButton() {
        stage.close();
        ticTacViewStage.show();
    }

    public TicTacView getTicTacView() {
        return ticTacView;
    }
}
