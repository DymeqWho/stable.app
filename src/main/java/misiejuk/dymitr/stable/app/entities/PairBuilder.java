package misiejuk.dymitr.stable.app.entities;

public final class PairBuilder {

    private Long id;

    private String description;
    private Rider rider;
    private Horse horse;

    public PairBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public PairBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public PairBuilder setRider(Rider rider) {
        this.rider = rider;
        return this;
    }

    public PairBuilder setHorse(Horse horse) {
        this.horse = horse;
        return this;
    }

    public Pair build() {
        return new Pair(id, description, rider, horse);
    }
}
