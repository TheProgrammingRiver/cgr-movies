package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {
    private final JWTUtils jwtUtils;
    private final MyUserDetailsService myUserDetailsService;

    /**
     * Injects dependencies to access resources
     * @param jwtUtils
     * @param myUserDetailsService
     */
    @Autowired
    public JWTRequestFilter(JWTUtils jwtUtils, MyUserDetailsService myUserDetailsService) {
        this.jwtUtils = jwtUtils;
        this.myUserDetailsService = myUserDetailsService;
    }

    /**
     * Extracts the JWT token from the request header.
     * @param request http request
     * @return The Token.
     */
    public String parseJwt(HttpServletRequest request){
        final String header = request.getHeader("Authorization");
        if (StringUtils.hasText(header) && header.startsWith("Bearer")){
            return header.substring(7);
        }
        return null;
    }

    /**
     * Checks and validates JWT, loads user details,
     * and sets the authentication token in the security context,
     * allowing protected resources to be accessed by authenticated users.
     *
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = parseJwt(request); // get the token from request
            if (jwt != null && jwtUtils.validateJwtToken(jwt)){
                String username = jwtUtils.getUsernameFromJwtToken(jwt);
                UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(username); // session information
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities()); //authenticates the user
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication); // grants access
            }
        } catch (Exception e){
            logger.error("unable to authenticate user.");
        }
        filterChain.doFilter(request, response);
    }
}
