package analytica.ui;

import java.util.List;
import analytica.domain.Event;
import analytica.service.EventService;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * EventList class is responsible for generating and managing the event list component
 * 
 * @author Mikael Törnwall
 */

public class EventList {
    
    private final EventService eventService;
    private List<Event> events;
    private Parent eventList;    
    
    public EventList(EventService eventService) {
        this.eventService = eventService;
        this.events = eventService.getEvents();
        this.eventList = createEventList();
    }
    
    public Parent getEventList() {
        return this.eventList;
    }
    
    /**
     * Method gets the updated event information and recreates the event list
     * 
     */
    
    public void updateList() {
        this.events = this.eventService.getEvents();
        this.eventList = createEventList();
    }        
    
    private Parent createEventList() {
        Text title = new Text("Events");        
        title.setFont(Font.font(20));
        GridPane pane = new GridPane();
        pane.add(title, 0, 0, 2, 1);
        
        Text name = new Text("Event name");
        Text price = new Text("Price");
        Text participants = new Text("Participants");
        Text opened = new Text("Opened account");
        Text notOpened = new Text("Didn't open account");
        Text males = new Text("Males");
        Text females = new Text("Females");        
        
        name.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        price.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        participants.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        opened.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        notOpened.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        males.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        females.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        pane.add(name, 0, 1);
        pane.add(price, 1, 1);
        pane.add(participants, 2, 1);
        pane.add(opened, 3, 1);
        pane.add(notOpened, 4, 1);
        pane.add(males, 5, 1);
        pane.add(females, 6, 1);
        
        if (this.events.size() <= 20) {
            for (int i = 0; i < events.size(); i++) {
                pane.add(new Text(events.get(i).getName()), 0, i + 2);
                pane.add(new Text(Double.toString(events.get(i).getPrice())), 1, i + 2);            
                pane.add(new Text(Integer.toString(events.get(i).getParticipants())), 2, i + 2);            
                pane.add(new Text(Integer.toString(events.get(i).getOpenedAccount())), 3, i + 2);            
                pane.add(new Text(Integer.toString(events.get(i).getDidNotOpenAccount())), 4, i + 2);            
                pane.add(new Text(Integer.toString(events.get(i).getMales())), 5, i + 2);            
                pane.add(new Text(Integer.toString(events.get(i).getFemales())), 6, i + 2);            
            }
        } else {
            for (int i = this.events.size() - 20; i < this.events.size(); i++) {
                pane.add(new Text(events.get(i).getName()), 0, i + 2);
                pane.add(new Text(Double.toString(events.get(i).getPrice())), 1, i + 2);            
                pane.add(new Text(Integer.toString(events.get(i).getParticipants())), 2, i + 2);            
                pane.add(new Text(Integer.toString(events.get(i).getOpenedAccount())), 3, i + 2);            
                pane.add(new Text(Integer.toString(events.get(i).getDidNotOpenAccount())), 4, i + 2);            
                pane.add(new Text(Integer.toString(events.get(i).getMales())), 5, i + 2);            
                pane.add(new Text(Integer.toString(events.get(i).getFemales())), 6, i + 2);            
            }
        }
                
        pane.setVgap(10);
        pane.setHgap(15);
        
        return pane;
    }   
}
