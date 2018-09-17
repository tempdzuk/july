package com.test.julyOld.endpoint;


import com.test.julyOld.handler.SecuritySuccessHandler;
import com.test.julyOld.service.UserService;
import com.test.julyOld.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Marta Ginosyan
 */

@Controller
@RequestMapping(value = "")
@PreAuthorize("permitAll()")
public class AuthController {

    @Autowired
    PersistentTokenRepository persistentTokenRepository;

    @Autowired
    UserService userDetailsService;

    /**
     * Resolves authorization and redirects to eligible page. If user is not authorized navigates to home page.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String base(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        if (authentication != null && authentication.isAuthenticated()) return
                "redirect:" + SecuritySuccessHandler.resolveTargetURL(authentication, Constants.PATH_BASE);
        return "home";
    }

    /**
     * Resolves authorization and redirects to eligible page. If user is not authorized navigates to home page.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        if (authentication != null && authentication.isAuthenticated()) return
                "redirect:" + SecuritySuccessHandler.resolveTargetURL(authentication, Constants.PATH_BASE);
        return "home";
    }

    /**
     * Resolves authorization and redirects to eligible page. If user is not authorized navigates to login page.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) return
                "redirect:" + SecuritySuccessHandler.resolveTargetURL(authentication, Constants.PATH_BASE);
        return "login";
    }

    @RequestMapping(value = "/access-denied", method = RequestMethod.GET)
    public String accessDenied() {
        return "access-denied";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpServletRequest request, Authentication authentication) throws ServletException {
        if (authentication != null) {
            persistentTokenRepository.removeUserTokens(authentication.getName());
        }
        request.logout();
        return "redirect:/login";
    }

}