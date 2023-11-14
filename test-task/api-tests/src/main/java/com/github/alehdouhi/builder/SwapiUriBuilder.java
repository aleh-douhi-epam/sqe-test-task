package com.github.alehdouhi.builder;

import io.restassured.config.EncoderConfig;
import io.restassured.internal.http.URIBuilder;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

public class SwapiUriBuilder {
    private final URI SWAPI_BASE_URL = URI.create("https://swapi.dev/api/");
    private final String SEARCH_PARAM = "search";
    private final String PAGE_PARAM = "page";
    private final String FORMAT_PARAM = "format";

    private final URIBuilder builder;

    {
        builder = new URIBuilder(SWAPI_BASE_URL, false, new EncoderConfig());
    }

    public SwapiUriBuilder withPath(String path) {
        try {
            builder.setPath(path + "/");
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return this;
    }

    public SwapiUriBuilder withId(String id) {
        try {
            builder.setPath(builder.toURI().getPath() + id + "/");
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return this;
    }

    public SwapiUriBuilder withSearchQuery(String searchQuery) {
        try {
            builder.addQueryParams(new HashMap<>() {
                {
                    put(SEARCH_PARAM, searchQuery);
                }
            });
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return this;
    }

    public SwapiUriBuilder withFormat(String format) {
        try {
            builder.addQueryParams(new HashMap<>() {
                {
                    put(FORMAT_PARAM, format);
                }
            });
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return this;
    }

    public SwapiUriBuilder withPageNumber(String pageNumber) {
        try {
            builder.addQueryParams(new HashMap<>() {
                {
                    put(PAGE_PARAM, pageNumber);
                }
            });
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return this;
    }

    public String build() {
        String uri;
        uri = builder.toString();
        System.out.println(uri);
        return uri;
    }
}