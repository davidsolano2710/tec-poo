package tec.poo.tareas;

import java.util.*;


public class BattleshipGame 
{
    private static final int BOARD_SIZE = 7;
    private Battleship[] battleshipBoard;
    private int numberOfGuesses;
    private static int [] guesses = new int[BOARD_SIZE];
    private static int pos2 = 0;

    public static void main( String[] args ){

        

        // game setup
        var battleshipGame = new BattleshipGame();
        battleshipGame.gameSetup();

        int i = 0;

        Scanner scanner = new Scanner(System.in);
        
        while(i < BOARD_SIZE){ 

            int guess = getUserInput("Enter a guess: ", scanner, guesses, pos2);
            
            var battleship = battleshipGame.getBattleshipFromGuess(guess);
            var status = battleshipGame.verifyBattleshipStatus(battleship);
            System.out.println(status);
            if (battleshipGame.verifyAllShipsAreDead(battleship)){
                break;
            }

            i++;
        }
        
        System.out.println("You took " + battleshipGame.numberOfGuesses + " guesses.");
    }

    private boolean verifyAllShipsAreDead(Battleship battleship) {
        if (battleship == null){
            return false;
        }
        return battleship.isDead();
    }

    public static int getUserInput(String prompt, Scanner scanner, int[] guesses, int pos2){
        int guess = 0;
        while(true){
            System.out.println(prompt);
            guess = scanner.nextInt();
            int pos = 0;
            while(pos<BOARD_SIZE){
                if(guesses[pos]==guess){
                    System.out.println("Already asked for this slot, try again");
                    pos++;
                    continue;
                }
                pos++;
            }
            if(guess>=1&&guess<=7){
                guesses[pos2] = guess;
                pos2++;
                break;
            }
            System.out.println("The " + guess + " slot doesn't exist, try again with a number between 1 and 7.");
            }
        return guess;
    }

    public void gameSetup(){
        this.battleshipBoard = new Battleship[BOARD_SIZE];
        this.numberOfGuesses = 0;

        //generates random number from 0 to 4
        Random rand = new Random();
        int pos = rand.nextInt(5);

        var poniez = new Battleship();
        poniez.setName("Poniez");
        poniez.setSize(3);

        this.battleshipBoard[pos] = poniez;
        this.battleshipBoard[(pos+1)] = poniez;
        this.battleshipBoard[(pos+2)] = poniez;
        
    }

    public void incrementNumberOfGuess(){
        this.numberOfGuesses++;
    }

    public String verifyBattleshipStatus(Battleship battleship){
        var status = "";
        if (battleship != null){
            status =  "Hit!";
            battleship.hit();
            if (battleship.isDead()){
                status = "Kill!";;
            }
        } else {
            status = "Miss";
        }
        
        this.incrementNumberOfGuess();
        return status;
    }

    public Battleship getBattleshipFromGuess(int guess){
        // validacion 
        return this.battleshipBoard[guess-1];
    }
}
