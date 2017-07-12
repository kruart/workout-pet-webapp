package ua.kruart.workout.controller.oauth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

import static ua.kruart.workout.controller.oauth.GithubOauth2StaticData.*;

/**
 * Handles requests for github oauth2 authorization
 *
 * @author kruart on 12.07.2017.
 */
@Controller
@RequestMapping("/oauth/github/")
public class Oauth2GithubController {

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
     * the github passes us the authorization code and then we requests access token from github for futher authorize our 'Client Application'
     */
    @RequestMapping("/callback")
    public String authorize(@RequestParam String code, @RequestParam String state, HttpServletRequest request) {

        System.out.println(request.getRequestURL() + "?" + request.getQueryString());
        return null;
    }
}
