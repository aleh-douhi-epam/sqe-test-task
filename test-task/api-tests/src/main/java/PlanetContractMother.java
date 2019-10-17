import java.util.ArrayList;

class PlanetContractMother {
    private final static ArrayList<String> alderaanResidents = new ArrayList<String>() {
        {
            add(new SwapiUriBuilder().withPath(SubPath.PEOPLE.toString()).withId("5").build());
            add(new SwapiUriBuilder().withPath(SubPath.PEOPLE.toString()).withId("68").build());
            add(new SwapiUriBuilder().withPath(SubPath.PEOPLE.toString()).withId("81").build());
        }
    };

    private final static ArrayList<String> alderaanFilms = new ArrayList<String>() {
        {
            add(new SwapiUriBuilder().withPath(SubPath.FILMS.toString()).withId("6").build());
            add(new SwapiUriBuilder().withPath(SubPath.FILMS.toString()).withId("1").build());
        }
    };

    private final static String alderaanPlanetUrl = new SwapiUriBuilder().withPath(SubPath.PLANETS.toString()).withId("2").build();

    static PlanetContractBuilder getAlderaan() {
        return getPlanet()
                .withName("Alderaan")
                .withRotationPeriod("24")
                .withOrbitalPeriod("364")
                .withDiameter("12500")
                .withClimate("temperate")
                .withGravity("1 standard")
                .withTerrain("grasslands, mountains")
                .withSurfaceWater("40")
                .withPopulation("2000000000")
                .withResidents(alderaanResidents)
                .withFilms(alderaanFilms)
                .withCreated("2014-12-10T11:35:48.479000Z")
                .withEdited("2014-12-20T20:58:18.420000Z")
                .withUrl(alderaanPlanetUrl);
    }

    private static PlanetContractBuilder getPlanet() {
        return new PlanetContractBuilder();
    }
}