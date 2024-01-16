import utils.RedBlackTree;

import java.util.TreeSet;

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
        var treeset = new TreeSet<>();


    }
}