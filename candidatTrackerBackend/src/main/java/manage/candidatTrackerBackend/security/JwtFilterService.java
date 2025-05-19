package manage.candidatTrackerBackend.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import manage.candidatTrackerBackend.model.JwToken;
import manage.candidatTrackerBackend.model.User;
import manage.candidatTrackerBackend.services.implement.TokenService;
import manage.candidatTrackerBackend.services.interfaces.IUserService;

@Service
public class JwtFilterService extends OncePerRequestFilter {

    @Autowired private IUserService userService;
    @Autowired private JwtService jwtService;
    @Autowired private TokenService tokenService;
    
    @Autowired @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver exceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException{

        String username=null;
        String token=null;
        boolean isTokenExpired = true;
        JwToken dbToken=null;
        try {
            final String autorization=request.getHeader("Authorization");
            if(autorization!=null && autorization.startsWith("Bearer ")){
                token=autorization.substring(7);
                isTokenExpired = jwtService.isTokenExpired(token);
                dbToken=tokenService.findByValue(token);
                username=jwtService.extractUserName(token);
            }
            
            if(!isTokenExpired && username!=null && dbToken.getUser().getUsername().equals(username)  && SecurityContextHolder.getContext().getAuthentication()==null){
                User user = userService.loadUserByUserName(username);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            exceptionResolver.resolveException(request, response, null, e);
        }
        
    }
    
}
