package mk.ukim.finki.wp_aud.service;

import mk.ukim.finki.wp_aud.model.User;

public interface AuthService {
    User login(String username, String password);
}
