package mk.ukim.finki.wp_aud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan //we got to have this when working with servlets
public class WpAudApplication {

    public static void main(String[] args) {
        SpringApplication.run(WpAudApplication.class, args);
    }

}
