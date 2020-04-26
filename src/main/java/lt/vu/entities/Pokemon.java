package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Setter
@Getter
public class Pokemon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer number;

    @ManyToOne()
    private Trainer trainer;

    @Column(name = "NAME", unique = true)
    private String name;

    @Size(max = 40)
    @Column(name = "LEVEL")
    private float level;

    private int combatPower;

    public void powerUp()
    {
        if (level < 40) {
            level++;
            combatPower += 24;
        }
    }
}
