package lt.vu.cdi;

import lt.vu.game.IXpSource;
import lt.vu.entities.Trainer;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public abstract class DoubleXpDecorator implements IXpSource {

    @Inject @Delegate @Any
    IXpSource xpSource;

    public void giveXP(int xp, Trainer trainer) {
        xpSource.giveXP(xp * 2, trainer);
    }
}
