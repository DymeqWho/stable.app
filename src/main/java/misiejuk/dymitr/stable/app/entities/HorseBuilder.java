package misiejuk.dymitr.stable.app.entities;

public final class HorseBuilder {
    private String name;
    private String description;

    public HorseBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public HorseBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public Horse build() {
        return new Horse(name, description);
    }
}
