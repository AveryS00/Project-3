import java.util.*;

public class ActorNode implements Node {
	private String _name;
	private List<MovieNode> _neighbors;
	
	public ActorNode(String name) {
		_name = name;
		_neighbors = new ArrayList<MovieNode>();
	}
	
	public String getName() {
		return _name;
	}
	
	public Collection<MovieNode> getNeighbors() {
		return _neighbors;
	}
	
	public void addNeighbor(MovieNode node) {
		_neighbors.add(node);
	}
}
