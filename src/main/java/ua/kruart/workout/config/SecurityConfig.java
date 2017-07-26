package ua.kruart.workout.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import ua.kruart.workout.controller.handler.UsernameRememberingAuthenticationFailureHandler;
import ua.kruart.workout.controller.oauth.Oauth2Data;
import ua.kruart.workout.util.PasswordUtil;

/**
 * Contains configuration for working with security
 *
 * Created by kruart on 25.07.2017.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userService")
    private UserDetailsService userDetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/register/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/**").hasRole("USER")
                .and()
                .formLogin()
                .loginProcessingUrl("/performLogin")
                .usernameParameter("wUsername")
                .passwordParameter("wPassword")
                .loginPage("/login")
                .failureHandler(customFailureHandler())
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .csrf();
    }

    @Bean
    public AuthenticationFailureHandler customFailureHandler() {
        UsernameRememberingAuthenticationFailureHandler failureHandler = new UsernameRememberingAuthenticationFailureHandler();
        failureHandler.setRedirectTarget("/login");
        return failureHandler;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PasswordUtil passwordUtil(BCryptPasswordEncoder bCryptPasswordEncoder) {
        PasswordUtil passwordUtil = new PasswordUtil();
        passwordUtil.setBCryptPasswordEncoder(bCryptPasswordEncoder);
        return passwordUtil;
    }

    @Bean
    public Oauth2Data oauth2GithubData() {
        Oauth2Data oauth2GithubData = new Oauth2Data();
        oauth2GithubData.setAuthorizeUrl("http://github.com/login/oauth/authorize");
        oauth2GithubData.setAccesTokenUrl("https://github.com/login/oauth/access_token");
        oauth2GithubData.setUserUrl("https://api.github.com/user");
        oauth2GithubData.setEmailUrl("https://api.github.com/user/emails");
        oauth2GithubData.setClientId("5ef6bbf3012559df545b");
        oauth2GithubData.setClientSecret("35321128b121f92bf2cee2166d962358402d2fb7");
        oauth2GithubData.setRedirectUrl("http://localhost:8080/oauth/github/callback");
        oauth2GithubData.setScope("user:email");
        oauth2GithubData.setState("workout_csrf_token_Dk38L9_Git");
        return oauth2GithubData;
    }

    @Bean
    public Oauth2Data oauth2FacebookData() {
        Oauth2Data oauth2FacebookData = new Oauth2Data();
        oauth2FacebookData.setAuthorizeUrl("https://www.facebook.com/dialog/oauth");
        oauth2FacebookData.setAccesTokenUrl("https://graph.facebook.com/oauth/access_token");
        oauth2FacebookData.setUserUrl("https://graph.facebook.com/v2.9/me?fields=name");
        oauth2FacebookData.setEmailUrl("https://graph.facebook.com/v2.9/me?fields=email");
        oauth2FacebookData.setClientId("1747338582230976");
        oauth2FacebookData.setClientSecret("e392b1f9db8896028fa370da02585a5d");
        oauth2FacebookData.setRedirectUrl("http://localhost:8080/oauth/facebook/callback");
        oauth2FacebookData.setScope("public_profile,email");
        oauth2FacebookData.setState("workout_csrf_token_Dk38L9_Face");
        return oauth2FacebookData;
    }
}
