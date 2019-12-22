package analytica.domain;

import java.util.Objects;

/** 
 * Event class
 * 
 * @author Mikael Törnwall
 */

public class Event {
    private int id;
    private String name;
    private double price;
    private int participants;
    private int opened;
    private int notopened;
    private int males;
    private int females;

    public Event(int id, String name, double price, int participants, int opened, int notopened, int males, int females) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.participants = participants;
        this.opened = opened;
        this.notopened = notopened;
        this.males = males;
        this.females = females;
    }
    
    public Event(String name, double price, int participants, int openedAccount, int didNotOpenAccount, int males, int females) {
        this(-1, name, price, participants, openedAccount, didNotOpenAccount, males, females);
    }

    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public int getOpenedAccount() {
        return opened;
    }

    public void setOpenedAccount(int opened) {
        this.opened = opened;
    }

    public int getDidNotOpenAccount() {
        return notopened;
    }

    public void setDidNotOpenAccount(int notopened) {
        this.notopened = notopened;
    }

    public int getMales() {
        return males;
    }

    public void setMales(int males) {
        this.males = males;
    }

    public int getFemales() {
        return females;
    }

    public void setFemales(int females) {
        this.females = females;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 97 * hash + this.participants;
        hash = 97 * hash + this.opened;
        hash = 97 * hash + this.notopened;
        hash = 97 * hash + this.males;
        hash = 97 * hash + this.females;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Event other = (Event) obj;
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }        
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }        
}
