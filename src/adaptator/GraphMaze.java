package adaptator;

import graph.Graph;
import maze.Maze;
import java.util.List;
import java.util.ArrayList;

public class GraphMaze<C> implements Graph<C> {
    private final Maze<C> maze;

    public GraphMaze(Maze<C> maze) {
        this.maze = maze;
    }

    @Override
    public List<Arc<C>> getSucc(C cell) {
        List<Arc<C>> arcs = new ArrayList<>();
        for (C neighbor : maze.openedNeighbours(cell)) {// Récupérer les voisins ouverts de la cellule
            arcs.add(new Arc<>(1, neighbor));// Créer un arc de valuation de 1 et l'ajouter à la liste
        }
        return arcs; // Retourne la liste d'arcs
    }

}
