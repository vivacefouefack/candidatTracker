package manage.candidatTrackerBackend.controller.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import manage.candidatTrackerBackend.controller.interfaces.IUserController;
import manage.candidatTrackerBackend.dto.AuthResponseDto;
import manage.candidatTrackerBackend.dto.AuthenticationDto;
import manage.candidatTrackerBackend.model.User;
import manage.candidatTrackerBackend.security.JwtService;
import manage.candidatTrackerBackend.services.interfaces.IUserService;

@RestController
@RequestMapping("/api/auth")
public class UserControllerImp  implements IUserController{

    @Autowired private IUserService userService;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtService jwtService;

    @Override
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthenticationDto authenticationDto) {
       try {
            final Authentication authenticate =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationDto.getUsername(), authenticationDto.getPassword())
            );
            if(authenticate.isAuthenticated()){
                User currentUser=(User) authenticate.getPrincipal();

                AuthResponseDto response=new AuthResponseDto();
                response.setUser(userService.convertToUserDto(currentUser));
                response.setCandidates(userService.convertToCandidatureDtoList(currentUser.getCandidate()));
                response.setToken(jwtService.generateJwt(authenticationDto.getUsername()));

                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return null;
    }

    @Override
    public ResponseEntity<Void> register(@RequestBody AuthenticationDto authenticationDto) {
        try {
            userService.register(authenticationDto.getUsername(), authenticationDto.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    public ResponseEntity<Void> logout(){
        try {
            jwtService.logout();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    public ResponseEntity<String> test(){
        return ResponseEntity.status(HttpStatus.CREATED).body("je suis un déveleppeur");
    }
    
}
