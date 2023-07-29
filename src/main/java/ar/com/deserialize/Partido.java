package ar.com.deserialize;

public class Partido {

	public Long id;
	public String status;
	public Long fecha;
	public String stage;
	public Long homeTeamId;
	public String homeTeamName;
	public Long awayTeamId;
	public String awayTeamName;
	public Integer scoreHome;
	public Integer scoreAway;
	public String homeTeamCrest;
	public String awayTeamCrest;
	
	public Partido(Long id, String status, String stage, Long homeTeamId, Long awayTeamId, Integer scoreHome, Integer scoreAway) {
		this.id = id;
		this.status = status;
		this.stage = stage;
		this.homeTeamId = homeTeamId;
		this.awayTeamId = awayTeamId;
		this.scoreHome = scoreHome;
		this.scoreAway = scoreAway;
	}
	
	public Partido(Long id, String homeTeamName, Integer scoreHome, String homeTeamCrest, String awayTeamName, Integer scoreAway, String awayTeamCrest) {
		this.id = id;
		this.homeTeamName = homeTeamName;
		this.scoreHome = scoreHome;
		this.awayTeamName = awayTeamName;
		this.scoreAway = scoreAway;
		this.homeTeamCrest = homeTeamCrest;
		this.awayTeamCrest = awayTeamCrest;
	}
	
	public Partido (Long id, Long homeTeamId, Long awayTeamId) {
		this.id = id;
		this.homeTeamId = homeTeamId;
		this.awayTeamId = awayTeamId;
	}
	
	public Partido (Long id, Long homeTeamId, Long awayTeamId, Integer scoreHome, Integer scoreAway) {
		this.id = id;
		this.homeTeamId = homeTeamId;
		this.awayTeamId = awayTeamId;
		this.scoreHome = scoreHome;
		this.scoreAway = scoreAway;
	}
	
	public Partido (Long id, String homeTeamName, String homeTeamCrest, String awayTeamName, String awayTeamCrest) {
		this.id = id;
		this.homeTeamName = homeTeamName;
		this.awayTeamName = awayTeamName;
		this.homeTeamCrest = homeTeamCrest;
		this.awayTeamCrest = awayTeamCrest;
	}
	
	
	public Long getId() {return id;}
	public String getStatus() { return status;}
	public Long getFecha() {return fecha;}
	public String getStage() { return stage;}
	public Long getHomeTeamId() {return homeTeamId;}
	public Long getAwayTeamId() {return awayTeamId;}
	public Integer getScoreHome() {return scoreHome;}
	public Integer getScoreAway() {return scoreAway;}
	public String getHomeTeamName() {return homeTeamName;}
	public String getAwayTeamName() {return awayTeamName;}
	public String getHomeTeamCrest() {return homeTeamCrest;}
	public String getAwayTeamCrest() {return awayTeamCrest;}
}
