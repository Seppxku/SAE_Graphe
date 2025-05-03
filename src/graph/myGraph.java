package graph;

import java.util.*;

public class myGraph<T> implements Graph<T> {
    private final HashMap<T, List<Arc<T>>> arcs_sortants;

    public myGraph() {
        this.arcs_sortants = new HashMap<>();
    }

    public void addArc(T origin, T destination, int valuation) {
        // On vérifie si le sommet d'origine existe déjà dans la hashmap
        if (!arcs_sortants.containsKey(origin)) {
            arcs_sortants.put(origin, new ArrayList<>()); // Si non, on crée une nouvelle liste
        }
        arcs_sortants.get(origin).add(new Arc<>(valuation, destination)); // Si oui, on ajoute l'arc à la liste des arcs sortants !
    }

    // Ici on retourne la liste des arcs sortants pour UN sommet donné.
    public List<Arc<T>> getSucc(T s) {
        return arcs_sortants.getOrDefault(s, new ArrayList<>()); //getOrDefault veut dire qu'on va essayer de get la liste des arcs sortants, mais si y'en a 0 on retourne une liste vide.
    }

}
