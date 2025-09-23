import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static String goalState = "123456780";
    public static HashSet<String> visitedStates = new HashSet<String>(); //Closed List
    public static int seed = 9;
    public static int nodeCount = 0;
    public static boolean foundSolution = false;
    public static Queue<Node> fringe = new LinkedList<Node>(); //Open List
    public static void main(String[] args){
        //Create the root node and add it to the fringe
        Node rootNode = new Node(seed);
        fringe.add(rootNode);
        visitedStates.add(rootNode.nodeToString());
        while (!foundSolution){
            nodeCount++;
            if(fringe.size() == 0){ //If the fringe is empty, there are no more nodes to visit. We have failed.
                System.out.println("ERROR: Failed to find solution");
                break;
            }
            Node currNode = fringe.remove(); //Examine a node
            System.out.println("STATS: Depth=" + currNode.depth + " Nodes Expanded=" + nodeCount);
            if(currNode.isSolution(goalState)){ //Check if the node is a solution
                foundSolution = true;
                System.out.println("Found solution: Depth= " + currNode.depth);
                currNode.pathToParent();
                break;
            }
            currNode.createChildren(); //Create all possible children nodes
            for (Node child: currNode.childrenNodes){ //For every child do
                if(!visitedStates.contains(child.nodeToString())){ //If we have already not visited this child node
                    visitedStates.add(child.nodeToString()); // Add this node to list of visited nodes
                    fringe.add(child); //Add this unvisited child to the node
                }
            }
        }
        System.out.println("Inital State was: ");
        rootNode.printState();
    }
}
