package org.dmx.userinfo.api.github;

import org.dmx.userinfo.api.ApiClient;
import org.springframework.beans.factory.annotation.Value;

public class GitHubApiClient extends ApiClient {

    @Value("${github.base.url}")
    private String baseUrl;

    public GitHubApiClient(String accessToken) {
        super(accessToken);
    }

    @Override
    protected String getUserUrl() {
        return baseUrl + "/user";
    }
}
