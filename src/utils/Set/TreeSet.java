package utils.Set;

import utils.RedBlackTree.RedBlackTree;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class TreeSet<E extends Comparable<E>> implements Set<E>,Iterable<E> {
    RedBlackTree<E> redBlackTree;
    private boolean firstAddBoolFlag = true;

    public TreeSet() {
        this.redBlackTree = new RedBlackTree<>();
    }

    @Override
    public boolean add(E element) {
//
        if (redBlackTree.contains(element)) return false;
        else {
            redBlackTree.add(element);
            return true;
        }
    }

    @Override
    public boolean remove(E element) {
        if (redBlackTree.contains(element)){
            redBlackTree.remove(element);
            return true;
        }
        else return false;
    }

    @Override
    public boolean contains(E element) {
        return redBlackTree.contains(element);
    }

    @Override
    public int size() {
        int size=0;
        for (var e : redBlackTree){
            size++;
        }
        return size;
    }

    @Override
    public void clear() {
        var newRedBlacktree = new RedBlackTree<E>();
        this.redBlackTree = newRedBlacktree;
    }
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Iterator<E> treeIterator = redBlackTree.iterator();

            @Override
            public boolean hasNext() {
                return treeIterator.hasNext();
            }

            @Override
            public E next() {
                return treeIterator.next();
            }
        };
    }
}








