package misiejuk.dymitr.stable.app.entities;

public final class RiderBuilder {
    private String name;
    private String description;

    public RiderBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public RiderBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public Rider build() {
        return new Rider(name, description);
    }
}
