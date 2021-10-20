package de.neuefische.devquiz.security.filter;

import de.neuefische.devquiz.security.service.JWTUtilsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.List;

@Slf4j
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private JWTUtilsService jwtUtils;

    public JwtAuthFilter(JWTUtilsService jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getAuthToken(request);

        try {
            if(token != null && !token.isBlank()){
                String username = jwtUtils.extractUsername(token);
                setSecurityContext(username);
            }
        } catch (Exception e) {
            throw new AccessDeniedException("invalid token. access denied.", e.getMessage(), "");
        }

        log.info("received token: " + token);
        filterChain.doFilter(request, response);
    }

    private String getAuthToken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if(authHeader != null){
            return authHeader.replace("Bearer ", "").trim(); //replace with RegEx
        }
        return null;
    }

    private void setSecurityContext(String username){
        UsernamePasswordAuthenticationToken authToken =new UsernamePasswordAuthenticationToken(username, "", List.of());
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}
