import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class GradeSystems {
	/**
	 * @uml.property  name="grades"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="Grades"
	 */
	private LinkedList<Grades> grades;
	/**
	 * @uml.property  name="weights" multiplicity="(0 -1)" dimension="1"
	 */
	private double[] weights;
	/**
	 * @uml.property  name="tmpWeights" multiplicity="(0 -1)" dimension="1"
	 */
	private double[] tmpWeights;
	private static final String FILENAME = "gradeinput.txt";
	
	public GradeSystems() throws FileNotFoundException,IOException,InputMismatchException {
		grades = new LinkedList<Grades>();
		weights = new double[] {0.1,0.1,0.1,0.3,0.4};
		tmpWeights = new double[5];
		
		try {
			FileReader fileReader = new FileReader(FILENAME);
			BufferedReader bufferReader = new BufferedReader(fileReader);
			String line = null;
			while((line = bufferReader.readLine()) != null) {
				Scanner input = new Scanner(line);
				String id = input.next();
				String name = input.next();
				int lab1 = input.nextInt();
				int lab2 = input.nextInt();
				int lab3 = input.nextInt();
				int midexam = input.nextInt();
				int finalexam = input.nextInt();
				Grades aGrade = new Grades(id,name,lab1,lab2,lab3,midexam,finalexam);
				aGrade.calculateTotalGrade(weights[0],weights[1],weights[2],weights[3],weights[4]);
				grades.add(aGrade);
			}
		}
		finally{}
	}
	
	public String showName(String id)
	{
		for(Grades g : grades) {
			if(id.equals(g.getID())) {
				return g.getName();
			}
		}
		return "";
	}
	
	public boolean containsID(String id) {
		for(Grades g : grades) {
			if(id.equals(g.getID())) return true;
		}
		return false;
	} 
	
	public void showGrade(String id) {
		for(Grades g : grades) {
			if(id.equals(g.getID())) {
				System.out.println("lab1 : " + checkFailed(g.getLab1())); 
				System.out.println("lab2 : " + checkFailed(g.getLab2()));
				System.out.println("lab3 : " + checkFailed(g.getLab3()));
				System.out.println("mid-term : " + checkFailed(g.getMidTerm()));
				System.out.println("final exam : " + checkFailed(g.getFinalExam()));
				System.out.println("total grade : " + checkFailed(g.getTotalGrade()));
				break;
			}
		}
	}
		
	public int showRank(String id) {
		int rank = 1;
		int totalGrade = 0;
		for(Grades g : grades) {
			if(id.equals(g.getID())) {
				totalGrade = g.getTotalGrade();
				break;
			}
		}
		for(Grades g : grades) {
			if(totalGrade < g.getTotalGrade()) {
				rank ++;
			}
		}
		return rank;
	}
	
	public void showAverage() {
		double L1Average,L2Average,L3Average,MAverage,FAverage;
		int[] sum = new int[5];
		for(Grades g : grades) {
			sum[0] += g.getLab1();
			sum[1] += g.getLab2();
			sum[2] += g.getLab3();
			sum[3] += g.getMidTerm();
			sum[4] += g.getFinalExam();
		}
		L1Average = (double)sum[0]/grades.size();
		L2Average = (double)sum[1]/grades.size();
		L3Average = (double)sum[2]/grades.size();
		MAverage = (double)sum[3]/grades.size();
		FAverage = (double)sum[4]/grades.size();
		System.out.println("lab1 平均: " + L1Average + 
					"\nlab2 平均: " + L2Average + 
					"\nlab3 平均: " + L3Average + 
					"\nmid-term 平均: " + MAverage + 
					"\nfinal exam 平均: " + FAverage);
	}
	
	public void updateWeights() {
		while(true) {
			showOldWeights();
			getNewWeights();
			System.out.println("請確認新配分" + "\nlab1：" + 
								(int)((tmpWeights[0])*100) + "%" + "\nlab2：" + 
								(int)((tmpWeights[1])*100) + "%" + "\nlab3：" + 
								(int)((tmpWeights[2])*100) + "%" + "\nmid-term：" +									(int)((tmpWeights[3])*100) + "%" + "\nfinal exam：" + 
								(int)((tmpWeights[4])*100) + "%");
			System.out.println("以上正確嗎? Y (Yes) 或 N (No) ");
			Scanner scanner = null;
			scanner = new Scanner(System.in);
			String str = scanner.nextLine();
			if(str.equals("Y")) break;
			else if(str.equals("N")) {
				System.out.println("請重新輸入新配分\n");
				continue;
			}
			else throw new IllegalArgumentException("請輸入合法字母 'Y' 或 'N'\n");
		}	
		setNewWeights();
		for(Grades g : grades) {
			g.calculateTotalGrade(weights[0], weights[1], weights[2], weights[3], weights[4]);
		}	
	}
	
	private void showOldWeights() {
		System.out.println("舊配分:");
		System.out.println("lab1：" + (int)((weights[0])*100) + "%");
		System.out.println("lab2：" + (int)((weights[1])*100) + "%");
		System.out.println("lab3：" + (int)((weights[2])*100) + "%");
		System.out.println("mid-term：" + (int)((weights[3])*100) + "%");
		System.out.println("final exam：" + (int)((weights[4])*100) + "%");
	}
	
	private void getNewWeights() throws IllegalArgumentException,InputMismatchException {
			Scanner scanner = new Scanner(System.in);
			int L1,L2,L3,M,F;
			System.out.println("新配分:");
			System.out.print("lab1：");
			L1 = scanner.nextInt();
			System.out.print("lab2：");
			L2 = scanner.nextInt();
			System.out.print("lab3：");
			L3 = scanner.nextInt();
			System.out.print("mid-term：");
			M = scanner.nextInt();
			System.out.print("final exam：");
			F = scanner.nextInt();
			tmpWeights[0] = L1/100.0;
			tmpWeights[1] = L2/100.0;
			tmpWeights[2] = L3/100.0;
			tmpWeights[3] = M/100.0;
			tmpWeights[4] = F/100.0;
			if((L1+L2+L3+M+F) != 100) {
				throw new IllegalArgumentException("成績的比重總和必須為100％\n");
			}
	}
	
	private void setNewWeights() {
		for(int i = 0 ; i < weights.length ; i++) {
			weights[i] = tmpWeights[i];
		}
	}
	
	private String checkFailed(int grade) {
		if(grade < 60) return grade + " *";
		else return Integer.toString(grade);
	}
}