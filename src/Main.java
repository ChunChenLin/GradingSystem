
public class Main {
	/* Main Program for Constructing UserInterface
	 * NoSuchIDExceptions: handle wrong input ID
	 * NoSuchCommandExceptions: handle wrong input command
	 */ 
	public static void main(String[] args) {
		try {
			UI ui = new UI();
		}
		catch(NoSuchIDExceptions e) {
			System.out.println("ID輸入有誤\n");
		}
		catch(NoSuchCommandExceptions e) {
			System.out.println("指令輸入有誤\n");
		}
	}
}
