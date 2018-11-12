import java.util.*;

public class MovieNode implements Node {
	private String _name;
	private List<ActorNode> _neighbors;
	
	public MovieNode(String name) {
		_name = name;
		_neighbors = new ArrayList<ActorNode>();
	}
	
	public String getName() {
		return _name;
	}
	
	public Collection<ActorNode> getNeighbors() {
		return _neighbors;
	}
	
	public void addNeighbor(ActorNode node) {
		_neighbors.add(node);
	}
}
