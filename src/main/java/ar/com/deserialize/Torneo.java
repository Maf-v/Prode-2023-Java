package ar.com.deserialize;

public class Torneo {
	
	public Long id;
	public String nombre;
	
	public Torneo(String nombre) {
		this.nombre = nombre;
	}
	
	public Torneo(Long id, String nombre) {
		this.nombre = nombre;
		this.id = id;
	}
	
    public long getId() { return id; }
    public void setId(Long value) { this.id = value; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String value) { this.nombre = value; }
}
