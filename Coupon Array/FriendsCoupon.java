import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class FriendsCoupon{
	
	//users numbered 1,2,3,4
	//coupons numbered A,B,C,D
	

	public static void main(String args[]) throws FileNotFoundException{
		
		
		int numLines = 0;
		
		if (args.length >= 1 && args[0].equals("-t")) {
            testReject();
            testIsFullSolution();
            testExtend();
            testNext();
            System.exit(0);
		}
		
		int numCoupons = Integer.parseInt(args[1]);
		File file = new File(args[0]);
		
		
		
		//Testing purposes:
		//int numCoupons = 1;
		//File file = new File("medium.txt");
		
		//gets number of lines
		Scanner sc1 = new Scanner(file);
		while (sc1.hasNextLine()){
			sc1.nextLine();
			numLines++;
			}
		
		
		int[][] friends = new int[numLines][numLines];
		
		
		//gets the array of friends
		Scanner sc2 = new Scanner(file);
		for(int i = 0; i < numLines; i++){
			for(int j = 0; j < numLines; j++){
				friends[i][j] = sc2.nextInt();
			}
		}
		
		//Looks for bad arrays
		try{
		if( friends[0][numLines +1] >= 0 || friends[numLines + 1][0] >= 0){
			System.out.println("Invalid Array");
			System.exit(0);
		}
		}
		catch(Exception e){
			
		}
		
		
		int[] partial = new int[friends.length];
		int[] solution = backTrack(partial, friends, numCoupons);
		//System.out.println(Arrays.toString(solution));
		if(solution == null){
			System.out.println("No Valid Solution");
		}
		char[] realSolution = new char[friends.length];
		for(int i = 0; i < friends.length; i++){
			for(int j = 0; j < friends.length; j++){
				try{
				realSolution[i] =  (char) ('A' + solution[i] -1);
				}
				catch(Exception e){
					
				}
				
			}
		}
		System.out.println(Arrays.toString(realSolution));
		
		//testNext();
		//testExtend();
		//testReject();
		//testIsFullSolution();
		
		
		
	}
	
	public static int[] backTrack(int[] partial, int[][] friends, int numCoupons) {
		//System.out.println("in back");
		
        if (reject(partial,friends)) return null;
        
        if (isFullSolution(partial,friends)) return partial;
        int[] attempt = extend(partial);
        while (attempt != null) {
        	//System.out.println(Arrays.toString(attempt));
            int[] solution = backTrack(attempt,friends, numCoupons);
            if (solution != null) return solution;
            attempt = next(attempt, numCoupons);
           
        }
        return null;
    }
	
	/*
	isFullSolution, a method that accepts a partial solution and returns true 
	if it is a complete valid solution.
	*/
	
	public static boolean isFullSolution(int[] partial, int[][] friends){
		for (int i = partial.length-1; i >= 0; i--) {
            if (partial[i] == 0) {
                return false;
            }
        }
		// The solution is known to be complete, check if it is valid
       
		if (reject(partial, friends)) {
            return false;
        }
        // The solution is complete and valid
        return true;
		
	}
	
	
	public static boolean reject(int[] partial, int[][] friends){
		for(int i = 0; i < partial.length; i++){
			for(int j = 0; j < i; j++){
				if(partial[i] == 0 || partial[j] == 0){
					return false;
				}
				else if(partial[i] == partial[j] && friends[i][j] == 1){
					return true;
				}
			}
		}
		
		return false;
		
	}
	/*
	extend, a method that accepts a partial solution and returns another 
	partial solution that includes one additional choice added on. 
	This method will return null if there are no more choices to add to the solution.
	Changes to next person
	
	increments the person your assigning coupons to
	*/
	
	public static int[] extend(int[] partial ){
		int temp[] = new int[partial.length];
		for(int i = 0; i < partial.length; i++){
			//giving a coupon to the next user
			
					if (partial[i] != 0){
						temp[i] = partial[i];						
					}
					else {
						temp[i] = 1;						
						return temp;
						
					}
				}
		
		return temp;
		
	}
	
	
	/*
	next, a method that accepts a partial solution and returns another 
	partial solution in which the most recent choice to be added has been 
	changed to its next option. This method will return null if there are 
	no more options for the most recent choice that was made.
	
	moves ticket up to the next one to try again
	
	increments the ticket
	*/
	public static int[] next(int partial[], int numCoupons){
		int[] temp = new int[partial.length];
		int i = 0;
		
		
		while(i <= partial.length - 1){
			
			if(i == (partial.length - 1)){
				if(partial[i] != 0){
				
				temp[i] = partial[i]+1;
				
				break;
				}
			}
			else if(i < partial.length - 1){
				
				
				if(partial[i+1] == 0){
				
					if(partial[i] >= numCoupons){
						return null;
						}
				
					else{
						temp[i] = partial[i] + 1;
						break;
				}
			}
				else {
	                // not last
	                temp[i] = partial[i];
	            }	
			
			}
			
			
			
            i++;
		}
		return temp;
		
	}
	
	//testIsFullSolution,a method that generates partial solutions
	//and ensures that the isFullSolution method correctly determines whether each is a 
	//complete solution.
	public static void testIsFullSolution(){
		
		//needs partial,friends as args
		 System.out.println("");
		System.out.println("TESTING ISFULLSOLUTION");
		 System.out.println("");
		
		System.out.println("Test 1 : [0,0,0,0]");
		if(!isFullSolution(new int[] {0,0,0,0}, new int[][] {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}})){
			System.out.println("Passed, not full");
		}
			else{
				System.out.println("Fails");
			}
		
		System.out.println("Test 2 : [1,1,1,0]");
		if(!isFullSolution(new int[] {1,1,1,0}, new int[][] {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}})){
			System.out.println("Passed, not full");
		}
			else{
				System.out.println("Fails");
			}
		
		System.out.println("Test 3 : [1,1,1,1]");
		if(isFullSolution(new int[] {1,1,1,1}, new int[][] {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}})){
			System.out.println("Passed, is full solution");
		}
			else{
				System.out.println("Fails");
			}
		
		System.out.println("Test 4 : [1,1,1,1]");
		if(!isFullSolution(new int[] {1,1,1,1}, new int[][] {{0,1,1,1},{1,0,1,1},{1,1,0,1},{1,1,1,0}})){
			System.out.println("Passed, solution is full but is rejected");
		}
			else{
				System.out.println("Fails");
			}
		
		System.out.println("Test 4 : [1,1,1,1]");
		if(!isFullSolution(new int[] {1,1,1,1,1,1,1,0}, new int[][] {{0,1,1,1},{1,0,1,1},{1,1,0,1},{1,1,1,0}})){
			System.out.println("Passed, solution is full but is rejected");
		}
			else{
				System.out.println("Fails");
			}
	}
	
	
	//testReject, a method that generates partial solutions and ensures 
	//that the reject method correctly determines whether each should be rejected.
	public static void testReject(){
		//needs partial and friends
		 System.out.println("");
		System.out.println("TESTING REJECT");
		 System.out.println("");
		
		System.out.println("Test 1 should be false, noone is friends");
		System.out.println("Rejected :" + reject(new int[] {1,1,1,1}, new int[][] {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}}));
		
		System.out.println("Test 2 should be true because friends have the same coupon");
		System.out.println("Rejected: " + reject(new int[] {1,1,1,1}, new int[][] {{0,1,0,0},{1,0,0,0},{0,0,0,0},{0,0,0,0}}));
		
		System.out.println("Test 3 should be true because friends have the same coupon, and there is no valid solution");
		System.out.println("Rejected : " + reject(new int[] {1,1}, new int[][] {{0,1},{1,0}}));
		
		System.out.println("Test 4 should be false because this is a valid partial solution");
		System.out.println("Rejected : " + reject(new int[] {1,2,3,4,5,6,7,8}, new int[][] {{0,1,0,0,0,0,0,0},{1,0,0,0,0,0,0,0},{0,0,0,1,0,0,0,0},{0,0,0,0,1,0,0,0,0},{0,0,0,0,1,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,1,0,0,1,0},{0,0,0,1,0,0,1,0}}));
	
		System.out.println("Test 5 should be true because friends have same coupon");
		System.out.println("Rejected : " + reject(new int[] {1,2,3,4,5,6,7,1}, new int[][] {{0,1,0,0,0,0,0,1},{1,0,0,0,0,0,0,0},{0,0,0,1,0,0,0,0},{0,0,0,0,1,0,0,0,0},{0,0,0,0,1,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,1,0,0,1,0},{1,0,0,1,0,0,1,0}}));
	}
	
	//testExtend, a method that generates partial solutions and ensures 
	//that the extend method correctly extends each with the correct next choice.
	public static void testExtend(){
		 System.out.println("");
		System.out.println("TESTING EXTEND");
		 System.out.println("");
		int[] extend1 = extend(new int[] {0,0,0,0});
		System.out.println("Should print: [1,0,0,0]");
		System.out.println(Arrays.toString(extend1));
		
		int[] extend2 = extend(new int[] {1,1,1,1});
		System.out.println("Should print: [1,1,1,1] because it cannot be extended");
		System.out.println(Arrays.toString(extend2));
		
		int[] extend3 = extend(new int[] {1,2,4,6});
		System.out.println("Should print: [1,2,4,6] because it cannot be extended");
		System.out.println(Arrays.toString(extend3));
		
		int[] extend4 = extend(new int[] {1,2,4,0});
		System.out.println("Should print: [1,2,4,1]");
		System.out.println(Arrays.toString(extend4));
		
		int[] extend5 = extend(new int[] {1,1,0,0,0,0,0});
		System.out.println("Should print: [1,1,1,0,0,0,0]");
		System.out.println(Arrays.toString(extend5));
		
		int[] extend6 = extend(new int[] {1,2,4,9,19,100,99,99,0});
		System.out.println("Should print: [1,2,4,9,19,100,99,99,1]");
		System.out.println(Arrays.toString(extend6));
		
		
		
		
		
	}
	
	//testNext, a method that generates partial solutions and ensures 
	//that the, in each, next method correctly changes the most 
	//recent choice that was added to its next option.
	public static void testNext(){
		
		 System.out.println("");
		 System.out.println("TESTING NEXT");
		 System.out.println("");
	      
	       //Case 1 should increment the first persons coupon
		
		 	int[] next1 = next(new int[] {0,0,0,0}, 1);
		 	System.out.println("Should print: [1,0,0,0] :");
		 	System.out.println(Arrays.toString(next1));
		 	
		 	int[] next5 = next(new int[] {0,1,0,0}, 2);
		 	System.out.println("Should print: [0,2,0,0] :");
		 	System.out.println(Arrays.toString(next5));
		 	
		 	int[] next6 = next(new int[] {1,1,1,1,1,1,0}, 2);
		 	System.out.println("Should print: [1,1,1,1,1,2,0] :");
		 	System.out.println(Arrays.toString(next6));
		 	
		 	//case 2 should increment the 1 to a 2;
		 	int[] next2 = next(new int[] {1,0,0,0}, 2);
		 	System.out.println("Should print: [2,0,0,0] :");
		 	System.out.println(Arrays.toString(next2));
		 	
		 	//case 3 should increment the last index
	       int[] next3 = next(new int[] {1,1,1,3}, 4);
	       System.out.println("Should Print[1,1,1,4] :");
	       System.out.println(Arrays.toString(next3));
	      //NEED ONE WITH OVER COUPONS
	       
	       int[] next4 = next(new int[] {2,0,0,0}, 2);
	       System.out.println("Should null, not enough coupons :");
	       System.out.println(Arrays.toString(next4));
	       
		
	}

	
 	
}
