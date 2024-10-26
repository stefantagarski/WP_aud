package mk.ukim.finki.wp_aud.bootstrap.exceptions;

public class PasswordsDontMatchException extends RuntimeException{
    public PasswordsDontMatchException() {
        super("Passwords don't match!");
    }
}
