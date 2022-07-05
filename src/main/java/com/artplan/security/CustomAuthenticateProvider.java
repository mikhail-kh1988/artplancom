package com.artplan.security;

import com.artplan.entity.User;
import com.artplan.exception.BadCredentialsException;
import com.artplan.exception.BlockedUserException;
import com.artplan.exception.NoUserException;
import com.artplan.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticateProvider implements AuthenticationProvider {

    private static final Integer COUNT_ATTEMPTS = 10;

    @Autowired
    private IUserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException, BadCredentialsException, BlockedUserException {
        String login = authentication.getName();
        String passw = authentication.getCredentials().toString();

        User user = userService.findUserByLogin(login);

        if (user != null & user.getPassword().equals(passw) & checkAttemptsUser(login)){
            userService.trueLogin(login);
            return new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword(), user.getAuthorities());
        }

        userService.updateCountAttempts(login);
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private boolean checkAttemptsUser(String login){
        User user = userService.findUserByLogin(login);
        int tempCount = user.getAttempts();

        if (tempCount < COUNT_ATTEMPTS){
            return true;
        }
        throw new BlockedUserException("");
    }

}
