package fr.ensisa.supercerveau.model.player;

public class Score {
	private int score;
	public Score() {
		this.score = 0;
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	public void addPoint() {
		this.score+=1;
	}
	
	public void addPoint(int point) {
		this.score+=point;
	}
	
	public void removePoint() {
		this.score-=1;
	}
	
	public void removePoint(int point) {
		this.score-=point;
	}
	
	public String toString() {
		return ""+this.score;
	}
	
}
