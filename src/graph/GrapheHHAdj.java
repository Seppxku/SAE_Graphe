package graph;

import java.util.*;

public class GrapheHHAdj implements VarGraph {
    private final HashMap<String, List<Arc<String>>> adj;

    public GrapheHHAdj() {
       this.adj = new HashMap<>();
    }

    @Override
    public List<Arc<String>> getSucc(String s) {
       return adj.getOrDefault(s, Collections.emptyList()); // si s n'existe pas dans le graphe, retourne une liste vide et pas "null" pour éviter un NullPointerException.
    }

    @Override
    public void ajouterSommet(String noeud) {
       adj.putIfAbsent(noeud, new ArrayList<>()); //putIfAbsent évite d'écraser une liste déjà existante.
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
       ajouterSommet(source);
       ajouterSommet(destination);

       List<Arc<String>> arcs = adj.get(source);

       // Vérifie si l'arc existe déjà
       for (Arc<String> arc : arcs) {
          if (arc.dst().equals(destination)) { //.dst c'est le getter auto généré par le record, retourne le sommet destination de l'arc.
             throw new IllegalArgumentException("L'arc " + source + " -> " + destination + " existe déjà.");
          }
       }
       arcs.add(new Arc<>(valeur, destination));
    }
}
