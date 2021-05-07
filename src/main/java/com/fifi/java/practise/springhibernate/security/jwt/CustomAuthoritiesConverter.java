package com.fifi.java.practise.springhibernate.security.jwt;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CustomAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    // extract authorities from "scope", "https://example.com/role", "https://example.com/group", and "permissions" claims.
    private static final Map<String, String> CLAIMS_TO_AUTHORITY_PREFIX_MAP = new HashMap<String, String>() {{
        put("scope", "SCOPE_");
        put("http://schemas.microsoft.com/ws/2008/06/identity/claims/role/role", "ROLE_");
        //put("https://example.com/group", "GROUP_");
        put("permissions", "PERMISSION_");
    }};

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
    	
    	Collection collection = CLAIMS_TO_AUTHORITY_PREFIX_MAP.entrySet().stream()
                .map(entry -> getAuthorities(jwt, entry.getKey(), entry.getValue()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    	
//    	System.out.println(collection);
    	
        return collection;
    }

    private Collection<GrantedAuthority> getAuthorities(Jwt jwt, String authorityClaimName, String authorityPrefix) {
        Object authorities = jwt.getClaim(authorityClaimName);
        if (authorities instanceof String) {
            if (StringUtils.hasText((String) authorities)) {
                List<String> claims = Arrays.asList(((String) authorities).split(" "));
                return claims.stream()
                    .map(claim -> new SimpleGrantedAuthority(authorityPrefix + claim))
                    .collect(Collectors.toList());
            } else {
                return Collections.emptyList();
            }
        } else if (authorities instanceof Collection) {
            Collection<String> authoritiesCollection = (Collection<String>) authorities;
            return authoritiesCollection.stream()
                .map(authority -> new SimpleGrantedAuthority(authorityPrefix + authority))
                .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}