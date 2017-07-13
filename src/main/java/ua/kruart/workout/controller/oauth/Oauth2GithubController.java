package ua.kruart.workout.controller.oauth;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
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
import static ua.kruart.workout.controller.oauth.GithubOauth2StaticData.*;
import static ua.kruart.workout.util.UserUtil.prepareForSaveOauth;

/**
 * Handles requests for github oauth2 authorization
 *
 * @author kruart on 12.07.2017.
 */
@Controller
@RequestMapping("/oauth/github/")
public class Oauth2GithubController {

    @Autowired
    private RestTemplate template;

    @Autowired
    private UserDetailsService serviceDetails;

    @Autowired
    private UserService service;

    /**
     * Performs redirect to github authorize url
     * more about: https://developer.github.com/apps/building-integrations/setting-up-and-registering-oauth-apps/
     */
    @RequestMapping("/authorize")
    public String authorize() {
        return "redirect:" + AUTHORIZE_URL +
                "?client_id=" + CLIENT_ID +
                "&redirect_uri=" + REDIRECT_URL +
                "&scope=" + SCOPE +
                "&state=" + STATE;
    }

    /**
     * This is the callback url(redirect-url) for the github(which we specify in 'authorize' function in params) through which
     * the github passes us the authorization code and then we requests access token from github for futher authorization our 'Client Application'
     */
    @RequestMapping("/callback")
    public String authenticate(@RequestParam String code, @RequestParam String state, HttpServletRequest request) {

        if(state.equals("workout_csrf_token_Dk38L9")) {
            String accessToken = getAccessToken(code);
            return authorizeOrRegister(getUser(accessToken), getEmail(accessToken));
        }
        return null;
    }

    /**
     * Gets Access Token from github
     */
    private String getAccessToken(String code) {
        UriComponentsBuilder builder = fromHttpUrl(ACCESS_TOKEN_URL)
                .queryParam("client_id", CLIENT_ID)
                .queryParam("client_secret", CLIENT_SECRET)
                .queryParam("code", code)
                .queryParam("redirect_uri", REDIRECT_URL)
                .queryParam("state", STATE);
        ResponseEntity<JsonNode> tokenEntity = template.postForEntity(builder.build().encode().toUri(), null, JsonNode.class);
        return tokenEntity.getBody().get("access_token").asText();
    }

    /**
     * Using an access token to receive username from github
     */
    private String getUser(String token) {
        UriComponentsBuilder builder = fromHttpUrl(USER_URL);
        ResponseEntity<JsonNode> tokenEntity = template.exchange(
                builder.build().encode().toUri(), HttpMethod.GET, new HttpEntity<>(createHeaderWithAccessToken(token)), JsonNode.class);
        return tokenEntity.getBody().get("login").asText();
    }

    /**
     * Using an access token to receive email from github
     */
    private String getEmail(String token) {
        UriComponentsBuilder builder = fromHttpUrl(EMAIL_URL);
        ResponseEntity<JsonNode> tokenEntity = template.exchange(
                builder.build().encode().toUri(), HttpMethod.GET, new HttpEntity<>(createHeaderWithAccessToken(token)), JsonNode.class);
        return tokenEntity.getBody().get(0).get("email").asText();
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
