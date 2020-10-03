package org.dmx.userinfo.api.facebook;

import org.dmx.userinfo.api.ApiClient;
import org.springframework.beans.factory.annotation.Value;

public class FacebookApiClient extends ApiClient {

    @Value("${facebook.base.url}")
    private String baseUrl;

    public FacebookApiClient(String accessToken) {
        super(accessToken);
    }

    @Override
    protected String getUserUrl() {
        return baseUrl + "/me";
    }
}
