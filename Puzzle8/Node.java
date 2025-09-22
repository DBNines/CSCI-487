public class Node {
    public int[][] gameState;

    public Node() {
        gameState = new int[3][3];
    }

    public void initGame() {
        gameState[0][0] = 1;
        gameState[0][1] = 2;
        gameState[0][2] = 3;
        gameState[1][0] = 4;
        gameState[1][1] = 0;
        gameState[1][2] = 5;
        gameState[2][0] = 6;
        gameState[2][1] = 7;
        gameState[2][2] = 8;
    }

    public void printState() {
        for (int[] row : gameState) {
            for (int positionNumber : row) {
                System.out.print(positionNumber + "|");
            }
            System.out.println('\n' + "------");
        }
    }
}
