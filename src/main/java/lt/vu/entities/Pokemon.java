package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
//@NamedQueries(
//        @NamedQuery(name = "pokemon.deleteById", query = "delete from Pokemon where Pokemon.id = :id")
//)
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
