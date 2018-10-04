package fr.creekorful.reward.pojo;

import java.io.Serializable;

import fr.creekorful.reward.constant.Severity;

/**
 * POJO class used to store rewarded player information
 */
public class Player implements Serializable {

    private String name;

    private Severity severity;

    private float ransom;

    public Player() {

    }

    public Player(String name, Severity severity, float ransom) {
        this.name = name;
        this.severity = severity;
        this.ransom = ransom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public float getRansom() {
        return ransom;
    }

    public void setRansom(float ransom) {
        this.ransom = ransom;
    }

    @Override
    public String toString() {
        return "Player{" + "name='" + name + '\'' + ", severity=" + severity + ", ransom=" + ransom + '}';
    }
}
