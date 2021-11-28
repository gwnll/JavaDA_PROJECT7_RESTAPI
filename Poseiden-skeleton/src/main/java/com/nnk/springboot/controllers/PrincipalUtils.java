package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.security.Principal;

public class PrincipalUtils {

    /**
     * Get the username of the logged in user from the Principal object
     * Two possibilies : in app login or GitHub login
     * @param principal
     * @return the username of the logged in user
     */
    public static String getUsername(Principal principal) {
        if (principal instanceof User) {
            return ((User) principal).getUsername();
        } else if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken authToken = ((OAuth2AuthenticationToken) principal);
            if (authToken.isAuthenticated()) {
                return (String) ((DefaultOAuth2User) authToken.getPrincipal()).getAttributes().get("login");
            }
        }
        return "";
    }

}
