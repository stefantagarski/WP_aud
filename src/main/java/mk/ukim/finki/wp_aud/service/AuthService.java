package mk.ukim.finki.wp_aud.service;

import mk.ukim.finki.wp_aud.model.User;

public interface AuthService {
    public User login(String username, String password);

    public User register(String username, String password, String repeatPassword, String name, String surname);
}
