package ua.kruart.workout.controller.oauth;

/**
 * Contains the data required for github oauth authorization
 *
 * @author  kruart on 12.07.2017.
 */
public class GithubOauth2StaticData {
    protected static final String AUTHORIZE_URL = "http://github.com/login/oauth/authorize";
    protected static final String ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";
    protected static final String USER_URL = "https://api.github.com/user";
    protected static final String EMAIL_URL = "https://api.github.com/user/emails";
    protected static final String CLIENT_ID = "5ef6bbf3012559df545b";
    protected static final String CLIENT_SECRET = "35321128b121f92bf2cee2166d962358402d2fb7";
    protected static final String REDIRECT_URL = "http://localhost:8080/oauth/github/callback";
    protected static final String STATE = "workout_csrf_token_Dk38L9";
    protected static final String SCOPE = "user:email";
}
