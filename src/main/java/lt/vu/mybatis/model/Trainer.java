package lt.vu.mybatis.model;

public class Trainer {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.TRAINER.ID
     *
     * @mbg.generated Mon Apr 27 20:55:42 EEST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.TRAINER.LEVEL
     *
     * @mbg.generated Mon Apr 27 20:55:42 EEST 2020
     */
    private Integer level;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.TRAINER.USERNAME
     *
     * @mbg.generated Mon Apr 27 20:55:42 EEST 2020
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.TRAINER.XP
     *
     * @mbg.generated Mon Apr 27 20:55:42 EEST 2020
     */
    private Integer xp;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.TRAINER.ID
     *
     * @return the value of PUBLIC.TRAINER.ID
     *
     * @mbg.generated Mon Apr 27 20:55:42 EEST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.TRAINER.ID
     *
     * @param id the value for PUBLIC.TRAINER.ID
     *
     * @mbg.generated Mon Apr 27 20:55:42 EEST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.TRAINER.LEVEL
     *
     * @return the value of PUBLIC.TRAINER.LEVEL
     *
     * @mbg.generated Mon Apr 27 20:55:42 EEST 2020
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.TRAINER.LEVEL
     *
     * @param level the value for PUBLIC.TRAINER.LEVEL
     *
     * @mbg.generated Mon Apr 27 20:55:42 EEST 2020
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.TRAINER.USERNAME
     *
     * @return the value of PUBLIC.TRAINER.USERNAME
     *
     * @mbg.generated Mon Apr 27 20:55:42 EEST 2020
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.TRAINER.USERNAME
     *
     * @param username the value for PUBLIC.TRAINER.USERNAME
     *
     * @mbg.generated Mon Apr 27 20:55:42 EEST 2020
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.TRAINER.XP
     *
     * @return the value of PUBLIC.TRAINER.XP
     *
     * @mbg.generated Mon Apr 27 20:55:42 EEST 2020
     */
    public Integer getXp() {
        return xp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.TRAINER.XP
     *
     * @param xp the value for PUBLIC.TRAINER.XP
     *
     * @mbg.generated Mon Apr 27 20:55:42 EEST 2020
     */
    public void setXp(Integer xp) {
        this.xp = xp;
    }
}