package com.mynotes.config.filters;

import com.mynotes.services.JwtTokenService;
import com.mynotes.services.auth.MyCustomUserDetailService;
import com.mynotes.services.auth.MyCustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private MyCustomUserDetailService myCustomUserDetailService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // GET AUTHENTICATION HEADER:
        String authHeader = request.getHeader("Authorization");
        // SET JWT PROPERTY:
        String jwtToken = null;
        // SET USERNAME PROPERTY:
        String userEmail = null;

        // CHECK / VALIDATE AUTHORIZATION HEADER:
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            // KILL CODE EXECUTION HERE:
            return;
        }
        // END OF CHECK / VALIDATE AUTHORIZATION HEADER.

        // SET JWT TOKEN VALUE RETRIEVED FROM AUTHORIZATION HEADER:
        jwtToken = authHeader.substring(7);

        // EXTRACT THE USER EMAIL FROM JWT TOKEN:
        userEmail = jwtTokenService.extractUsername(jwtToken);

        // CHECK IF USER NOT NULL AND IS AUTHENTICATED:
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails =  myCustomUserDetailService.loadUserByUsername(userEmail);

            // VALIDATE TOKEN:
            if(jwtTokenService.validateToken(jwtToken, userDetails)){
                UsernamePasswordAuthenticationToken userAuthToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()

                        );
                // END OF USERNAME PASSWORD AUTH TOKEN OBJECT.
                userAuthToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // END OF SET USER AUTH TOKEN DETAILS OBJECT.

                SecurityContextHolder.getContext().setAuthentication(userAuthToken);
                // END OF SET SECURITY CONTEXT HOLDER AUTHENTICATION.

            }
            // END OF VALIDATE TOKEN.
        }
        // END OF CHECK IF USER NOT NULL AND IS AUTHENTICATED.

        // MOVE TO NEXT FILTER:
        filterChain.doFilter(request, response);
    }
    // END OF DO FILTER INTERNAL METHOD.
}
// END OF JWT AUTHENTICATION FILTER CLASS.
