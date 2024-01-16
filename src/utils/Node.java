package utils;

import java.io.Serializable;
import java.util.Objects;

public class Node<V> implements Serializable , Comparable<Node<V>>{

    private int color;
    private V value;
    private Node<V> pro;
    private Node<V> left;
    private Node<V> right;

    public Node(int color, V value, Node<V> pro, Node<V> left, Node<V> right) {
        this.color = color;
        this.value = value;
        this.pro = pro;
        this.left = left;
        this.right = right;
    }

    public Node() {
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }



    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Node<V> getPro() {
        return pro;
    }

    public void setPro(Node pro) {
        this.pro = pro;
    }

    public Node<V> getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node<V> getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return Objects.equals(value, node.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int compareTo(Node<V> o) {
        return this.hashCode() - o.hashCode();
    }
}
