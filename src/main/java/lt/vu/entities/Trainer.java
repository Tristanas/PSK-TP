package lt.vu.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "TRAINER")
@EqualsAndHashCode(of = {"username"})
@NamedQueries({
        @NamedQuery(name = "Trainer.findByUsername", query = "select a from Trainer as a where a.username = :username"),
        @NamedQuery(name = "Trainer.findAll", query = "select a from Trainer as a"),
})
public class Trainer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany
    @JoinTable(name="TRAINER_BADGE")
    private Collection<GymBadge> gymBadges;

    @OneToMany(mappedBy = "trainer")
    private List<Pokemon> pokemonList;

    @Size(max = 20)
    @Column(name = "USERNAME", unique = true)
    private String username;

    private int xp;

    private int level;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    public Trainer() {
    }

    public void gainXP(int xp) {
        if (xp < 1) return;
        this.xp += xp;
    }
}


