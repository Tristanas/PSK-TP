package lt.vu.cdi;

import lt.vu.qualifiers.RandomLevel;
import lt.vu.services.IRandom;
import lt.vu.services.RandomNumberGenerator;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.Specializes;
import javax.inject.Named;
import java.util.Random;

@ApplicationScoped
@Specializes
public class BoostedRandomNumberGenerator extends RandomNumberGenerator {
    private Random random = new Random();

    @Override
    public int getRandomLevel() {
        return random.nextInt(25) + 11;
    }
}
