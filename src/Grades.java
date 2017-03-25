import java.math.BigDecimal;

public class Grades {
	/**
	 * @uml.property  name="iD"
	 */
	private String ID;
	/**
	 * @uml.property  name="name"
	 */
	private String name;
	/**
	 * @uml.property  name="lab1"
	 */
	private int lab1;
	/**
	 * @uml.property  name="lab2"
	 */
	private int lab2;
	/**
	 * @uml.property  name="lab3"
	 */
	private int lab3;
	/**
	 * @uml.property  name="midTerm"
	 */
	private int midTerm;
	/**
	 * @uml.property  name="finalExam"
	 */
	private int finalExam;
	/**
	 * @uml.property  name="totalGrade"
	 */
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
	
	/**
	 * @return
	 * @uml.property  name="iD"
	 */
	public String getID() {
		return this.ID;
	}
	
	/**
	 * @return
	 * @uml.property  name="name"
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return
	 * @uml.property  name="lab1"
	 */
	public int getLab1() {
		return this.lab1;
	}
	
	/**
	 * @return
	 * @uml.property  name="lab2"
	 */
	public int getLab2() {
		return this.lab2;
	}
	
	/**
	 * @return
	 * @uml.property  name="lab3"
	 */
	public int getLab3() {
		return this.lab3;
	}
	
	/**
	 * @return
	 * @uml.property  name="midTerm"
	 */
	public int getMidTerm() {
		return this.midTerm;
	}
	
	/**
	 * @return
	 * @uml.property  name="finalExam"
	 */
	public int getFinalExam() {
		return this.finalExam;
	}
	
	/**
	 * @return
	 * @uml.property  name="totalGrade"
	 */
	public int getTotalGrade() {
		return this.totalGrade;
	}
}
