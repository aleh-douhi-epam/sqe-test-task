import java.util.ArrayList;

class PlanetContractBuilder {
    private Planet planet;

    PlanetContractBuilder() {
        this.planet = new Planet();
    }

    PlanetContractBuilder withName(String name) {
        planet.name = name;
        return this;
    }

    PlanetContractBuilder withRotationPeriod(String rotationPeriod) {
        planet.rotation_period = rotationPeriod;
        return this;
    }

    PlanetContractBuilder withOrbitalPeriod(String orbitalPeriod) {
        planet.orbital_period = orbitalPeriod;
        return this;
    }

    PlanetContractBuilder withDiameter(String diameter) {
        planet.diameter = diameter;
        return this;
    }

    PlanetContractBuilder withClimate(String climate) {
        planet.climate = climate;
        return this;
    }

    PlanetContractBuilder withGravity(String gravity) {
        planet.gravity = gravity;
        return this;
    }

    PlanetContractBuilder withTerrain(String terrain) {
        planet.terrain = terrain;
        return this;
    }

    PlanetContractBuilder withSurfaceWater(String surfaceWater) {
        planet.surface_water = surfaceWater;
        return this;
    }

    PlanetContractBuilder withPopulation(String population) {
        planet.population = population;
        return this;
    }

    PlanetContractBuilder withResidents(ArrayList<String> residents) {
        planet.residents = residents;
        return this;
    }

    PlanetContractBuilder withFilms(ArrayList<String> films) {
        planet.films = films;
        return this;
    }

    PlanetContractBuilder withCreated(String created) {
        planet.created = created;
        return this;
    }

    PlanetContractBuilder withEdited(String edited) {
        planet.edited = edited;
        return this;
    }

    PlanetContractBuilder withUrl(String url) {
        planet.url = url;
        return this;
    }

    Planet build() {
        return this.planet;
    }
}