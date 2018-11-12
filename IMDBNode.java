import java.util.*;

public class IMDBNode implements Node {
	private String _name;
	private List<Node> _neighbors;
	
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
	
	public void addNeighbor(Node node) {
		_neighbors.add(node);
	}
}
