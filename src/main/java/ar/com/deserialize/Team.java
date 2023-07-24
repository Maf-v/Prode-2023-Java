package ar.com.deserialize;

public class Team {
    private long id;
    private String name;
    private String shortName;
    private String tla;
    private String crest;
    
    public Team(Long id, String shortName, String tla, String crest) {
    	this.id = id;
    	this.crest = crest;
    	this.shortName = shortName;
    	this.tla = tla;
    }

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getShortName() { return shortName; }
    public void setShortName(String value) { this.shortName = value; }

    public String getTLA() { return tla; }
    public void setTLA(String value) { this.tla = value; }

    public String getCrest() { return crest; }
    public void setCrest(String value) { this.crest = value; }
}
