package ua.kruart.workout.controller.oauth;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ua.kruart.workout.service.UserService;

import javax.persistence.NoResultException;

import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;
import static ua.kruart.workout.util.UserUtil.prepareForSaveOauth;

/**
 * @author kruart on 14.07.2017.
 */
public class AbstractOauth2Controller {

    @Autowired
    RestTemplate template;

    @Autowired
    private UserDetailsService serviceDetails;

    @Autowired
    private UserService service;

    /**
     * Gets Access Token from 'Resource Provider'
     */
    String getAccessToken(String code, Oauth2Data oauth2Data) {
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
     * Creates and returns http header with Access Token inside
     */
    HttpHeaders createHeaderWithAccessToken(String token) {
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", "Bearer " + token);
        return header;
    }

    /**
     * Authorizes a user or registers a new user, and then authorizes
     */
    String authorizeOrRegister(String userName, String email) {
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
