package lt.vu.cdi;

import lt.vu.services.RandomNumberGenerator;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import java.util.Random;

@ApplicationScoped
@Specializes
public class BoostedRandomNumberGenerator extends RandomNumberGenerator {
    private final Random random = new Random();

    @Override
    public int getRandomLevel() {
        return random.nextInt(25) + 11;
    }
}
