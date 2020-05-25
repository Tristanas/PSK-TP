package lt.vu.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NamedQueries(
        @NamedQuery(name = "GymBadge.findAll", query = "select a from GymBadge as a")
)
public class GymBadge implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 40)
    private String gymName;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;
}
