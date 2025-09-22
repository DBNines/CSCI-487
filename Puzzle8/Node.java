import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Node {
    public int[][] gameState;

    public Node() {
        gameState = new int[3][3];
    }

    public void initGame() {
        Integer[] values = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
        int counter = 0;
        Collections.shuffle(Arrays.asList(values), new Random(29234234));
        for (int i = 0; i < gameState.length; i++) {
            for (int j = 0; j < gameState[i].length; j++) {
                gameState[i][j] = values[counter];
                counter++;
            }
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

    // Checks if the empty spot is in the top row
    public boolean isEmptyInTopRow() {
        for (int position : gameState[0]) {
            if (position == 0) {
                return true;
            }
        }
        return false;
    }

    // Checks if the empty spot is in the bottom row
    public boolean isEmptyInBottomRow() {
        for (int position : gameState[2]) {
            if (position == 0) {
                return true;
            }
        }
        return false;
    }

    // Checks if the empty spot is in the left column
    public boolean isEmptyInLeftCol() {
        for (int[] row : gameState) {
            if (row[0] == 0) {
                return true;
            }
        }
        return false;
    }

    // Checks if the empty spot is in the right column
    public boolean isEmptyInRightCol() {
        for (int[] row : gameState) {
            if (row[2] == 0) {
                return true;
            }
        }
        return false;
    }
}
