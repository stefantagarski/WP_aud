package mk.ukim.finki.wp_aud.service.implementation;

import mk.ukim.finki.wp_aud.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.wp_aud.model.exceptions.InvalidCredentialsException;
import mk.ukim.finki.wp_aud.model.exceptions.PasswordsDontMatchException;
import mk.ukim.finki.wp_aud.model.User;
import mk.ukim.finki.wp_aud.repository.InMemoryUserRepository;
import mk.ukim.finki.wp_aud.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final InMemoryUserRepository repository;

    public AuthServiceImpl(InMemoryUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User login(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
       return repository.findByUsernameAndPassword(username, password)
               .orElseThrow(InvalidCredentialsException::new);
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname) {
        if (username.isEmpty() || password.isEmpty()
                || repeatPassword.isEmpty() || name.isEmpty() || surname.isEmpty()) {
            throw new InvalidArgumentsException();
        }

        if (!password.equals(repeatPassword)) {
            throw new PasswordsDontMatchException();
        }

        return repository.saveOrUpdate(new User(username, password, name, surname));
    }
}
