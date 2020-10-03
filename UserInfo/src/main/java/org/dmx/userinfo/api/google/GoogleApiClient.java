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
    public String getUrl() {
        return baseUrl + "/userinfo";
    }
}
