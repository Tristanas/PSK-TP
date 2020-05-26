package lt.vu.services;

import lt.vu.qualifiers.RandomCP;
import lt.vu.qualifiers.RandomLevel;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.util.Random;

@ApplicationScoped
public class RandomNumberGenerator implements IRandom {
    private Random random = new Random();

    @Produces @Named @RandomCP
    public int getRandomNumber() {
        return random.nextInt(100);
    }

    @Produces @Named @RandomLevel
    public int getRandomLevel() {
        return random.nextInt(25) + 1;
    }
}
