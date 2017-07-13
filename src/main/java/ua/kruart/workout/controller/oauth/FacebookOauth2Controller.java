package ua.kruart.workout.controller.oauth;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ua.kruart.workout.service.UserService;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;
import static ua.kruart.workout.util.UserUtil.prepareForSaveOauth;

/**
 * Handles requests for facebook oauth2 authorization
 *
 * @author kruart on 13.07.2017.
 */
@Controller
@RequestMapping("/oauth/facebook/")
public class FacebookOauth2Controller {

    @Autowired
    private RestTemplate template;

    @Autowired
    private UserDetailsService serviceDetails;

    @Autowired
    private UserService service;

    @Autowired
    @Qualifier(value = "oauth2FacebookData")
    private Oauth2Data oauth2Data;

    /**
     * Performs redirect to facebook authorize url
     * more about: https://developers.facebook.com/docs/facebook-login/manually-build-a-login-flow
     * check: https://developers.facebook.com/tools/explorer
     */
    @RequestMapping("/authorize")
    public String authorize() {
        return "redirect:" + oauth2Data.getAuthorizeUrl() +
                "?client_id=" + oauth2Data.getClientId() +
                "&redirect_uri=" + oauth2Data.getRedirectUrl() +
                "&scope=" + oauth2Data.getScope() +
                "&state=" + oauth2Data.getState() +
                "&response_type=code";
    }

    /**
     * This is the callback url(redirect-url) for the facebook(which we specify in 'authorize' function in params) through which
     * the facebook passes us the authorization code and then we requests access token from facebook for futher authorization our 'Client Application'
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
     * Gets Access Token from facebook
     */
    private String getAccessToken(String code) {
        UriComponentsBuilder builder = fromHttpUrl(oauth2Data.getAccesTokenUrl())
                .queryParam("client_id", oauth2Data.getClientId())
                .queryParam("client_secret", oauth2Data.getClientSecret())
                .queryParam("code", code)
                .queryParam("redirect_uri", oauth2Data.getRedirectUrl())
                .queryParam("state", oauth2Data.getState());
        ResponseEntity<JsonNode> tokenEntity = template.postForEntity(builder.build().encode().toUri(), null, JsonNode.class);
        return tokenEntity.getBody().get("access_token").asText();
    }

    /**
     * Using an access token to receive username from facebook
     */
    private String getUser(String token) {
        UriComponentsBuilder builder = fromHttpUrl(oauth2Data.getUserUrl());
        ResponseEntity<JsonNode> tokenEntity = template.exchange(
                builder.build().encode().toUri(), HttpMethod.GET, new HttpEntity<>(createHeaderWithAccessToken(token)), JsonNode.class);
        return tokenEntity.getBody().get("name").asText();
    }

    /**
     * Using an access token to receive email from facebook
     * NOTE: If user used the phone number when registering, then the mail graph will be null ...
     */
    private String getEmail(String token) {
        UriComponentsBuilder builder = fromHttpUrl(oauth2Data.getEmailUrl());
        ResponseEntity<JsonNode> tokenEntity = template.exchange(
                builder.build().encode().toUri(), HttpMethod.GET, new HttpEntity<>(createHeaderWithAccessToken(token)), JsonNode.class);
        return tokenEntity.getBody().get("email").asText();
    }

    /**
     * Creates and returns http header with Access Token inside
     */
    private HttpHeaders createHeaderWithAccessToken(String token) {
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", "Bearer " + token);
        return header;
    }

    /**
     * Authorizes a user or registers a new user, and then authorizes
     */
    private String authorizeOrRegister(String userName, String email) {
        UserDetails userDetails = null;
        try {
            userDetails = serviceDetails.loadUserByUsername(email);
        }
        catch(NoResultException e) {
            service.save(prepareForSaveOauth(userName, email));
            userDetails = serviceDetails.loadUserByUsername(email);
        }
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
        return "redirect:/";

    }
}
