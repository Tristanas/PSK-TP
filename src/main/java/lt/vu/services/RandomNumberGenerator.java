package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.util.Random;

@ApplicationScoped
public class RandomNumberGenerator {
    private Random random = new Random();

    @Produces @Named
    public int getRandomNumber() {
        return random.nextInt(100);
    }
}
