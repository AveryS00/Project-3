import java.util.*;

public class IMDBNode implements Node {
	private String _name;
	private List<Node> _neighbors;
	
	public IMDBNode(String name, List<Node> neighbors) {
		_name = name;
		_neighbors = neighbors;
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
