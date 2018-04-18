package fr.ensisa.supercerveau.model.player;

public class Player {
	private String name;
	private Score score;
	
	public Player(String name) {
		this.name=name;
		this.score=new Score();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Score getScore() {
		return score;
	}
	public void setScore(Score score) {
		this.score = score;
	}
	
	public void addPoint() {
		this.score.addPoint();
	}
	public void addPoint(int point) {
		this.score.addPoint(point);
	}
	public void removePoint() {
		this.score.removePoint();
	}
	public void removePoint(int point) {
		this.score.removePoint(point);
	}
}
