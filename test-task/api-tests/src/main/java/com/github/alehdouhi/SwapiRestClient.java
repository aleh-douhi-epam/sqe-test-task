package com.github.alehdouhi;

import com.github.alehdouhi.builder.SwapiUriBuilder;
import com.github.alehdouhi.model.Planet;
import com.github.alehdouhi.model.Planets;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import jakarta.xml.ws.WebServiceException;
import static io.restassured.RestAssured.get;

public class SwapiRestClient {
    private static volatile SwapiRestClient instance = null;

    // private constructor restricted to this class itself
    private SwapiRestClient() {
    }

    // static method to create instance of SwapiRestClient class
    public static SwapiRestClient instance() {
        // To ensure only one instance is created
        if (instance == null) {
            synchronized (SwapiRestClient.class) {
                if (instance == null) {
                    instance = new SwapiRestClient();
                }
            }
        }
        return instance;
    }

    public Planet getPlanet(String id) {
        String uri = new SwapiUriBuilder().withPath(SubPath.PLANETS.toString()).withId(id).build();
        return getResponseBody(uri).as(Planet.class);
    }

    public Planets getPlanets() {
        String uri = new SwapiUriBuilder().withPath(SubPath.PLANETS.toString()).build();
        return getResponseBody(uri).as(Planets.class);
    }

    public Planets getPlanets(String pageNumber, String searchQuery) {
        SwapiUriBuilder swapiUriBuilder = new SwapiUriBuilder().withPath(SubPath.PLANETS.toString());
        if (pageNumber != null) {
            swapiUriBuilder.withPageNumber(pageNumber);
        }
        if (searchQuery != null) {
            swapiUriBuilder.withSearchQuery(searchQuery);
        }
        String uri = swapiUriBuilder.build();
        return getResponseBody(uri).as(Planets.class);
    }

    private ResponseBody getResponseBody(String uri) {
        Response response = get(uri);
        ResponseBody responseBody;
        if (response.getStatusCode() != 200) {
            throw new WebServiceException(Integer.toString(response.getStatusCode()));
        }
        else {
            responseBody = response.body();
        }
        return responseBody;
    }
}