import Contracts.Planet;
import Contracts.Planets;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import javax.xml.ws.WebServiceException;

import static io.restassured.RestAssured.*;

class SwapiRestClient {
    private static SwapiRestClient instance = null;

    // private constructor restricted to this class itself
    private SwapiRestClient()
    {
    }

    // static method to create instance of SwapiRestClient class
    static synchronized SwapiRestClient instance()
    {
        // To ensure only one instance is created
        if (instance == null)
        {
            instance = new SwapiRestClient();
        }
        return instance;
    }

    Planet getPlanet(String id)
    {
        String uri = new SwapiUriBuilder().withPath(SubPath.PLANETS.toString()).withId(id).build();
        return getResponseBody(uri).as(Planet.class);
    }

    Planets getPlanets()
    {
        String uri = new SwapiUriBuilder().withPath(SubPath.PLANETS.toString()).build();
        return getResponseBody(uri).as(Planets.class);
    }

    Planets getPlanets(String pageNumber, String searchQuery)
    {
        SwapiUriBuilder swapiUriBuilder = new SwapiUriBuilder().withPath(SubPath.PLANETS.toString());
        if (pageNumber != null)
        {
            swapiUriBuilder.withPageNumber(pageNumber);
        }
        if (searchQuery != null)
        {
            swapiUriBuilder.withSearchQuery(searchQuery);
        }
        String uri = swapiUriBuilder.build();
        return getResponseBody(uri).as(Planets.class);
    }

    private ResponseBody getResponseBody(String uri) {
        ResponseBody responseBody;
        Response response = get(uri);
        if (response.getStatusCode() != 200) {
            throw new WebServiceException(Integer.toString(response.getStatusCode()));
        }
        else {
            responseBody = response.body();
        }
        return responseBody;
    }
}
