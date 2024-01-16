package utils;

import java.io.Serializable;
import java.util.Iterator;

public class Tree<V> implements Serializable, Iterable<Node< V>> {

    private Node<V> root;

    private int size;

    private int BLACK = 0;
    private int RED = 1; // int is too expensive. Mb make bool

    public Tree() {
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    @Override
    public Iterator<Node<V>> iterator() {
        return null;
    }
}
