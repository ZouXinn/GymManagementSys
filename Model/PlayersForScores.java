package Model;

public class PlayersForScores {
	private String a_gameID;
	private String a_ageGroup;
	private boolean a_sex;//true stands for boys and false stands for girls
	public PlayersForScores(String a_gameID,String a_ageGroup,boolean a_sex) {
		this.a_gameID = a_gameID;
		this.a_ageGroup = a_ageGroup;
		this.a_sex = a_sex;
	}
	public String getGameID() {
		return a_gameID;
	}
	public String getAgeGroup() {
		return a_ageGroup;
	}
	public boolean getSex() {
		return a_sex;
	}
}
