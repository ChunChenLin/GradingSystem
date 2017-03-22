import java.math.BigDecimal;

public class Grades {
	private String ID;
	private String name;
	private int lab1;
	private int lab2;
	private int lab3;
	private int midTerm;
	private int finalExam;
	private int totalGrade;
	
	public Grades(String ID, String name, int lab1, int lab2, int lab3, int midTerm, int finalExam) {
		this.ID = ID;
		this.name = name;
		this.lab1 = lab1;
		this.lab2 = lab2;
		this.lab3 = lab3;
		this.midTerm = midTerm;
		this.finalExam = finalExam;
	}
	
	public void calculateTotalGrade(double w1,double w2,double w3,double wM,double wF) {
		double doubleTotalGrade = lab1*w1 + lab2*w2 + lab3*w3 + midTerm*wM + finalExam * wF;
		this.totalGrade = new BigDecimal(doubleTotalGrade).setScale(0, BigDecimal.ROUND_HALF_UP).intValueExact();
	}
	
	public String getID() {
		return this.ID;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getLab1() {
		return this.lab1;
	}
	
	public int getLab2() {
		return this.lab2;
	}
	
	public int getLab3() {
		return this.lab3;
	}
	
	public int getMidTerm() {
		return this.midTerm;
	}
	
	public int getFinalExam() {
		return this.finalExam;
	}
	
	public int getTotalGrade() {
		return this.totalGrade;
	}
}
