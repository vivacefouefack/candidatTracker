package manage.candidatTrackerBackend.controller.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import manage.candidatTrackerBackend.dto.AuthResponseDto;
import manage.candidatTrackerBackend.dto.AuthenticationDto;

public interface IUserController {
    
    @PostMapping("/login")
    ResponseEntity<AuthResponseDto> login(@RequestBody AuthenticationDto authenticationDto);


    @PostMapping("/register")
    ResponseEntity<Void> register(@RequestBody AuthenticationDto authenticationDto);


    @GetMapping("/test")
    ResponseEntity<String> test();
}
