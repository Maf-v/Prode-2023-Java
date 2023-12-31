package ar.com.deserialize;

public class Pronostico {
	public String userId;
	public Long matchId;
	public Integer scoreHome;
	public Integer scoreAway;
	public Integer puntos;

	public Pronostico(String userId, Long matchId, Integer scoreHome, Integer scoreAway) {
		this.userId = userId;
		this.matchId = matchId;
		this.scoreHome = scoreHome;
		this.scoreAway = scoreAway;
	}
	
	public Pronostico(String userId, Long matchId, Integer scoreHome, Integer scoreAway, Integer puntos) {
		this.userId = userId;
		this.matchId = matchId;
		this.scoreHome = scoreHome;
		this.scoreAway = scoreAway;
		this.puntos = puntos;
	}
	
	public String getUserId() { return userId; }
	public void setUserId(String value) {this.userId = value; }
	
    public long getMatchID() { return matchId; }
    public void setMatchID(long value) { this.matchId = value; }
    
    public Integer getScoreHome() { return scoreHome; }
    public void setScoreHome(Integer value) {this.scoreHome = value;}
    
    public Integer getScoreAway() { return scoreAway; }
    public void setScoreAway(Integer value) {this.scoreAway = value;}
    
    public Integer getPuntos() {return puntos;}
    public void setPuntos(Integer value) {this.puntos = value;}
}
