package analytica.ui;

import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Insets;


public class NewEvent {
    private Label nameLabel;
    private Label participantsLabel;
    private Label priceLabel;
    private Label openedLabel;
    private Label notOpenedLabel;
    private Label malesLabel;
    private Label femalesLabel;
    private TextField name;
    private TextField participants;
    private TextField price;
    private TextField opened;
    private TextField notOpened;
    private TextField males;
    private TextField females;       
    private Button addButton;    
    private Parent addData;
    
    public NewEvent() {
        this.nameLabel = new Label("Event name");
        this.participantsLabel = new Label("Number of participants");
        this.priceLabel = new Label("Price of the event");
        this.openedLabel = new Label("Number of participants who opened an account");
        this.notOpenedLabel = new Label("Number of participants who did not open an account");
        this.malesLabel = new Label("Number of male participants");
        this.femalesLabel = new Label("Number of female participants");        
        this.name = new TextField();
        this.participants = new TextField();
        this.price = new TextField();
        this.opened = new TextField();
        this.notOpened = new TextField();
        this.males = new TextField();
        this.females = new TextField();
        this.addButton = new Button("Add");        
        this.addData = this.createAddData();
    }        
    
    public Button getAddButton() {
        return this.addButton;
    }
    
    public Parent getAddData() {
        return this.addData;
    }        
    
    public Parent createAddData() {
        GridPane grid = new GridPane();
        grid.setVgap(5);
        grid.setHgap(5);
        HBox namePane = new HBox();
        HBox participantsPane = new HBox();
        HBox pricePane = new HBox();
        HBox openedPane = new HBox();
        HBox notOpenedPane = new HBox();
        HBox malesPane = new HBox();
        HBox femalesPane = new HBox();
                
        Text sceneTitle = new Text("Add new event");
        sceneTitle.setFont(Font.font(20));
        namePane.getChildren().addAll(this.nameLabel, this.name);
        participantsPane.getChildren().addAll(this.participantsLabel, this.participants);
        pricePane.getChildren().addAll(this.priceLabel, this.price);                        
        openedPane.getChildren().addAll(this.openedLabel, this.opened);                        
        notOpenedPane.getChildren().addAll(this.notOpenedLabel, this.notOpened);                        
        malesPane.getChildren().addAll(this.malesLabel, this.males);                        
        femalesPane.getChildren().addAll(this.femalesLabel, this.females);                        
        
        grid.add(sceneTitle, 0, 0, 2, 1);
        grid.add(nameLabel, 0, 1);
        grid.add(name, 1, 1);
        grid.add(participantsLabel, 0, 2);
        grid.add(participants, 1, 2);
        grid.add(priceLabel, 0, 3);
        grid.add(price, 1, 3);
        grid.add(openedLabel, 0, 4);
        grid.add(opened, 1, 4);
        grid.add(notOpenedLabel, 0, 5);
        grid.add(notOpened, 1, 5);
        grid.add(malesLabel, 0, 6);
        grid.add(males, 1, 6);
        grid.add(femalesLabel, 0, 7);
        grid.add(females, 1, 7);
        
        BorderPane layout = new BorderPane();        
        layout.setCenter(grid);
        
        return layout;
    }
}
