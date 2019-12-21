package analytica.ui;

import java.util.List;
import java.util.ArrayList;
import analytica.domain.Event;
import analytica.service.EventService;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.geometry.Insets;


public class NewEvent {
    private EventService eventService;
    private Dashboard dashboard;
    private EventList eventList;
    private List<TextField> textFields;
    private List<Label> errorLabelList;
    private Label nameLabel;
    private Label participantsLabel;
    private Label priceLabel;
    private Label openedLabel;    
    private Label malesLabel;
    private Label femalesLabel;
    private Label errorLabel;    
    private TextField name;
    private TextField participants;
    private TextField price;
    private TextField opened;    
    private TextField males;
    private TextField females;       
    private Button addButton;    
    private Parent addData;
    
    public NewEvent(EventService eventService, EventList eventList, Dashboard dashboard) {
        this.eventService = eventService;
        this.eventList = eventList;
        this.dashboard = dashboard;
        this.textFields = new ArrayList<>();
        this.errorLabelList = new ArrayList<>();
        this.nameLabel = new Label("Event name");
        this.priceLabel = new Label("Price of the event");
        this.participantsLabel = new Label("Number of participants");
        this.openedLabel = new Label("Number of participants who opened an account");        
        this.malesLabel = new Label("Number of male participants");
        this.femalesLabel = new Label("Number of female participants");    
        this.errorLabel = new Label();        
        this.name = new TextField();
        this.price = new TextField();
        this.participants = new TextField();
        this.opened = new TextField();        
        this.males = new TextField();
        this.females = new TextField();
        this.addButton = new Button("Add event");        
        this.addData = this.createAddData();
        this.initTextFieldsList();        
    }        
    
    public void initTextFieldsList() {
        this.textFields.add(this.name);
        this.textFields.add(this.price);
        this.textFields.add(this.participants);
        this.textFields.add(this.opened);
        this.textFields.add(this.males);
        this.textFields.add(this.females);
    }           
    
    public String getNameInput() {
        return this.name.getText();
    }
    
    public void setName(String value) {
        this.name.setText(value);
    }
    
    public Integer getParticipantsInput() {
        return Integer.valueOf(this.participants.getText());
    }        
    
    public void setParticipants(String value) {
        this.participants.setText(value);
    }
    
    public Double getPriceInput() {
        return Double.valueOf(this.price.getText());
    }
    
    public void setPrice(String value) {
        this.price.setText(value);
    }
    
    public Integer getOpenedInput() {
        return Integer.valueOf(this.opened.getText());
    }
    
    public void setOpened(String value) {
        this.opened.setText(value);
    }
    
    public Integer getMalesInput() {
        return Integer.valueOf(this.males.getText());
    }
    
    public void setMales(String value) {
        this.males.setText(value);
    }
    
    public Integer getFemalesInput() {
        return Integer.valueOf(this.females.getText());
    }        
    
    public void setFemales(String value) {
        this.females.setText(value);
    }
    
    public Button getAddButton() {
        return this.addButton;
    }
    
    public Parent getAddData() {
        return this.addData;
    }        
    
    public boolean checkInputFormat() {
        if (getNameInput().length() > 20 || getNameInput().isEmpty()) {
            this.errorLabel.setText("Event name should me 1-20 characters long! ");
            return false;
        } else if (!checkNumericFields()) {
            return false;
        } else if ((getMalesInput() + getFemalesInput()) != getParticipantsInput()) {
            this.errorLabel.setText("Number of males and females should sum up to the total number of participants!");
            return false;
        } else if (getOpenedInput() > getParticipantsInput()) {
            this.errorLabel.setText("Please make sure that the number of opened accounts is not bigger than the number of participants!");
            return false;
        }
        
        return true;
    }
    
    public boolean checkNumericFields() {
        if (!this.textFields.get(1).getText().matches("[0-9]+|[0-9]+.[0-9]+")) {
            this.errorLabel.setText("Please enter numeric value for price (e.g 12.5)");
            return false;
        }
        for (int i = 2; i < this.textFields.size(); i++) {
            if (!this.textFields.get(i).getText().matches("[0-9]+")) {                
                this.errorLabel.setText("Please enter numeric value for a numeric field! Only positive integers are allowed");
                return false;
            } else if (Double.valueOf(this.textFields.get(i).getText()) > 10000) {
                this.errorLabel.setText("Please enter smaller value! ");
                return false;   
            }
        }
        return true;
    }
    
    public void addEvent() {
        this.errorLabel.setText("");    
        
        if (!this.checkInputFormat()) {
            return;
        }
        
        Event event = new Event(
                getNameInput(), 
                getPriceInput(), 
                getParticipantsInput(), 
                getOpenedInput(), 
                (getParticipantsInput() - getOpenedInput()), 
                getMalesInput(), 
                getFemalesInput());
        
        if (this.eventService.createEvent(event)) {
            for (TextField field : this.textFields) {
                field.setText("");                
            }
            this.eventList.updateList();
            this.dashboard.updateDashboard();
        } else {
            this.errorLabel.setText("Event with name '" + this.getNameInput() + "' already exists.");
        }
    }
    
    private Parent createAddData() {
        GridPane grid = new GridPane();
        grid.setVgap(5);
        grid.setHgap(5);       
                
        Text sceneTitle = new Text("Add new event");        
        sceneTitle.setFont(Font.font(20));                            
        
        grid.add(sceneTitle, 0, 0, 2, 1);        
        grid.add(nameLabel, 0, 1);
        grid.add(name, 1, 1);
        grid.add(priceLabel, 0, 2);
        grid.add(price, 1, 2);
        grid.add(participantsLabel, 0, 3);
        grid.add(participants, 1, 3);
        grid.add(openedLabel, 0, 4);
        grid.add(opened, 1, 4);        
        grid.add(malesLabel, 0, 5);
        grid.add(males, 1, 5);
        grid.add(femalesLabel, 0, 6);
        grid.add(females, 1, 6);
        grid.add(addButton, 0, 7);
        grid.add(errorLabel, 0, 8, 3, 1);
        
        BorderPane layout = new BorderPane();        
        layout.setCenter(grid);
        
        return layout;
    }
}
