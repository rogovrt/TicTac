import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class StartWindowView extends BorderPane {
    private Label title;
    private GridPane playersInfo;
    private ButtonBar bb;
    private StartWindowViewModel startWindowViewModel;

    public StartWindowView(StartWindowViewModel startWindowViewModel) {
        title = new Label("Enter player's nicknames\nUse maximum 10 symbols");
        playersInfo = new GridPane( );
        bb = new ButtonBar( );
        this.startWindowViewModel = startWindowViewModel;

        initPlayersInfo();
        initButtonBar();
        setUserNames();

        setTop(title);
        setCenter(playersInfo);
        setBottom(bb);
        setPadding(new Insets(4));
    }

    private void initPlayersInfo() {
        playersInfo.setPadding(new Insets(4));
        playersInfo.setHgap(20);
        playersInfo.setVgap(10);

        Label player1 = new Label("Player1");
        Label player2 = new Label("Player2");
        TextField player1Tf = new TextField("Player1");
        TextField player2Tf = new TextField("Player2");


        playersInfo.add(player1, 0, 0);
        playersInfo.add(player2, 0, 1);
        playersInfo.add(player1Tf, 1, 0);
        playersInfo.add(player2Tf, 1, 1);
    }

    private void initButtonBar() {
        Button play = new Button("Play");
        Button cancel = new Button("Cancel");

        cancel.setOnMouseClicked(mouseEvent -> startWindowViewModel.actionOnCancelButton( ));
        play.setOnMouseClicked(new EventHandler<MouseEvent>( ) {
            @Override
            public void handle(MouseEvent mouseEvent) {
                startWindowViewModel.actionOnPlayButton();
            }
        });
        bb.getButtons( ).addAll(play, cancel);
    }

    private void setUserNames() {
        ObservableList<Node> tf = playersInfo.getChildren();
        TextField tf1 = (TextField) tf.get(2);
        TextField tf2 = (TextField) tf.get(3);
        ObservableList<Node> l = startWindowViewModel.getTicTacView().getStatField().getChildren();
        Label l1 = (Label) l.get(1);
        Label l2 = (Label) l.get(2);
        tf1.textProperty().bindBidirectional(l1.textProperty());
        tf2.textProperty().bindBidirectional(l2.textProperty());
    }
}
