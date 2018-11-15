import java.util.*;

public class GraphSearchEngineImpl implements GraphSearchEngine{
	
	public List<Node> findShortestPath(Node s, Node t) {
		List<Node> visitedNodes = new ArrayList<Node>();
		//Queue of nodes to visit in order
		Queue<Node> nodesToVisit = new LinkedList<Node>();
		//Map of distances from the root, key being the node called, value being distance
		Map<Node, Integer> distanceFromS = new HashMap<Node, Integer>();
		List<Node> path = null;
		
		nodesToVisit.add(s);
		distanceFromS.put(s, 0);
		while (nodesToVisit.size() > 0) {
			//grabs new node from front of queue
			Node n = nodesToVisit.poll();
			visitedNodes.add(n);
			//check if current node is the one being searched for
			if (n.equals(t)) {
				//Start Backtrack
				path = new ArrayList<Node>();
				int distance = distanceFromS.get(n);
				//Loop for how far away from the root the current node is minus 1
				for (int i = 0; i < distance; i++) {
					path.add(n);
					for (Node j : n.getNeighbors()) {
						//check neighbors for a node with a distance 1 less than current distance then go to it
						if (visitedNodes.contains(j) && distanceFromS.get(j) == distanceFromS.get(n) - 1) {
							n = j;
							break;
						}
					}
				}
				//add root to path
				path.add(s);
				break;
			} else {
				//add all node neighbors to the nodesToVisit queue
				for (Node k : n.getNeighbors()) {
					if (!visitedNodes.contains(k)) {
						nodesToVisit.add(k);
						distanceFromS.put(k, distanceFromS.get(n) + 1);
					}
				}
			}
		}
		//reverse order of backtrack if not null or else just return null if not found
		if (path != null)
			Collections.reverse(path);
		return path;
	}
}
