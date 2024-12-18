package mk.ukim.finki.wp_aud.service.implementation;

import mk.ukim.finki.wp_aud.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.wp_aud.model.exceptions.InvalidCredentialsException;
import mk.ukim.finki.wp_aud.model.exceptions.PasswordsDontMatchException;
import mk.ukim.finki.wp_aud.model.User;
import mk.ukim.finki.wp_aud.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.wp_aud.repository.jpa.UserRepository;
import mk.ukim.finki.wp_aud.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
       return userRepository.findByUsernameAndPassword(username, password)
               .orElseThrow(InvalidCredentialsException::new);
    }

}
