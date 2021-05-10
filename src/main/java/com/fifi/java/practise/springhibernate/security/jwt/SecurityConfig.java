package com.fifi.java.practise.springhibernate.security.jwt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.util.*;
import java.util.stream.Collectors;
/**
 * Configures our application with Spring Security to restrict access to our API endpoints.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuer;

    @Autowired
    CustomAuthoritiesConverter customAuthoritiesConverter;

    @Autowired
    AudienceValidator audienceValidator;  
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        /*
        This is where we configure the security required for our endpoints and setup our app to serve as
        an OAuth2 Resource Server, using JWT validation.
        */
        http.authorizeRequests()
                .mvcMatchers("/api/comments").permitAll()
                .mvcMatchers("/api/posts").permitAll()
                .mvcMatchers("/api/comment").authenticated()
                .mvcMatchers("/api/private").authenticated()
                .and().cors()
                .and().oauth2ResourceServer().jwt()
                .decoder(jwtDecoder())
                .jwtAuthenticationConverter(jwtAuthenticationConverter());        
    	
        //allow post request
        http.csrf().disable();
        
        //allow iframe - allow H2 console
        http.headers().frameOptions().disable();
    }

    
    JwtDecoder jwtDecoder() {

        NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder)
                JwtDecoders.fromOidcIssuerLocation(issuer);

        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);

        jwtDecoder.setJwtValidator(withAudience);

        return jwtDecoder;
    }
    
 
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
        authenticationConverter.setJwtGrantedAuthoritiesConverter(customAuthoritiesConverter);
        System.out.println(authenticationConverter);
        return authenticationConverter;
    }    

    
}
