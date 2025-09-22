import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Node {
    public int[][] gameState;
    private int seed = 2924234;
    public List<Node> childrenNodes = new ArrayList<Node>();
    public Node parentNode;

    // Constructor for root node
    public Node() {
        gameState = new int[3][3]; // Creates an empty game state
    }

    // Constructor for child nodes, pass in parent and state of game
    public Node(Node parent, int[][] state) {
        this.parentNode = parent;
        this.gameState = state;
        printState();
    }

    public void initGame() {
        Integer[] values = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
        int counter = 0;
        Collections.shuffle(Arrays.asList(values), new Random(seed));
        for (int i = 0; i < gameState.length; i++) {
            for (int j = 0; j < gameState[i].length; j++) {
                gameState[i][j] = values[counter];
                counter++;
            }
        }
    }

    // Creates children for every viable swap move with the empty space
    public void createChildren() {
        int emptyRow = findZero()[0];
        int emptyCol = findZero()[1];
        // Swap Upwards
        if (!isZeroInTopRow()) { // Make sure it's allowed to swap
            int[][] newState = getCopyOfState();
            newState[emptyRow][emptyCol] = newState[emptyRow - 1][emptyCol];
            newState[emptyRow - 1][emptyCol] = 0;
            childrenNodes.add(new Node(this, newState));
        }
        // Swap Downwards
        if (!isZeroInBottomRow()) { // Make sure it's allowed to swap
            int[][] newState = getCopyOfState();
            newState[emptyRow][emptyCol] = newState[emptyRow + 1][emptyCol];
            newState[emptyRow + 1][emptyCol] = 0;
            childrenNodes.add(new Node(this, newState));
        }
        // Swap Left
        if (!isZeroInLeftCol()) { // Make sure it's allowed to swap
            int[][] newState = getCopyOfState();
            newState[emptyRow][emptyCol] = newState[emptyRow][emptyCol - 1];
            newState[emptyRow][emptyCol - 1] = 0;
            childrenNodes.add(new Node(this, newState));
        }
        // Swap Right
        if (!isZeroInRightCol()) { // Make sure it's allowed to swap
            int[][] newState = getCopyOfState();
            newState[emptyRow][emptyCol] = newState[emptyRow - 1][emptyCol + 1];
            newState[emptyRow][emptyCol + 1] = 0;
            childrenNodes.add(new Node(this, newState));
        }
    }

    // Prints the current state of the game in a 3x3 grid
    public void printState() {
        for (int[] row : gameState) {
            for (int positionNumber : row) {
                System.out.print(positionNumber + "|");
            }
            System.out.println('\n' + "------");
        }
    }

    // Returns row and column position of where the empty spot in
    private int[] findZero() {
        int location[] = new int[2];
        for (int rowIndex = 0; rowIndex < gameState.length; rowIndex++) {
            for (int colIndex = 0; colIndex < gameState[rowIndex].length; colIndex++) {
                if (gameState[rowIndex][colIndex] == 0) {
                    location[0] = rowIndex;
                    location[1] = colIndex;
                    return location;
                }
            }
        }
        System.out.println("Error: Could not find empty spot.");
        return null;
    }

    // Return a copy of the current state, which can be used to make a new node
    private int[][] getCopyOfState() {
        int[][] copyState = new int[3][3];
        for (int i = 0; i < gameState.length; i++) {
            copyState[i] = gameState[i].clone();
        }
        return copyState;
    }

    // Checks if the empty spot is in the top row
    private boolean isZeroInTopRow() {
        for (int position : gameState[0]) {
            if (position == 0) {
                return true;
            }
        }
        return false;
    }

    // Checks if the empty spot is in the bottom row
    private boolean isZeroInBottomRow() {
        for (int position : gameState[2]) {
            if (position == 0) {
                return true;
            }
        }
        return false;
    }

    // Checks if the empty spot is in the left column
    private boolean isZeroInLeftCol() {
        for (int[] row : gameState) {
            if (row[0] == 0) {
                return true;
            }
        }
        return false;
    }

    // Checks if the empty spot is in the right column
    private boolean isZeroInRightCol() {
        for (int[] row : gameState) {
            if (row[2] == 0) {
                return true;
            }
        }
        return false;
    }
}
