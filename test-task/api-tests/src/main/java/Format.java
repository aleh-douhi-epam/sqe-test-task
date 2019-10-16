public enum Format {
    API,
    JSON,
    WOOKIEE;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
