import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;

public class SwapiUriBuilder {
    private final String SWAPI_URL = "https://swapi.co/api/";
    private final String SEARCH_PARAM = "search";
    private final String PAGE_PARAM = "page";
    private final String FORMAT_PARAM = "format";

    private URIBuilder builder;

    {
        try {
            builder = new URIBuilder(SWAPI_URL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    SwapiUriBuilder withPath(String path)
    {
        builder.setPath(appendSegmentToPath(builder.getPath(), path + "/"));
        return this;
    }

    SwapiUriBuilder withId(String id)
    {
        builder.setPath(appendSegmentToPath(builder.getPath(), id + "/"));
        return this;
    }

    SwapiUriBuilder withSearchQuery(String searchQuery)
    {
        builder.addParameter(SEARCH_PARAM, searchQuery);
        return this;
    }

    public SwapiUriBuilder withFormat(String format)
    {
        builder.addParameter(FORMAT_PARAM, format);
        return this;
    }

    SwapiUriBuilder withPageNumber(String pageNumber)
    {
        builder.addParameter(PAGE_PARAM, pageNumber);
        return this;
    }

    String build() {
        String uri = null;
        try {
            uri = builder.build().toString();
            System.out.println(uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return uri;
    }

    private String appendSegmentToPath(String path, String segment) {
        if (path == null || path.isEmpty()) {
            path = "/";
        }

        if (path.charAt(path.length() - 1) == '/' || segment.startsWith("/")) {
            return path + segment;
        }

        return path + "/" + segment;
    }
}