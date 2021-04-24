package algorithms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import sections.board.GridPanel;
import sections.board.Node;

/**
 * The following AStar class conducts the A* pathfinding algorithm on a given
 * grid, displaying the shortest path from a start point to an end point.
 * 
 * @author Troy Zada
 *
 */

public class AStar extends Algorithm {

    private ArrayList<Node> openList; // Open Nodes
    private ArrayList<Node> closedList; // Closed Nodes

    /**
     * This constructor conducts an A* Search on the provided grid, where the
     * solution can either be viewed as it unfolds or just the final answer will be
     * displayed.
     * 
     * @param mainGrid  - The board that will have BFS conducted on it.
     * @param start     - The source Node where BFS will begin.
     * @param showSteps - Whether the solution can be seen or just final answer.
     */
    public AStar(GridPanel mainGrid, Node start, boolean showSteps) {
	this.mainGrid = mainGrid;
	this.start = start;
	this.end = mainGrid.getEndNode();
	this.parents = new HashMap<>();
	this.openList = new ArrayList<>();
	this.closedList = new ArrayList<>();

	// Calculates g, h, and f for start point (where g = 0) and adds Node to open
	Node current = this.start;
	current.setGCost(0);
	current.setHCost(calcHDistance(current, end));
	current.setFCost(calcFValue(current.getGCost(), current.getHCost()));
	openList.add(current);

	// Display solution in 1 of 2 ways, depending on showSteps boolean
	if (showSteps) { // Using a timer that renders a Node every 0.01s until End Node is reached
	    Timer timer = new Timer(10, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    Node current = getLowestF(end); // Make current the Node with lowest F value
		    solveUsingAStar(current, end, showSteps);

		    // Displays specific solution (either MAGENTA path or "no solution" dialog box)
		    showSolutionOutputWithSteps(openList.isEmpty(), e, current.equals(end));
		}
	    });
	    timer.start();
	} else { // Using a while loop that doesn't render anything except the solution path
	    // While End Node hasn't been reached yet and there's still Nodes to view
	    while (!current.equals(end) && !openList.isEmpty()) {
		current = getLowestF(end); // Make current the Node with lowest F value
		solveUsingAStar(current, end, showSteps);
	    }
	    // Displays specific solution (either MAGENTA path or "no solution" dialog box)
	    showSolutionOutputWithoutSteps(openList.isEmpty());
	}
    }

    private void solveUsingAStar(Node current, Node end, boolean showSteps) {
	visuallyCloseNode(current, showSteps);

	// For every node adjacent to u
	for (Node v : mainGrid.getAdjacencyNodes(current)) {
	    // If Node not in open or closed Lists, then add to open
	    if (!openList.contains(v) && !closedList.contains(v)) {
		openList.add(v);
		visuallyOpenNode(v, showSteps);
	    }
	    // Do g, h, and f calculations for Node v
	    double currentG = calcGDistance(v, current); // G
	    v.setHCost(calcHDistance(v, end));
	    double currentF = calcFValue(currentG, v.getHCost()); // F

	    // If new F value is less than the Node's existing F value, then update existing
	    // f and parent
	    if (currentF < v.getFCost()) {
		v.setGCost(currentG);
		v.setFCost(currentF);
		parents.put(v, current);
	    }

	}
	// Add current Node to closed List and remove from open
	closedList.add(current);
	openList.remove(current);
    }

    // Calculates actual distance from start, g (finds distance between current Node
    // and start Node including all present obstacles)
    private double calcGDistance(Node current, Node previous) {
	return previous.getGCost() + calculateActualDistance(current, previous);
    }

    // Calculates heuristic distance, h (finds distance between current Node
    // and end Node assuming that no obstacles exist)
    private double calcHDistance(Node current, Node end) {
	return calculateActualDistance(current, end);
    }

    // Calculates heuristic distance, h (finds distance between current Node
    // and end Node assuming that no obstacles exist)
    private double calcFValue(double g, double h) {
	return g + h;
    }

    // Find Node with lowest F value
    private Node getLowestF(Node end) {
	// Default lowestNodeF to be end Node
	Node lowestNodeF = end;
	double lowestFVal = end.getFCost();
	// Go through all Nodes
	for (Node n : openList) {
	    if (n.getFCost() < lowestFVal) {
		lowestNodeF = n;
		lowestFVal = n.getFCost();
	    }
	}
	return lowestNodeF;
    }

}
