
/**
 *
 * @author Azinm
 */
import java.util.Scanner;

public class TurtleGraphics {

    public static final int FLOOR_LENGTH = 20;
    public static final int COMMANDS_LENGTH = 100;
    public static int[][] floor = new int[FLOOR_LENGTH][FLOOR_LENGTH];
    public static int[][] commands = new int[COMMANDS_LENGTH][2];
    public enum Direction {WEST_EAST,NORTH_SOUTH,EAST_WEST,SOUTH_NORTH};   
     

    public static void main(String[] args) {
        
        getingCommands();       
        boolean prtintFloor = false;
        if (declaringFloor(prtintFloor))
            printingFloor();
    }
    
    
    //function for setting commands array
    public static void getingCommands(){
        
        int count =0;
        int inputCommand=0;
        
        Scanner input = new Scanner(System.in);
        
        while (inputCommand!=9 && count <COMMANDS_LENGTH){
          
            System.out.printf( "Enter command (9 to end input): ");
            inputCommand = input.nextInt();       
            commands[count][0]=inputCommand;
            
            if (inputCommand==5) {              
                System.out.print( "Enter forward spaces: " );
                commands[count][1]=input.nextInt();
            }
            
            count++;
        }
        
        System.out.println();
        
    }
    
    
    //function for declaring floor array
    public static boolean declaringFloor(boolean prtintFloor){
        
        int nextStep;
        prtintFloor = false;
        boolean penDown = false;
        int row=0 , column = 0;
        //variables for finding direction
        int direction = 1;
        Direction directionStep = Direction.WEST_EAST;

        for (short i = 0 ; i< COMMANDS_LENGTH ; i++){
            
            switch (commands[i][0]) {
                case 1:
                    penDown = false;
                    break;
                case 2:
                    penDown = true;
                    break;
                case 3:
                    direction++;
                    break;
                case 4:
                    direction--;
                    break;
                case 6:
                    prtintFloor = true;
                    break;
                case 9:
                default:
                    break;
            }
                        
            //to handle the direction exception & keep it between 1 and 4           
            if (direction >=1 && direction<=4) {
            
                switch (direction) {
                    case 1 -> directionStep = Direction.WEST_EAST;
                    case 2 -> directionStep = Direction.NORTH_SOUTH;
                    case 3 -> directionStep = Direction.EAST_WEST;
                    case 4 -> directionStep = Direction.SOUTH_NORTH;
                    default -> {
                    }
                }
                
                if (commands[i][0]==5){
                
                    switch (directionStep) {
                        case WEST_EAST -> {
                            nextStep = commands[i][1];
                            if (penDown)
                                for (int j = 1 ; j <= nextStep ; j++,column++)
                                    floor[row][column]= 1;
                            else
                                for (int j = 1 ; j <= nextStep ; j++)
                                    column++;
                        }
                        case NORTH_SOUTH -> {
                            nextStep = commands[i][1];
                            if (penDown)
                                for (int j = 1 ; j <= nextStep ; j++,row++)
                                    floor[row][column]= 1;
                            else
                                for (int j = 1 ; j <= nextStep ; j++)
                                    row++;
                        }
                        case EAST_WEST -> {
                            nextStep = commands[i][1];
                            if (penDown)
                                for (int j = 1 ; j <= nextStep ; j++,column--)
                                    floor[row][column]= 1;
                            else
                                for (int j = 1 ; j <= nextStep ; j++)
                                    column--;
                        }
                        default -> {
                            nextStep = commands[i][1];
                            if (penDown)
                                for (int j = 1 ; j <= nextStep ; j++,row--)
                                    floor[row][column]= 1;
                            else
                                for (int j = 1 ; j <= nextStep ; j++)
                                    row--;
                        }
                    }                    
                } 
            }
            else {
                System.out.print("""
                                 
                                 You have given the wrong direction.
                                 Please try again.
                                 
                                 """);
                prtintFloor = false;
                break;
            }              
        }
        return prtintFloor;
    }
    
 
    //function for printing floor array (turtle movements)
    public static void printingFloor(){
    
        System.out.printf("The Drawing is:\n\n");
        
        for (short i = 0 ; i< FLOOR_LENGTH ; i++){
            for (short j = 0 ; j< FLOOR_LENGTH ; j++) {
                
                if (floor[i][j] == 1)
                    System.out.printf("*");
                else
                    System.out.printf(" ");
                    
            }
            System.out.println();
        }
    }    
}