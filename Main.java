import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        TicTacView ticTacView = new TicTacView(new TicTacViewModel());
        System.out.println(System.getProperty("java.class.path"));
        Scene scene = new Scene(ticTacView, 640, 500);
        primaryStage.setMinHeight(540);
        primaryStage.setMaxHeight(540);
        primaryStage.setMinWidth(680);
        primaryStage.setMaxWidth(680);
        primaryStage.setTitle("Fuck-Tic-Tac");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
