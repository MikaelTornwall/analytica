package analytica.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import analytica.db.SQLDatabase;
import analytica.domain.Event;

/**
 * SQLEventDao class is responsible for the Event related database operations and queries
 * 
 * @author Mikael Törnwall
 */

public class SQLEventDao implements EventDao {
    
    private SQLDatabase database;    
    
    public SQLEventDao(SQLDatabase database) {
        this.database = database;        
    }
    
    /**
     * Method creates a new event instance in the database    
     * 
     * @param event object as a parameter
     */    
    
    public void create(Event event) {
        try (Connection connection = database.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Event (name, participants, price, opened, notopened, males, females) VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, event.getName());
            stmt.setInt(2, event.getParticipants());            
            stmt.setDouble(3, event.getPrice());            
            stmt.setInt(4, event.getOpenedAccount());            
            stmt.setInt(5, event.getDidNotOpenAccount());            
            stmt.setInt(6, event.getMales());            
            stmt.setInt(7, event.getFemales());            
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Method fetches an event from the database if it matches the event name
     * 
     * @param name string as a parameter     
     * @return an event object if name finds database match, null otherwise
     */
    
    public Event getByName(String name) {        
        Event event = null;
        
        try (Connection connection = this.database.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Event WHERE name = ?");
            stmt.setString(1, name);
            ResultSet res = stmt.executeQuery();            
            
            if (res.next()) {
                event = new Event(res.getInt("id"), res.getString("name"), res.getDouble("price"), res.getInt("participants"), res.getInt("opened"), res.getInt("notopened"), res.getInt("males"), res.getInt("females"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        if (event == null) {
            return null;
        }
        
        return event;
    }        
    
    /**
     * Method fetches an all events from the database
     * 
     * @return a list of Event objects
     */
    
    public List<Event> getAll() {
        List<Event> events = new ArrayList<>();
        try (Connection connection = this.database.getConnection();
                ResultSet res = connection.prepareStatement("SELECT * FROM Event").executeQuery()) {
            while (res.next()) {
                events.add(new Event(res.getInt("id"), res.getString("name"), res.getDouble("price"), res.getInt("participants"), res.getInt("opened"), res.getInt("notopened"), res.getInt("males"), res.getInt("females")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
                
        return events;
    } 
    
    /**
     * Method fetches the number of participants for each event from the database
     * 
     * @return list of integer values
     */
    
    public List<Integer> getParticipantsList() {
        List<Integer> participants = new ArrayList<>();
        try (Connection connection = this.database.getConnection();
                ResultSet res = connection.prepareStatement("SELECT participants FROM Event").executeQuery()) {
            while (res.next()) {
                participants.add(res.getInt("participants"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
                
        return participants;
    }
    
    /**
     * Method fetches prices for each event from the database
     * 
     * @return list of double values
     */
    
    public List<Double> getPricesList() {
        List<Double> prices = new ArrayList<>();
        try (Connection connection = this.database.getConnection();
                ResultSet res = connection.prepareStatement("SELECT price FROM Event").executeQuery()) {
            while (res.next()) {
                prices.add(res.getDouble("price"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
                
        return prices;
    }
    
    /**
     * Method fetches the number of opened accounts for each event from the database
     * 
     * @return list of integer values
     */
    
    public List<Integer> getOpenedList() {
        List<Integer> opened = new ArrayList<>();
        try (Connection connection = this.database.getConnection();
                ResultSet res = connection.prepareStatement("SELECT opened FROM Event").executeQuery()) {
            while (res.next()) {
                opened.add(res.getInt("opened"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
                
        return opened;
    }
    
    /**
     * Method fetches the number of not opened accounts for each event from the database
     * 
     * @return list of integer values
     */
    
    public List<Integer> getNotOpenedList() {
        List<Integer> notOpened = new ArrayList<>();
        try (Connection connection = this.database.getConnection();
                ResultSet res = connection.prepareStatement("SELECT notopened FROM Event").executeQuery()) {
            while (res.next()) {
                notOpened.add(res.getInt("notopened"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return notOpened;
    }
    
    /**
     * Method fetches the number of males in each event from the database
     * 
     * @return list of integer values
     */
    
    public List<Integer> getMalesList() {
        List<Integer> males = new ArrayList<>();
        try (Connection connection = this.database.getConnection();
                ResultSet res = connection.prepareStatement("SELECT males FROM Event").executeQuery()) {
            while (res.next()) {
                males.add(res.getInt("males"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return males;
    }
    
    /**
     * Method fetches the number of females in each event from the database
     * 
     * @return list of integer values
     */
    
    public List<Integer> getFemalesList() {
        List<Integer> females = new ArrayList<>();
        try (Connection connection = this.database.getConnection();
                ResultSet res = connection.prepareStatement("SELECT females FROM Event").executeQuery()) {
            while (res.next()) {
                females.add(res.getInt("females"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return females;
    }
}
