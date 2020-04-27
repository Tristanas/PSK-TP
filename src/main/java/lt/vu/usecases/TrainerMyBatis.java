package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.dao.GymbadgeMapper;
import lt.vu.mybatis.dao.TrainerMapper;
import lt.vu.mybatis.model.Gymbadge;
import lt.vu.mybatis.model.Trainer;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class TrainerMyBatis {
    @Inject
    private TrainerMapper trainerMapper;

    @Inject
    private GymbadgeMapper gymbadgeMapper;

    @Getter @Setter
    private Gymbadge newBadge = new Gymbadge();

    @Getter @Setter
    private Trainer trainer;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer trainerId = Integer.parseInt(requestParameters.get("trainerId"));
        trainer = trainerMapper.selectByPrimaryKey(trainerId);
    }

    @Transactional
    public String createGymBadge() {
        gymbadgeMapper.insert(newBadge);
        return "myBatis/trainer?trainerId=" + trainer.getId() + "&faces-redirect=true";
    }

}
