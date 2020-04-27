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
    private Integer id;

    @Column(name = "NUMBER")
    private Integer number;

    @ManyToOne
    @JoinColumn(name="TRAINER_ID")
    private Trainer trainer;

    @Column(name = "NAME")
    private String name;

    //@Size(max = 40)
    @Column(name = "LEVEL")
    private Integer level;

    private int combatPower;

    public void powerUp()
    {
        if (level < 40) {
            level++;
            combatPower += 24;
        }
    }
}
