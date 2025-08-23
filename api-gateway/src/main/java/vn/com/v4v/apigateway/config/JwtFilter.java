package vn.com.v4v.apigateway.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    @Autowired
    private JwtHandler jwtHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader(AUTHORIZATION_HEADER);
        String jwtToken = null;
        List<String> roles = null;
        UserDetails userDetails = null;
        String username;
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        if(authHeader != null && authHeader.startsWith(BEARER_PREFIX)) {

            jwtToken = authHeader.substring(7);
        }
        if(jwtToken != null && jwtHandler.validateToken(jwtToken) && !jwtHandler.isRefreshToken(jwtToken)) {

            roles = jwtHandler.getRoles(jwtToken);
            roles.forEach(item -> authorities.add(new SimpleGrantedAuthority(item)));
            username = jwtHandler.getUsername(jwtToken);
            userDetails = new User(username, "", authorities);

            // Set system authentication
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
