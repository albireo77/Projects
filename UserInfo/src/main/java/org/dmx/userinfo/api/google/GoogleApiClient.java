package org.dmx.userinfo.api.google;

import org.dmx.userinfo.api.ApiClient;
import org.springframework.beans.factory.annotation.Value;

public class GoogleApiClient extends ApiClient {

    @Value("${google.base.url}")
    private String baseUrl;

    public GoogleApiClient(String accessToken) {
        super(accessToken);
    }

    @Override
    protected String getUserUrl() {
        return baseUrl + "/userinfo";
    }
}
