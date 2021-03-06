package lt.vu.mybatis.dao;

import java.util.List;
import lt.vu.mybatis.model.TrainerBadge;
import org.mybatis.cdi.Mapper;

@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
@Mapper
public interface TrainerBadgeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.TRAINER_BADGE
     *
     * @mbg.generated Mon Apr 27 20:55:42 EEST 2020
     */
    int insert(TrainerBadge record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.TRAINER_BADGE
     *
     * @mbg.generated Mon Apr 27 20:55:42 EEST 2020
     */
    List<TrainerBadge> selectAll();
}