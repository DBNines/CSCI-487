import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Node {
    public int[][] gameState;
    public int depth;
    public String creationMove;
    public List<Node> childrenNodes = new ArrayList<Node>();
    public Node parentNode;

    // Constructor for root node
    public Node(int seed) {
        this.parentNode = null;
        this.depth = 0;
        this.creationMove = "ROOT";
        gameState = new int[3][3]; // Creates an empty game state
        initGame(seed);
    }

    // Constructor for child nodes, pass in parent and state of game
    public Node(Node parent, int[][] state, int depth, String move) {
        this.parentNode = parent;
        this.gameState = state;
        this.depth = depth;
        this.creationMove = move;
    }

    public void initGame(int seed) {
        Integer[] values = { 1, 2, 3, 4, 5, 6, 7, 8, 0 };
        int counter = 0;
        Collections.shuffle(Arrays.asList(values), new Random(seed));
        for (int i = 0; i < gameState.length; i++) {
            for (int j = 0; j < gameState[i].length; j++) {
                gameState[i][j] = values[counter];
                counter++;
            }
        }
    }

    public String nodeToString() {
        StringBuilder nodeString = new StringBuilder();
        for (int[] row : gameState) {
            for (int positionNumber : row) {
                nodeString.append(positionNumber);
            }
        }
        return nodeString.toString();
    }

    // Finds if the current state matches the goal state
    public boolean isSolution(String solutionState) {
        return this.nodeToString().equals(solutionState);
    }

    // Give a count of how many tiles are NOT where they should be.
    public int numOfMisplaced(String solutionState) {
        int misplacedCounter = 0;
        for (int i = 0; i < solutionState.length(); i++) {
            if (solutionState.charAt(i) != this.nodeToString().charAt(i)) {
                misplacedCounter++;
            }
        }
        return misplacedCounter;
    }

    public int getNodeCost(String solutionState) {
        return this.depth + this.numOfMisplaced(solutionState);
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
            childrenNodes.add(new Node(this, newState, this.depth + 1, "UP"));
        }
        // Swap Downwards
        if (!isZeroInBottomRow()) { // Make sure it's allowed to swap
            int[][] newState = getCopyOfState();
            newState[emptyRow][emptyCol] = newState[emptyRow + 1][emptyCol];
            newState[emptyRow + 1][emptyCol] = 0;
            childrenNodes.add(new Node(this, newState, this.depth + 1, "DOWN"));
        }
        // Swap Left
        if (!isZeroInLeftCol()) { // Make sure it's allowed to swap
            int[][] newState = getCopyOfState();
            newState[emptyRow][emptyCol] = newState[emptyRow][emptyCol - 1];
            newState[emptyRow][emptyCol - 1] = 0;
            childrenNodes.add(new Node(this, newState, this.depth + 1, "LEFT"));
        }
        // Swap Right
        if (!isZeroInRightCol()) { // Make sure it's allowed to swap
            int[][] newState = getCopyOfState();
            newState[emptyRow][emptyCol] = newState[emptyRow][emptyCol + 1];
            newState[emptyRow][emptyCol + 1] = 0;
            childrenNodes.add(new Node(this, newState, this.depth + 1, "RIGHT"));
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
        System.out.println('\n');
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

    // Prints the path from this node to the parent.
    public void pathToParent() {
        // this.printState();
        System.out.println("MOVE: " + this.creationMove);
        if (this.parentNode != null) {
            this.parentNode.pathToParent();
        }
    }
}
