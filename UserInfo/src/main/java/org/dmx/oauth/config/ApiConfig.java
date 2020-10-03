package org.dmx.oauth.config;

import org.dmx.oauth.api.ApiClient;
import org.dmx.oauth.api.facebook.FacebookApiClient;
import org.dmx.oauth.api.github.GitHubApiClient;
import org.dmx.oauth.api.google.GoogleApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class ApiConfig {

    @Autowired
    OAuth2AuthorizedClientService clientService;

    @Bean
    @RequestScope
    public ApiClient apiClient() {
        OAuth2AuthorizedClient client = getOAuth2AuthorizedClient();
        if (client != null) {
            String token = client.getAccessToken().getTokenValue();
            switch (client.getClientRegistration().getRegistrationId()) {
                case "github":
                    return new GitHubApiClient(token);
                case "facebook":
                    return new FacebookApiClient(token);
                case "google":
                    return new GoogleApiClient(token);
            }
        }
        throw new IllegalStateException("Not authorized");
    }

    private OAuth2AuthorizedClient getOAuth2AuthorizedClient() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getClass().isAssignableFrom(OAuth2AuthenticationToken.class)) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            return clientService.loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId(), oauthToken.getName());
        }
        return null;
    }
}
