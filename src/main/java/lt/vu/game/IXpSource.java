package lt.vu.game;

import lt.vu.entities.Trainer;

public interface IXpSource {
    void giveXP(int xp, Trainer trainer);
}
