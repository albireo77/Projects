package org.dmx.oauth.web;

import org.dmx.oauth.api.ApiClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserInfoController {

    private final ApiClient apiClient;

    public UserInfoController(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @GetMapping
    public String userInfo() {
        return apiClient.getUserInfo(String.class);
    }
}
