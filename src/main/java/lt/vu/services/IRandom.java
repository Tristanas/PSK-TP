package lt.vu.services;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

public interface IRandom {
    public int getRandomNumber();
    public int getRandomLevel();
}
