package mk.ukim.finki.wp_aud.service.implementation;

import mk.ukim.finki.wp_aud.model.User;
import mk.ukim.finki.wp_aud.model.enumerations.Role;
import mk.ukim.finki.wp_aud.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.wp_aud.model.exceptions.PasswordsDontMatchException;
import mk.ukim.finki.wp_aud.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.wp_aud.repository.jpa.UserRepository;
import mk.ukim.finki.wp_aud.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role role) {
        if (username.isEmpty() || password.isEmpty()
                || repeatPassword.isEmpty() || name.isEmpty() || surname.isEmpty()) {
            throw new InvalidArgumentsException();
        }

        if (!password.equals(repeatPassword)) {
            throw new PasswordsDontMatchException();
        }

        if (userRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException(username);
        }

        return userRepository.save(new User(username, passwordEncoder.encode(password), name, surname, role));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
