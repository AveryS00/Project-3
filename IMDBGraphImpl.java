import java.util.*;
import java.io.File;
import java.io.IOException;

public class IMDBGraphImpl implements IMDBGraph {
	final Scanner _actorScanner;
	final Scanner _actressScanner;
	final List<Node> _actorGraph;
	final List<Node> _movieGraph;
	
	public IMDBGraphImpl(String actorsFileName, String actressesFileName) throws IOException {
		_actorScanner = new Scanner(new File(actorsFileName), "ISO-8859-1");
		_actressScanner = new Scanner(new File(actressesFileName));
		_actorGraph = new ArrayList<Node>();
		_movieGraph = new ArrayList<Node>();
		parseData();
	}
	
	/**
	 * TODO
	 * uses the given IMDB files and filters out information such as actors, actresses and movies
	 * and places them into nodes which are then put into a graph in ArrayList format.
	 */
	private void parseData() {
		
	}
	
	public Collection<? extends Node> getActors() {
		return _actorGraph;
	}
	
	public Collection<? extends Node> getMovies() {
		return _movieGraph;
	}
	
	public Node getActor(String name) {
		return getNode(_actorGraph, name);
	}

	public Node getMovie(String name) {
		return getNode(_movieGraph, name);
	}
	
	/**
	 * Abstract method getNode, finds a node with a specified name in a list and returns that node
	 * @param graph | the graph to search through
	 * @param name | the name of the node to search for
	 * @return the full node with the name given in parameter
	 */
	private static Node getNode(List<Node> graph, String name) {
		Node node = null;
		for (int i = 0; i < graph.size(); i++) {
			if (name.equals(graph.get(i).getName())) {
				node = graph.get(i);
			}
		}
		return node;
	}
}
