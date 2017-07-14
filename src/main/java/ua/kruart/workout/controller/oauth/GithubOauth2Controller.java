package ua.kruart.workout.controller.oauth;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

/**
 * Handles requests for github oauth2 authorization
 *
 * @author kruart on 12.07.2017.
 */
@Controller
@RequestMapping("/oauth/github")
public class GithubOauth2Controller extends AbstractOauth2Controller {

    @Autowired
    @Qualifier(value = "oauth2GithubData")
    private Oauth2Data oauth2Data;

    /**
     * Performs redirect to github authorize url
     * more about: https://developer.github.com/apps/building-integrations/setting-up-and-registering-oauth-apps/
     */
    @RequestMapping("/authorize")
    public String authorize() {
        return "redirect:" + oauth2Data.getAuthorizeUrl() +
                "?client_id=" + oauth2Data.getClientId() +
                "&redirect_uri=" + oauth2Data.getRedirectUrl() +
                "&scope=" + oauth2Data.getScope() +
                "&state=" + oauth2Data.getState();
    }

    /**
     * This is the callback url(redirect-url) for the github(which we specify in 'authorize' function in params) through which
     * the github passes us the authorization code and then we requests access token from github for futher authorization our 'Client Application'
     */
    @RequestMapping("/callback")
    public String authenticate(@RequestParam String code, @RequestParam String state, HttpServletRequest request) {

        if(state.equals(oauth2Data.getState())) {
            String accessToken = getAccessToken(code);
            return authorizeOrRegister(getUser(accessToken), getEmail(accessToken));
        }
        return null;
    }

    /**
     * Calls the parent method to obtain the access token
     */
    private String getAccessToken(String code) {
        return getAccessToken(code, oauth2Data);
    }

    /**
     * Using an access token to receive username from github
     */
    private String getUser(String token) {
        UriComponentsBuilder builder = fromHttpUrl(oauth2Data.getUserUrl());
        ResponseEntity<JsonNode> tokenEntity = template.exchange(
                builder.build().encode().toUri(), HttpMethod.GET, new HttpEntity<>(createHeaderWithAccessToken(token)), JsonNode.class);
        return tokenEntity.getBody().get("login").asText();
    }

    /**
     * Using an access token to receive email from github
     */
    private String getEmail(String token) {
        UriComponentsBuilder builder = fromHttpUrl(oauth2Data.getEmailUrl());
        ResponseEntity<JsonNode> tokenEntity = template.exchange(
                builder.build().encode().toUri(), HttpMethod.GET, new HttpEntity<>(createHeaderWithAccessToken(token)), JsonNode.class);
        return tokenEntity.getBody().get(0).get("email").asText();
    }
}
