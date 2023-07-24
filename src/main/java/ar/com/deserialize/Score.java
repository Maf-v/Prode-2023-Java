package ar.com.deserialize;

public class Score {
    private Object winner;
    private String duration;
    private Time fullTime;
    private Time halfTime;

    public Object getWinner() { return winner; }
    public void setWinner(Object value) { this.winner = value; }

    public String getDuration() { return duration; }
    public void setDuration(String value) { this.duration = value; }

    public Time getFullTime() { return fullTime; }
    public void setFullTime(Time value) { this.fullTime = value; }

    public Time getHalfTime() { return halfTime; }
    public void setHalfTime(Time value) { this.halfTime = value; }
}
