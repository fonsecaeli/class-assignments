import java.util.Random;

public class randomTest {

	private static int counter = 0;
	public static void main(String[] args) {
		Random rand = new Random();
		int ties = 0;
		int wins = 0;
		int loses = 0;
		for(int i = 0; i < 1000; i++) {
			int random = rand.nextInt(3);
			int myG = mine();
			if(random == myG) {
				ties++;
			}
			else if(random == 1 && myG == 0) {
				loses++;
			}
			else if(random == 2 && myG == 0) {
				wins++;
			}
			else if(random == 0 && myG == 1) {
				wins++;
			}
			else if(random == 0 && myG == 2) {
				loses++;
			}
			else if(random == 2 && myG == 1) {
				loses++;
			}
			else if(random == 1 && myG == 2) {
				wins++;
			} 
		}
		System.out.println("Ties: "+ ties);
		System.out.println("Wins: "+ wins);
		System.out.println("Loses: "+loses);
	}
	public static int mine() {
		Random rand2 = new Random();
		/*if(counter % 1 == 0) {
			counter++;
			return 1;
		}
		else {
			counter++;
			return rand2.nextInt(3);
		}*/
		return 2;
	}
}