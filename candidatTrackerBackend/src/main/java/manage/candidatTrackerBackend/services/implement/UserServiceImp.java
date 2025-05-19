package manage.candidatTrackerBackend.services.implement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import manage.candidatTrackerBackend.dto.CandidateDto;
import manage.candidatTrackerBackend.dto.UserDto;
import manage.candidatTrackerBackend.model.Candidate;
import manage.candidatTrackerBackend.model.User;
import manage.candidatTrackerBackend.repository.UserRepository;
import manage.candidatTrackerBackend.services.interfaces.IUserService;

@Service
public class UserServiceImp implements IUserService, UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username).orElseThrow(
            () -> new  UsernameNotFoundException("not found"));
    }

    @Override
    public UserDto register(String username, String password) {
        User user = new User();
        user.setUserName(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("USER");
        user = userRepository.save(user);
        return new UserDto(user.getId(), user.getUsername(), user.getRole());
    }

    @Override
    public UserDto login(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUserName(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return new UserDto(user.getId(), user.getUsername(), user.getRole());
            }
        }
        return null;
    }

    @Override
    public UserDto updateProfile(int userId, String username, String newPassword) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUserName(username);
            user.setPassword(passwordEncoder.encode(newPassword));
            user = userRepository.save(user);
            return new UserDto(user.getId(), user.getUsername(), user.getRole());
        }
        return null;
    }

    @Override
    public List<Candidate> getMyCandidates(int userId) {
        return userRepository.findById(userId)
                .map(User::getCandidate)
                .orElse(List.of());
    }

    @Override
    public User loadUserByUserName(String username) {
        return loadUserByUsername(username);
    }


    @Override
    public UserDto convertToUserDto(User user) {
        if (user == null) return null;
        return new UserDto(
            user.getId(),
            user.getUsername(),
            user.getRole()
        );
    }

    @Override
    public List<CandidateDto> convertToCandidatureDtoList(List<Candidate> candidates) {
        if (candidates == null) return List.of();

        return candidates.stream()
            .map(c -> new CandidateDto(
                c.getId(),
                c.getCompanyName(),
                c.getJobTitle(),
                c.getApplicationDate(),
                c.getStatus(),
                c.getJobOfferLink(),
                c.getNotes()
            )).collect(Collectors.toList());
    }
    
}
