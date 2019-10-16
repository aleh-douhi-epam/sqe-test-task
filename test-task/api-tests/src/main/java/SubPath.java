public enum SubPath {
    PEOPLE,
    PLANETS,
    FILMS,
    SPECIES,
    VEHICLES,
    STARSHIPS;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
