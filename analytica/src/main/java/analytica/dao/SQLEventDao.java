package analytica.dao;

import analytica.db.SQLDatabase;
import analytica.domain.Event;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mikael Törnwall
 */

public class SQLEventDao implements EventDao {
    
    private SQLDatabase database;    
    
    public SQLEventDao(SQLDatabase database) {
        this.database = database;        
    }
    
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
    
    public void delete(String name) {
        try (Connection connection = database.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM Event WHERE name = ?");            
            stmt.setString(1, name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }    
    
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
