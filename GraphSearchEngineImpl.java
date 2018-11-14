import java.util.*;

public class GraphSearchEngineImpl implements GraphSearchEngine{
	
	//TODO test and write up comments
	public List<Node> findShortestPath(Node s, Node t) {
		List<Node> visitedNodes = new ArrayList<Node>();
		Queue<Node> nodesToVisit = new LinkedList<Node>();
		//Map of distances from the root, key being the node called, value being distance
		Map<Node, Integer> distanceFromS = new HashMap<Node, Integer>();
		List<Node> path = null;
		
		nodesToVisit.add(s);
		distanceFromS.put(s, 0);
		while (nodesToVisit.size() < 0) {
			Node n = nodesToVisit.poll();
			visitedNodes.add(n);
			if (n.equals(s)) {
				path = new ArrayList<Node>();
				for (int i = 0; i < distanceFromS.get(n); i++) {
					path.add(n);
					for (Node j : n.getNeighbors()) {
						if (visitedNodes.contains(j) && distanceFromS.get(j) == distanceFromS.get(n) - 1) {
							n = j;
							break;
						}
					}
				}
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
		return path;
	}
}
