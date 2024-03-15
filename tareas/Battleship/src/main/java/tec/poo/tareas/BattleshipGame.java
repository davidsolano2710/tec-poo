package tec.poo.tareas;

import java.util.*;


public class BattleshipGame 
{
    private static final int BOARD_SIDE_SIZE = 7;
    private Battleship[][] battleshipBoard;
    private int numberOfGuesses;
    private static String [] guesses = new String[BOARD_SIDE_SIZE*BOARD_SIDE_SIZE+1];
    private int pos2 = 0;
    private int shipsAlive = 3;

    public static void main( String[] args ){

        // game setup
        var battleshipGame = new BattleshipGame();
        Random random = new Random();

        int ori1 = random.nextInt(2);
        int ori2 = random.nextInt(2);
        int ori3 = random.nextInt(2);

        var poniez = new Battleship();
        poniez.setName("Poniez");
        poniez.setSize(3);
        poniez.setOrientation(ori1);

        Battleship yamato = new Battleship();
        yamato.setName("Yamato");
        yamato.setSize(3);
        yamato.setOrientation(ori2);

        Battleship uss_iowa = new Battleship();
        uss_iowa.setName("USS Iowa");
        uss_iowa.setSize(3);
        uss_iowa.setOrientation(ori3);

        battleshipGame.gameSetup(battleshipGame, poniez, yamato, uss_iowa);
        
        int i = 0;

        Scanner scanner = new Scanner(System.in);
        
        while(i < BOARD_SIDE_SIZE*BOARD_SIDE_SIZE){ 

            String guess = getUserInput("Enter a guess: ", scanner, guesses, battleshipGame);

            Battleship battleship = battleshipGame.getBattleshipFromGuess(guess);
            
            var status = battleshipGame.verifyBattleshipStatus(battleship);
            System.out.println(status);
            if (status.equals("Kill!")){
                battleshipGame.shipsAlive--;
                System.out.println("You sunk " + battleship.getName() + "!");
            }
            System.out.println(battleshipGame.shipsAlive);
            

            if (battleshipGame.shipsAlive == 0) {
                System.out.println("All ships sunk!");
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

    public static String getUserInput(String prompt, Scanner scanner, String[] guesses, BattleshipGame battleshipGame){
        String guess = "";
   
        String[] validRange = {"A1", "B1", "C1", "D1", "E1", "F1", "G1",
                               "A2", "B2", "C2", "D2", "E2", "F2", "G2",
                               "A3", "B3", "C3", "D3", "E3", "F3", "G3",
                               "A4", "B4", "C4", "D4", "E4", "F4", "G4",
                               "A5", "B5", "C5", "D5", "E5", "F5", "G5",
                               "A6", "B6", "C6", "D6", "E6", "F6", "G6",
                               "A7", "B7", "C7", "D7", "E7", "F7", "G7"};

        while(true){
            boolean repeated = false;


            System.out.println(prompt);
            guess = scanner.next();


            if(!Arrays.asList(validRange).contains(guess)){
                System.out.println("Invalid guess, try again.");
                continue;
            } else {
                if (!(guesses[0]==null)){
                    for (String str : guesses) {
                        if(str == null){
                            break;
                        } else if (str.equals(guess)) {
                            repeated = true;
                            System.out.println("Repeated guess, try again.");
                            break;
                        }
                    }
                    if(repeated){
                        continue;
                    } else {
                        guesses[battleshipGame.pos2] = guess;
                        battleshipGame.pos2++;
                    }
                } else {
                    guesses[0] = guess;
                    battleshipGame.pos2++;
                }
                
                break;
            }
        }
        return guess;
    }

    public void gameSetup(BattleshipGame battleshipGame, Battleship bs1, Battleship bs2, Battleship bs3){
        battleshipBoard = new Battleship[BOARD_SIDE_SIZE][BOARD_SIDE_SIZE];
        this.numberOfGuesses = 0;

        Random random = new Random();

        //ship1

        if (bs1.getOrientation()==0){
            int posx1 = random.nextInt(5);
            int posy1 = random.nextInt(7);
            this.battleshipBoard[ posx1][   posy1] = bs1;
            this.battleshipBoard[(posx1+1)][posy1] = bs1;
            this.battleshipBoard[(posx1+2)][posy1] = bs1;
        } else {
            int posx1 = random.nextInt(7);
            int posy1 = random.nextInt(5);
            this.battleshipBoard[posx1][   posy1]    = bs1;
            this.battleshipBoard[posx1][(  posy1+1)] = bs1;
            this.battleshipBoard[posx1][(  posy1+2)] = bs1;
        }
        //--------------------------------------------
        //ship2
        while(true){

            if(bs2.getOrientation()==0){
                int posx2 = random.nextInt(5);
                int posy2 = random.nextInt(7);
                if (this.battleshipBoard[posx2][posy2] == null &&
                this.battleshipBoard[(posx2+1)][posy2] == null && 
                this.battleshipBoard[(posx2+2)][posy2] == null){

                    this.battleshipBoard[ posx2][   posy2] = bs2;
                    this.battleshipBoard[(posx2+1)][posy2] = bs2;
                    this.battleshipBoard[(posx2+2)][posy2] = bs2;
                    break;
                }
            } else {
                int posx2 = random.nextInt(7);
                int posy2 = random.nextInt(5);
                if (this.battleshipBoard[posx2][posy2] == null &&
                this.battleshipBoard[posx2][(posy2+1)] == null && 
                this.battleshipBoard[posx2][(posy2+2)] == null){

                    this.battleshipBoard[posx2][ posy2]    = bs2;
                    this.battleshipBoard[posx2][(posy2+1)] = bs2;
                    this.battleshipBoard[posx2][(posy2+2)] = bs2;
                    break;
                }
            }
            
        }
        //--------------------------------------------
        //ship3
        while(true){

            if(bs3.getOrientation()==0){
                int posx3 = random.nextInt(5);
                int posy3 = random.nextInt(7);

                if (this.battleshipBoard[posx3][posy3] == null &&
                this.battleshipBoard[(posx3+1)][posy3] == null && 
                this.battleshipBoard[(posx3+2)][posy3] == null){

                    this.battleshipBoard[ posx3][   posy3] = bs3;
                    this.battleshipBoard[(posx3+1)][posy3] = bs3;
                    this.battleshipBoard[(posx3+2)][posy3] = bs3;
                    break;
                }
            } else {
                int posx3 = random.nextInt(7);
                int posy3 = random.nextInt(5);
                if (this.battleshipBoard[posx3][posy3] == null &&
                this.battleshipBoard[posx3][(posy3+1)] == null && 
                this.battleshipBoard[posx3][(posy3+2)] == null){

                    this.battleshipBoard[posx3][ posy3]    = bs3;
                    this.battleshipBoard[posx3][(posy3+1)] = bs3;
                    this.battleshipBoard[posx3][(posy3+2)] = bs3;
                    break;
                }
            }
        }

    }

    public void incrementNumberOfGuess(){
        this.numberOfGuesses++;
    }

    public Battleship getBattleshipFromGuess(String guess){
        // validacion 
        String y = guess.substring(0,1);
        String x = guess.substring(1);
        
        int yint = 0;
        int xint = Integer.parseInt(x);

        switch (y) {
            case "A":
                yint = 0;
                break;
            case "B":
                yint = 1;
                break;
            case "C":
                yint = 2;
                break;
            case "D":
                yint = 3;
                break;
            case "E":
                yint = 4;
                break;
            case "F":
                yint = 5;
                break;
            case "G":
                yint = 6;
                break;
            default:
                break;
        }

        return this.battleshipBoard[yint][(xint-1)];
        
    }

    public String verifyBattleshipStatus(Battleship battleship){
        var status = "";
        
        if (battleship != null){
            status =  "Hit!";
            battleship.hit();
            if (battleship.isDead()){
                status = "Kill!";
            }
        } else {
            status = "Miss";
        }
        
        this.incrementNumberOfGuess();
        return status;
    }
}
