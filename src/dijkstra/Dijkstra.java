package dijkstra;

import graph.Graph;
import graph.ShortestPath;
import java.util.*;

public class Dijkstra<T> implements ShortestPath<T> {

	@Override
	public Distances<T> compute(Graph<T> g, T src, Animator<T> animator) throws IllegalArgumentException {
		if (g == null || src == null || animator == null) {
			throw new IllegalArgumentException("Le graphe, le sommet source et l'animateur ne doivent pas être null.");
		}

		Map<T, Integer> dist = new HashMap<>(); //distance actuelle la plus courte de src vers chaque sommet
		Map<T, T> pred = new HashMap<>(); // stocke prédécesseur de chaque sommet dans le chemin le plus court
		Set<T> visited = new HashSet<>(); // marque les sommets dont la distance est définitivement connue
		PriorityQueue<T> pq = new PriorityQueue<>(Comparator.comparingInt(dist::get)); //priority queue pour choisir le sommet avec la plus petite distance pas encore visité.

		// Initialisation avec la source
		dist.put(src, 0);
		pq.add(src);

		while (!pq.isEmpty()) {
			T current = pq.poll();

			if (!visited.add(current)) continue;

			animator.accept(current, dist.get(current));

			for (Graph.Arc<T> arc : g.getSucc(current)) {
				if (arc.val() < 0) {
					throw new IllegalArgumentException("La valuation des arcs ne peut pas être négative : " + arc.val());
				}

				T neighbor = arc.dst();

				// Ajout dynamique des nouveaux sommets rencontrés
				dist.putIfAbsent(neighbor, Integer.MAX_VALUE);
				pred.putIfAbsent(neighbor, null);

				int newDist = dist.get(current) + arc.val();
				if (newDist < dist.get(neighbor)) {
					dist.put(neighbor, newDist);
					pred.put(neighbor, current);
					pq.add(neighbor);
				}
			}
		}

		return new Distances<>(dist, pred);
	}
}
