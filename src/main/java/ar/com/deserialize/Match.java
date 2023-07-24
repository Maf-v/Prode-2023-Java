package ar.com.deserialize;

public class Match {
    private long id;
    private String status;
    private long matchday;
    private String stage;
    private Team homeTeam;
    private Team awayTeam;
    private Score score;
    
    public Match(Long id) {
    	this.id = id;
    }

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public String getStatus() { return status; }
    public void setStatus(String value) { this.status = value; }

    public long getMatchday() { return matchday; }
    public void setMatchday(long value) { this.matchday = value; }

    public String getStage() { return stage; }
    public void setStage(String value) { this.stage = value; }

    public Team getHomeTeam() { return homeTeam; }
    public void setHomeTeam(Team value) { this.homeTeam = value; }

    public Team getAwayTeam() { return awayTeam; }
    public void setAwayTeam(Team value) { this.awayTeam = value; }

    public Score getScore() { return score; }
    public void setScore(Score value) { this.score = value; }
}