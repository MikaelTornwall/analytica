package analytica.ui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AddData {
    private TextArea textArea;
    private Button addButton;    
    private Parent addData;
    
    public AddData() {
        this.textArea = new TextArea();
        this.addButton = new Button("Add");        
        this.addData = this.createAddData();
    }
    
    public String getText() {
        return this.textArea.getText();
    }
    
    public void setText(String text) {
        this.textArea.setText(text);
    }
    
    public Button getAddButton() {
        return this.addButton;
    }
    
    public Parent getAddData() {
        return this.addData;
    }        
    
    public Parent createAddData() {
        VBox pane = new VBox();
        
        pane.getChildren().addAll(this.textArea, this.addButton);
        
        BorderPane layout = new BorderPane();        
        layout.setCenter(pane);
        
        return layout;
    }
}
