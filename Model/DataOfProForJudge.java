package Model;

public class DataOfProForJudge {
	private String p_id;
	private String proName;
	private String ageGroup;
	private String groupNum;
	private String r_gameId;
	public DataOfProForJudge(String p_id,String proName,String ageGroup,String groupNum,String r_gameId) {
		// TODO 自动生成的构造函数存根
		this.p_id = p_id;
		this.proName = proName;
		this.ageGroup = ageGroup;
		this.groupNum = groupNum;
		this.r_gameId = r_gameId;
	}
	public String getProName() {
		return proName;
	}
	public String getAgeGroup() {
		return ageGroup;
	}
	public String getGroupNum() {
		return groupNum;
	}
	public String getR_gameId() {
		return r_gameId;
	}
	public String getP_id() {
		return p_id;
	}
}
