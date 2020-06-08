package shoes.model;

public class Sum {
	protected  String sumType;
	protected  Integer planNum;
	protected  Integer prodNum;
	protected  String  achiveRate;
	
	public String getSumType() {
		return sumType;
	}
	public void setSumType(String sumType) {
		this.sumType = sumType;
	}
	public Integer getPlanNum() {
		return planNum;
	}
	public void setPlanNum(Integer planNum) {
		this.planNum = planNum;
	}
	public Integer getProdNum() {
		return prodNum;
	}
	public void setProdNum(Integer prodNum) {
		this.prodNum = prodNum;
	}
	public String getAchiveRate() {
		return achiveRate;
	}
	public void setAchiveRate(String achiveRate) {
		this.achiveRate = achiveRate;
	}
	
	@Override
	public String toString() {
		return "Sum [sumType=" + sumType + ", planNum=" + planNum + ", prodNum=" + prodNum + ", achiveRate="
				+ achiveRate + "]";
	}	
}
