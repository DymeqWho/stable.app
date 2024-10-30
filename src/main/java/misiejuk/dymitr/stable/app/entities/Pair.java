package misiejuk.dymitr.stable.app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "pair")
public class Pair {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String description;

    @ManyToOne
    @JoinColumn(name = "horse_name", referencedColumnName = "name")
    private Rider rider;

    @ManyToOne
    @JoinColumn(name = "rider_name", referencedColumnName = "name")
    private Horse horse;

    public Pair() {
    }

    public Pair(Long id, String description, Rider rider, Horse horse) {
        this.id = id;
        this.description = description;
        this.rider = rider;
        this.horse = horse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public Horse getHorse() {
        return horse;
    }

    public void setHorse(Horse horse) {
        this.horse = horse;
    }
}
