package ar.com.deserialize;

public class UserTorneo {
	
	public Long id;
	public String userId;
	public Long torneoId;
	public Long puntos;
	
	public UserTorneo(String userId, Long torneoId) {
		this.userId = userId;
		this.torneoId = torneoId;
	}
	
	public UserTorneo(String userId, Long puntos, Long torneoId) {
		this.userId = userId;
		this.puntos = puntos;
	}
	
    public Long getId() { return id; }
    public void setId(Long value) { this.id = value; }
	
    public String getUserId() { return userId; }
    public void setUserId(String value) { this.userId = value; }
    
    public Long getTorneoId() { return torneoId; }
    public void setTorneoId(Long value) { this.torneoId = value; }
    
    public Long getPuntos() { return puntos;}
    public void setPuntos(Long value) { this.puntos = value;}

}
