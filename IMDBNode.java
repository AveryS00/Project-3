import java.util.*;

public class IMDBNode implements Node {
	private String _name;
	private ArrayList<Node> _neighbors;
	
	public IMDBNode(String name) {
		_name = name;
		_neighbors = new ArrayList<Node>();
	}
	
	public String getName() {
		return _name;
	}
	
	public Collection<Node> getNeighbors() {
		return _neighbors;
	}
	
	/**
	 * Used when parsing, adds the specified parameter node to the list of neighbors for this node.
	 * @param node the node to be added to the called upon node's neighbors
	 */
	public void addNeighbor(Node node) {
		_neighbors.add(node);
	}
}
