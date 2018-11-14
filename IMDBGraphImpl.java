import java.util.*;
import java.util.regex.Pattern;
import java.io.File;
import java.io.IOException;

public class IMDBGraphImpl implements IMDBGraph {
	final Scanner _actorScanner;
	final Scanner _actressScanner;
	final Map<String, IMDBNode> _actorGraph;
	final Map<String, IMDBNode> _movieGraph;

	public IMDBGraphImpl(String actorsFileName, String actressesFileName) throws IOException {
		_actorScanner = new Scanner(new File(actorsFileName), "ISO-8859-1");
		_actressScanner = new Scanner(new File(actressesFileName));
		_actorGraph = new HashMap<String, IMDBNode>();
		_movieGraph = new HashMap<String, IMDBNode>();
		parseData();
	}

	/**
	 * TODO uses the given IMDB files and filters out information such as
	 * actors, actresses and movies and places them into nodes which are then
	 * put into a graph in ArrayList format.
	 */
	private void parseData() {
		while (_actorScanner.hasNextLine()) {

			final String currentActorString = _actorScanner.nextLine();
			// final String currentActressString = _actressScanner.nextLine();
			if (currentActorString.equals("THE ACTORS LIST")) {
				IMDBNode newActor = new IMDBNode(currentActorString.substring(0, currentActorString.indexOf(" ")));
				_actorGraph.put(newActor.getName(), newActor);
				IMDBNode newMovie = new IMDBNode(
						currentActorString.substring(currentActorString.indexOf(" "), currentActorString.indexOf(")")));
				if (!_movieGraph.containsValue(newMovie))
					_movieGraph.put(newMovie.getName(), newMovie);
				else
					_movieGraph.get(newMovie.getName()).addNeighbor(newActor);
				if (_actorScanner.nextLine().contains("\t")) {
					String newMovieString = _actorScanner.nextLine();
					while (newMovieString.contains("\t")) {
						newMovieString = newMovieString.replaceFirst("\t", "");
					}
					if (!newMovieString.contains("(TV)")
							&& !newMovieString.substring(0, newMovieString.indexOf(" ")).contains("\"")) {
						IMDBNode newMovieCont = new IMDBNode(newMovieString.substring(0,
								currentActorString.indexOf(")")));
						if (!_movieGraph.containsValue(newMovieCont))
							_movieGraph.put(newMovieCont.getName(), newMovieCont);
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
