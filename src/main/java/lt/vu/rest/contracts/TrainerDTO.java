package lt.vu.rest.contracts;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Version;

@Getter @Setter
public class TrainerDTO {

    private String Username;

    private int Xp;

    private int Level;

    private int Id;
}
