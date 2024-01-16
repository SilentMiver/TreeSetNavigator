import NavigatorAPI.routes.Route;
import NavigatorAPI.utils.RedBlackTree.RedBlackTree;
import NavigatorAPI.utils.Set.TreeSet;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        RedBlackTree<Integer> tree = new RedBlackTree();
        tree.add(10);
        tree.add(9);
        tree.add(8);
        tree.add(119);
        tree.add(12);
        tree.add(13);
        tree.add(15);
        tree.add(18);
        tree.printTree();
        for (var node : tree){
            if (node.equals(0)) System.out.println(node);
        }
        tree.remove(12);
        tree.printTree();
//        tree.
        System.out.println(tree.contains(119));
        System.out.println(tree.contains(0));
        TreeSet<Integer> treeset = new TreeSet<>();
        System.out.println("-----");
        System.out.println(treeset.add(10));

        System.out.println(treeset.add(10));
        treeset.add(11);
        treeset.remove(10);
        for (var e : treeset){
            System.out.println(e);
        }

        Route route1 = createRoute("1", 100.0, 3, true, "B", "B", "C");
        Route route2 = createRoute("2", 150.0, 2, false, "B", "D");
        Route route3 = createRoute("3", 120.0, 4, true, "B", "C", "D", "C");
        Route route4 = createRoute("4", 111.0, 4, true, "B", "G", "D", "C");
        Route route5 = createRoute("5", 130.0, 4, true, "B", "C", "D", "C");
        Route route6 = createRoute("6", 150.0, 4, true, "B", "C", "D", "C");
        Route route7 = createRoute("6", 3.0, 4, true, "B", "C", "C");
        var treeset1 = new TreeSet<Route>();



        System.out.println(treeset1.add(route1));
        System.out.println(treeset1.add(route2));
        System.out.println(treeset1.add(route2));
        System.out.println(treeset1.add(route3));
        System.out.println(treeset1.add(route4));
        System.out.println(treeset1.add(route5));
        System.out.println(treeset1.add(route6));
        System.out.println(treeset1.add(route7));
        for (var e : treeset1){
            System.out.println(e);
        }
        treeset1.print();
    }
    private static Route createRoute(String id, double distance, int popularity, boolean isFavorite, String... locationPoints) {
        Route route = new Route();
        route.setId(id);
        route.setDistance(distance);
        route.setPopularity(popularity);
        route.setFavorite(isFavorite);
        route.setLocationPoints(new ArrayList<>(Arrays.asList(locationPoints)));
        return route;
    }
}