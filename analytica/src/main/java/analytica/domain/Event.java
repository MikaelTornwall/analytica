package analytica.domain;

import java.util.Objects;

public class Event {
    private String name;
    private double price;
    private int participants;
    private int openedAccount;
    private int didNotOpenAccount;
    private int males;
    private int females;

    public Event(String name, double price, int participants, int openedAccount, int didNotOpenAccount, int males, int females) {
        this.name = name;
        this.price = price;
        this.participants = participants;
        this.openedAccount = openedAccount;
        this.didNotOpenAccount = didNotOpenAccount;
        this.males = males;
        this.females = females;
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
        return openedAccount;
    }

    public void setOpenedAccount(int openedAccount) {
        this.openedAccount = openedAccount;
    }

    public int getDidNotOpenAccount() {
        return didNotOpenAccount;
    }

    public void setDidNotOpenAccount(int didNotOpenAccount) {
        this.didNotOpenAccount = didNotOpenAccount;
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
        hash = 97 * hash + this.openedAccount;
        hash = 97 * hash + this.didNotOpenAccount;
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
        if (this.participants != other.participants) {
            return false;
        }
        if (this.openedAccount != other.openedAccount) {
            return false;
        }
        if (this.didNotOpenAccount != other.didNotOpenAccount) {
            return false;
        }
        if (this.males != other.males) {
            return false;
        }
        if (this.females != other.females) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }        
}
