package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.security.Principal;

@Slf4j
public class PrincipalUtils {

    /**
     * Get the username of the logged in user from the Principal object
     * Two possibilies : in app login or GitHub login
     * @param principal
     * @return the username of the logged in user
     */
    public static String getUsername(Principal principal, @AuthenticationPrincipal User user) {
        if (principal instanceof UsernamePasswordAuthenticationToken) {
            return user.getUsername();
        } else if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken authToken = ((OAuth2AuthenticationToken) principal);
            if (authToken.isAuthenticated()) {
                return (String) (authToken.getPrincipal()).getAttributes().get("login");
            }
        }
        return "Utilisateur anonyme";
    }

}
