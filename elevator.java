//1. We have created an elevator object that uses ArrayLists to determine
//the destination floors that it will be going to upward and downwards then
//asks the user if they wanted to run it again.
/*2. 1st problem: I had to make an arraylist of generated numbers for the up and down list
 *  which we did by using MATH.random, then I had to make sure none of the numbers in
 *  the respective lists didnt have duplicates, so i used a while loop to check if a
 *  previous value had that same number and just randomized the variable again
 *  then i had to sort the lists but i just used the collections class to sort
 *  both lists then used the reverse method to make sure the downlist was decending
 *  2nd problem: I had to make a floorUp and floorDown method which will display
 *  where you are at, where you are going, where you stopped, and how long you
 *  stopped for
 *  3rd problem: I had to make a method that would control the executions of the 
 *  floor up and floor down methods and making sure if the user decides to re run the
 *  program, the current floor would be reset to 1
 */
//3. ArrayList
//4. Run the program then insert y if you would like to continue the program
//when it asks "Would you like to run again?"
//5. Elevator class is the client, and also we use the constructor of the class to 
//initialize certain data as requsted in the program.
import java.util.*;
public class elevator {
	private boolean isGoingUp;
	private int currentFloor;
	private int destinationFloor;
	private ArrayList<Integer> upList;
	private ArrayList<Integer> downList;
	
	public elevator() {
		currentFloor = 1;
		isGoingUp = true;
		destinationFloor = 1;
		upList = new ArrayList<Integer>();
		downList = new ArrayList<Integer>();
		upListGenerator();
		downListGenerator();
	}
	public static void main(String[] args) throws InterruptedException {
		elevator elevate = new elevator();
		elevate.moveFloor();
		Scanner scan = new Scanner(System.in);
		System.out.println("Do you want to run it again?");
		String answer = scan.nextLine();
		if(answer.equals("n")) {
			System.out.println("Terminated");
		} else {
			System.out.println("Invalid answer.");
			answer = scan.nextLine();
		}
		while(answer.equals("y")) {
			elevate.upListGenerator();
			elevate.downListGenerator();
			elevate.moveFloor();
			System.out.println("Do you want to run it again?");
			answer = scan.nextLine();
		}
	}
	public  void upListGenerator() {
		for(int i = 0; i < 8; i++){
			int g = (int) (Math.random()*12 +1);
			while(this.upList.contains(g)) {
				g = (int) (Math.random()*12 +1);
			}
			this.upList.add(g);
		}
		Collections.sort(upList);
	}
	public void downListGenerator() {
		Random rand = new Random();
		for(int i = 0; i < 5; i++){
			int g = (int) (Math.random()*12 +1);
			while(this.downList.contains(g)) {
				g = (int) (Math.random()*12 +1);
			}
			this.downList.add(g);
		}
		Collections.sort(downList);
		Collections.reverse(downList);
	}
	public void floorUp(ArrayList<Integer> list) throws InterruptedException {
		for(int i = 0; i < 8; i++) {
			destinationFloor = list.get(0);
			System.out.println("Starting at floor " + currentFloor);
			while(!(currentFloor == destinationFloor)) {
				currentFloor++;
				System.out.println("Going up, now at floor: " + currentFloor);
			}
			System.out.println("Stopping at floor " + currentFloor + " for 3 seconds ... 1, 2, 3");
			Thread.sleep(1000);
			list.remove(0);
		}
		isGoingUp = false;
	}
	public void floorDown(ArrayList<Integer> list) throws InterruptedException {
		for(int i = 0;i < 5; i++) {
			destinationFloor = list.get(0);
			System.out.println("Starting at floor " + currentFloor);
			while(!(currentFloor == destinationFloor)) {
				currentFloor--;
				System.out.println("Going down, now at floor: " + currentFloor);
			}
			System.out.println("Stopping at floor " + currentFloor + " for 3 seconds ... 1, 2, 3");
			Thread.sleep(1000);
			list.remove(0);
		}
		isGoingUp = true;
		
	}
	public void moveFloor() throws InterruptedException {
		currentFloor = 1;
		for(int i = 0; i < 2; i++)
		if(this.isGoingUp) {
			floorUp(upList);
		} else {
			floorDown(downList);
		}
	}

}