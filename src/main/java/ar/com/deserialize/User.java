package ar.com.deserialize;

public class User {
	String uid;
	String password;
	
	public User(String uid, String password) {
		this.uid = uid;
		this.password = password;
	}
	
    public String getUid() { return uid; }
    public void setUid(String value) { this.uid = value; }
    
    public String getPassword() { return password; }
    public void setPassword(String value) { this.password = value; }
}
