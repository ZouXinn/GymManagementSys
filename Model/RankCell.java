package Model;

public class RankCell {
	private int rank;
	private String gameID;
	private double score;
	public RankCell(int rank,String gameID,double score) {
		this.rank = rank;
		this.gameID = gameID;
		this.score = score;
	}
	public int getRank() {
		return rank;
	}
	public String getGameID() {
		return gameID;
	}
	public double getScore() {
		return score;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
}
