package outedg.outgration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OutgrationApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(OutgrationApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Outedg Migration TOOLS!!");
    }
}
