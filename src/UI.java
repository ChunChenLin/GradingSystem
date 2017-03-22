import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {
	private String ID;
	private String Name;
	
	public UI() throws NoSuchIDExceptions, NoSuchCommandExceptions {
		try {
			GradeSystems gs = new GradeSystems();
			while(promptID()) {
				if(!checkID(gs)) throw new NoSuchIDExceptions(); 
				else {
					showWelcomeMsg(gs);
					while(promptCommand(gs));
				}
			}
			showFinishMsg();
		}
		catch (FileNotFoundException ex) {}
		catch(IOException ex) {}
		catch(InputMismatchException ex) {}
	}
	
	private boolean checkID(GradeSystems gs) {
		return gs.containsID(ID);
	}
	
	private boolean promptCommand(GradeSystems gs) throws NoSuchCommandExceptions{
		System.out.println("輸入指令 :\n"
				+ "1) G 顯示成績 (Grade) \n"
				+ "2) R 顯示排名 (Rank) \n"
				+ "3) A 顯示平均 (Average)\n"
				+ "4) W 更新配分 (Weight) \n"
				+ "5) E 離開選單 (Exit) ");
		Scanner scanner = new Scanner(System.in);
		String command = scanner.nextLine();
		switch(command) {
			case "E":
				return false;
			case "G":
				System.out.println(Name + " 成績:");
				gs.showGrade(ID);
				return true;
			case "R":
				System.out.println(Name + "排名第 " + gs.showRank(ID));
				return true;
			case "A":
				System.out.println("顯示平均:");
				gs.showAverage();
				return true;
			case "W":
				gs.updateWeights();
				return true;
			default:
				throw new NoSuchCommandExceptions();
		}
	}
	
	private boolean promptID() throws NoSuchIDExceptions{
		System.out.println("輸入ID或 Q (結束使用)？");
		Scanner scanner = new Scanner(System.in);
		ID = scanner.nextLine();
		if(ID.equals("Q")) return false;
		else return true;
	}
	
	private void showFinishMsg() {
		System.out.println("結束了");
	}
	
	private void showWelcomeMsg(GradeSystems gs) {
		Name = gs.showName(ID);
		System.out.println("Welcome " + Name);
	}
}
