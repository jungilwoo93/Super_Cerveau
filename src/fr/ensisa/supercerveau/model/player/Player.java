package fr.ensisa.supercerveau.model.player;

import fr.ensisa.supercerveau.model.util.Constantes;
import fr.ensisa.supercerveau.model.util.ScoreWinException;

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
	public Score getScore() throws ScoreWinException {
		if(this.score.getScore() >= Constantes.SCORE_WIN) {
			throw new ScoreWinException(this.score.getScore()+"");
		}
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
