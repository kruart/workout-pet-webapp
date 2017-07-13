package ua.kruart.workout.controller.oauth;

/**
 * Contains the data required for github oauth authorization
 *
 * @author  kruart on 12.07.2017.
 */
public class Oauth2Data {
//    protected static final String AUTHORIZE_URL = "http://github.com/login/oauth/authorize";
//    protected static final String ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";
//    protected static final String USER_URL = "https://api.github.com/user";
//    protected static final String EMAIL_URL = "https://api.github.com/user/emails";
//    protected static final String CLIENT_ID = "5ef6bbf3012559df545b";
//    protected static final String CLIENT_SECRET = "35321128b121f92bf2cee2166d962358402d2fb7";
//    protected static final String REDIRECT_URL = "http://localhost:8080/oauth/github/callback";
//    protected static final String STATE = "workout_csrf_token_Dk38L9";
//    protected static final String SCOPE = "user:email";


    private String authorizeUrl;
    private String accesTokenUrl;
    private String userUrl;
    private String emailUrl;
    private String clientId;
    private String clientSecret;
    private String redirectUrl;
    private String state;
    private String scope;

    public String getAuthorizeUrl() {
        return authorizeUrl;
    }

    public void setAuthorizeUrl(String authorizeUrl) {
        this.authorizeUrl = authorizeUrl;
    }

    public String getAccesTokenUrl() {
        return accesTokenUrl;
    }

    public void setAccesTokenUrl(String accesTokenUrl) {
        this.accesTokenUrl = accesTokenUrl;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

    public String getEmailUrl() {
        return emailUrl;
    }

    public void setEmailUrl(String emailUrl) {
        this.emailUrl = emailUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
