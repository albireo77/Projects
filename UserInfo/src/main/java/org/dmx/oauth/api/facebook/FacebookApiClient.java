package org.dmx.oauth.api.facebook;

import org.dmx.oauth.api.ApiClient;
import org.springframework.beans.factory.annotation.Value;

public class FacebookApiClient extends ApiClient {

    @Value("${facebook.base.url}")
    private String baseUrl;

    public FacebookApiClient(String accessToken) {
        super(accessToken);
    }

    @Override
    public String getUrl() {
        return baseUrl + "/me";
    }
}
