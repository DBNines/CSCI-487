import java.util.HashMap;
import java.util.PriorityQueue;

public class AStar {
    public static String goalState = "123456780";
    public static HashMap<String, Integer> visitedStates = new HashMap<String, Integer>(); // Closed List
    public static int nodeCount = 0;
    // Open List, sorts best possible option to top.
    // Get node cost adds the depth (current cost) + number of squares off (future
    // cost) to get a heuristic function.
    public static PriorityQueue<Node> fringe = new PriorityQueue<Node>(
            (Node node1, Node node2) -> Integer.compare(node1.getNodeCost(goalState), node2.getNodeCost(goalState)));
    public static int seed = 9;

    public static void main(String[] args) {
        Node rootNode = new Node(seed);
        fringe.add(rootNode);

        while (fringe.size() != 0) { // While we have nodes to eval
            nodeCount++;
            Node currNode = fringe.remove(); // Get best node (lowest score)

            visitedStates.put(currNode.nodeToString(), currNode.depth); // Record best so far
            System.out.println("STATS: DEPTH=" + currNode.depth + " Nodes Visited =" + nodeCount);

            if (currNode.isSolution(goalState)) { // Check if the node is a solution
                System.out.println("Found solution: Depth= " + currNode.depth);
                currNode.pathToParent();
                break;
            }
            currNode.createChildren(); // Generate all possible child nodes

            for (Node child : currNode.childrenNodes) {
                // Only expand if it's new or a better path
                if (!visitedStates.containsKey(child.nodeToString())
                        || child.depth < visitedStates.get(child.nodeToString())) { // Go forward if only the closed
                                                                                    // list does not contain this child
                    fringe.add(child); // Only add this child if has not been already visited OR it has a better value
                                       // than the already visited one
                }
            }
        }
        rootNode.printState();
    }
}
