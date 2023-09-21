package hangMan;
import java.util.Scanner;
import java.util.List;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.io.File;


public class HangManGame {
    public static void main(String[] args) {
    	Scanner scanner;
    	Scanner keyboard;
    	String word;
    	
    	
    	keyboard = new Scanner(System.in);
    	System.out.println("1 player or 2 players");
    	String player = keyboard.nextLine();
    	
    	if(player.equals("1")) {
    	
    	
		try {
			scanner = new Scanner(new File("C:/Users/wilso/eclipse-workspace/hangMan/src/ListOfTeams.txt/"));
			
			
			List<String> words = new ArrayList<>();
    		
	    	while(scanner.hasNext()) {
	    		words.add(scanner.nextLine());
	    	}
	    	Random rand = new Random();
	    	word = words.get(rand.nextInt(100));
	    
	    
	    	
	    	List<Character> playerGuess = new ArrayList<>();
	    	int wrongCount=0;
	    	while(true) {
	    		printHangMan(wrongCount);
	    		
	    		if(wrongCount >= 6) {
	    			System.out.println("You LOSE, the word was " + word);
	    			break;
	    		}
	    		printWordState(word, playerGuess);
	    	if(!getPlayerGuess(keyboard, word, playerGuess)) {
	    		wrongCount++;
	    	}
	    	 
	    	if(printWordState(word,playerGuess)) {
	    		System.out.println("You Won, the word was " + word);
	    		break;
	    	}
	    	System.out.println("Enter your guess for the word:");
	    	if(keyboard.nextLine().equals(word)) {
	    		System.out.println("You Won, the word was" + word);
	    		break;
	    	}
	    	else {
	    	System.out.println("No, try again");
	    	}
	    	}
	    	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	}
    	else {
    		System.out.println("This is only a single player game currently.");
    	}
    	
    	
    	
    }

	private static void printHangMan(int wrongCount) {
		System.out.println("------");
		System.out.println(" |    |");
		if(wrongCount>=1) {
		System.out.println(" 0 ");
		}
		if(wrongCount>=2) {
			System.out.print(" \\");
			if(wrongCount>=3) {
				System.out.println("/");	
				}
			else {
				System.out.println("");
			}
			}
		
   
		if(wrongCount>=4) {
			System.out.println(" |");
			}
		
		if(wrongCount>=5) {
			System.out.print(" /");
			if(wrongCount>=6) {
				System.out.println(" \\");
			}
			else {
				System.out.println("");
			}
			}
	}

	private static boolean getPlayerGuess(Scanner keyboard, String word, List<Character> playerGuess) {
		System.out.println("Please enter a letter.");
		String letterGuess = keyboard.nextLine();
		playerGuess.add(letterGuess.charAt(0));
		
		return word.contains(letterGuess);
	}

	private static boolean printWordState(String word, List<Character> playerGuess) {
		int correctCount=0;
		for(int i=0 ; i<word.length();i++) {
			if(playerGuess.contains(word.charAt(i))) {
				System.out.print(word.charAt(i));
				correctCount++;
			}
			else {
				System.out.print("-");
			}
			
		}
		System.out.println();
		return (word.length()== correctCount);
	}
}
