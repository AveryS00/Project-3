import java.util.*;
import java.io.File;
import java.io.IOException;

public class IMDBGraphImpl implements IMDBGraph {
	final Scanner _actorScanner;
	final Scanner _actressScanner;
	final Map<String, IMDBNode> _actorGraph;
	final Map<String, IMDBNode> _movieGraph;

	public IMDBGraphImpl(String actorsFileName, String actressesFileName) throws IOException {
		_actorScanner = new Scanner(new File(actorsFileName), "ISO-8859-1");
		_actressScanner = new Scanner(new File(actressesFileName), "ISO-8859-1");
		_actorGraph = new HashMap<String, IMDBNode>();
		_movieGraph = new HashMap<String, IMDBNode>();
		parseData(_actorScanner);
		parseData(_actressScanner);
	}

	/**
	 * TODO uses the given IMDB files and filters out information such as
	 * actors, actresses and movies and places them into nodes which are then
	 * put into a graph in ArrayList format.
	 */
	private void parseData(Scanner scanner) {
		boolean beginParse = false;
		IMDBNode newActor = null;
		while (scanner.hasNextLine()) {

			String currentLine = scanner.nextLine();
			//skips to the line right before the list of actors begins, 
			//identifies when parsing should begin through the beginParse boolean
			if (currentLine.equals("----			------")) {
				beginParse = true;
			} else if (currentLine.equals("-----------------------------------------------------------------------------")) {
				beginParse = false;
			}
			//checks to see if it is time to being or continue parsing code
			//checks to see if the current line includes an actor 
			else if (beginParse == true && !currentLine.equals("")) {
				//checks to see if the currentLine is a movie-only line
				if (!currentLine.substring(0, 1).equals("\t")) {
					//checks to see if the current actor already exists and has only tv movies/ tv shows.
					//if so, it is removed from the graph of actors
					if (newActor != null && newActor.getNeighbors().isEmpty()) {
						_actorGraph.remove(newActor.getName());
					}
					newActor = new IMDBNode(currentLine.substring(0, currentLine.indexOf("\t")));
					_actorGraph.put(newActor.getName(), newActor);
					currentLine = currentLine.substring(currentLine.indexOf("\t"));
					//cleans up a line that does not include an actor causing currentLine to begin with the movie name
					while (currentLine.contains("\t")) {
						currentLine = currentLine.replaceFirst("\t", "");
					}
					//checks and skips over tv shows
					if (!currentLine.contains("(TV)")
							&& !currentLine.substring(0, 1).contains("\"")) {
						IMDBNode newMovie = new IMDBNode(
								currentLine.substring(0, currentLine.indexOf(")") + 1));
						newMovie.addNeighbor(newActor);
						//checks to see if the movie exits in the graph, 
						//adds the node to the graph if it does and links newActor and newMovie
						if (_movieGraph.get(newMovie.getName()) == null) {
							_movieGraph.put(newMovie.getName(), newMovie);
							newActor.addNeighbor(newMovie);
						} else {
							_movieGraph.get(newMovie.getName()).addNeighbor(newActor);
							newActor.addNeighbor(_movieGraph.get(newMovie.getName()));
						}
					}
				}// case for if it is a movie line 
				else {
					while (currentLine.contains("\t")) {
						currentLine = currentLine.replaceFirst("\t", "");
					}
					//checks to see if it is a tv movie/show
					if (!currentLine.contains("(TV)")
							&& !currentLine.substring(0, 1).contains("\"")) {
						IMDBNode newMovie = new IMDBNode(
								currentLine.substring(0, currentLine.indexOf(")") + 1));
						//checks to see if the movie exits in the graph, 
						//adds the node to the graph if it does and links newActor and newMovie
						if (_movieGraph.get(newMovie.getName()) == null) {
							_movieGraph.put(newMovie.getName(), newMovie);
							newActor.addNeighbor(newMovie);
						} else {
							_movieGraph.get(newMovie.getName()).addNeighbor(newActor);
							newActor.addNeighbor(_movieGraph.get(newMovie.getName()));
						}
					}
				}
			}
		}
	}

	public Collection<? extends Node> getActors() {
		return _actorGraph.values();
	}

	public Collection<? extends Node> getMovies() {
		return _movieGraph.values();
	}

	public Node getActor(String name) {
		return _actorGraph.get(name);
	}

	public Node getMovie(String name) {
		return _movieGraph.get(name);
	}

}