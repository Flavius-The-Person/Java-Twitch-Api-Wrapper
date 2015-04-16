package api.resources;

import api.TwitchResponse;
import api.models.Root;
import api.models.Token;
import http.HttpResponse;

import java.io.IOException;

public class RootResource extends AbstractResource {

    public RootResource(String baseUrl, String apiVersion) {
        super(baseUrl, apiVersion);
    }

    public TwitchResponse<Token> get() throws IOException {
        String url = String.format("%s/", getBaseUrl());

        TwitchResponse<Root> container = requestGet(url, HttpResponse.HTTP_OK, Root.class);

        // Create object with list rather than the container class
        TwitchResponse<Token> response = new TwitchResponse<Token>(
                container.getStatusCode(),
                container.getStatusText(),
                container.getErrorMessage());

        if (!container.hasError()) {
            response.setObject(container.getObject().getToken());
        }

        return response;
    }
}
