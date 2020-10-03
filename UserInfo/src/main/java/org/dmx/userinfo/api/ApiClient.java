package org.dmx.userinfo.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

public abstract class ApiClient {

    private final RestTemplate restTemplate;

    public ApiClient(String accessToken) {
        restTemplate = new RestTemplate();
        if (accessToken != null) {
            restTemplate.getInterceptors().add(getBearerTokenInterceptor(accessToken));
        } else {
            restTemplate.getInterceptors().add(getNoTokenInterceptor());
        }
    }

    private ClientHttpRequestInterceptor getBearerTokenInterceptor(String accessToken) {
         return (request, bytes, execution) -> {
                    request.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
                    return execution.execute(request, bytes);
         };
    }

    private ClientHttpRequestInterceptor getNoTokenInterceptor() {
        return (request, bytes, execution) -> {
            throw new IllegalStateException("Can't access the API without an access token");
        };
    }

    public <T> T getUserInfo(Class<T> aClass) {
        return restTemplate.getForObject(getUserUrl(), aClass);
    }

    protected abstract String getUserUrl();
}
