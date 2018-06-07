package pl.coderslab.dto;

import java.io.Serializable;

public class LiveEventDto implements Serializable {

    // Ponizszy numer okresla wersje obiektu
    private static final long serialVersionUID = 1336618159361713256L;

    private String teamA;
    private String teamB;

    private byte scoreA;
    private byte scoreB;

    public LiveEventDto(String teamA, byte scoreA, String teamB, byte scoreB) {
        this.teamA = teamA;
        this.scoreA = scoreA;
        this.teamB = teamB;
        this.scoreB = scoreB;
    }

    public String getTeam1() {
        return teamA;
    }

    public String getTeam2() {
        return teamB;
    }

    public byte getPoint1() {
        return scoreA;
    }

    public byte getPoint2() {
        return scoreB;
    }


    @Override
    public String toString() {
        return teamA + " : " + scoreA + ", : " + teamB + scoreB;
    }
}
