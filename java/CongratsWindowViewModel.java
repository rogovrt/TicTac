import javafx.scene.Scene;
import javafx.stage.Stage;

public class CongratsWindowViewModel {
    private Stage stage;
    private Stage ticTacViewStage;
    private TicTacViewModel ticTacViewModel;

    public CongratsWindowViewModel(Stage stage, TicTacViewModel ticTacViewModel) {
        this.stage = stage;
        ticTacViewStage = ticTacViewModel.getMainStage();
        this.ticTacViewModel = ticTacViewModel;
    }

    public void actionOnNewGameButton() {
        initTicTacViewStage();
        stage.close();
    }

    private void initTicTacViewStage() {
        TicTacView ticTacView = new TicTacView(ticTacViewModel);
        Scene scene = new Scene(ticTacView, 640, 500);
        ticTacViewStage.setMinHeight(540);
        ticTacViewStage.setMaxHeight(540);
        ticTacViewStage.setMinWidth(680);
        ticTacViewStage.setMaxWidth(680);
        ticTacViewStage.setTitle("Fuck-Tic-Tac");
        ticTacViewStage.setScene(scene);
        ticTacViewStage.show();
    }
}
