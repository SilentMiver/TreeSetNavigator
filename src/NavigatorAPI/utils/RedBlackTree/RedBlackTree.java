package NavigatorAPI.utils.RedBlackTree;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class RedBlackTree<T extends Comparable<T>> implements Iterable<T>, Iterator<T> {

    /**
     * Перечисление цветов узла дерева.
     */
    enum NodeColor {
        RED,
        BLACK,
        NONE
    }

    /**
     * Класс реализующий узел дерева.
     */
    public class Node {

        /**
         * Значение узла дерева.
         */
        private T value;
        /**
         * Цвет узла.
         */
        private NodeColor color;
        /**
         * Родительский узел.
         */
        private Node parent;
        /**
         * Левый дочерниый узел.
         */
        private Node left;
        /**
         * Правый дочерний узел.
         */
        private Node right;

        /**
         * Конструктор по-умолчанию.
         */
        public Node() {
            value = null;
            color = NodeColor.NONE;
            parent = null;
            left = null;
            right = null;
        }


        /**
         * Конструктор с параметрами, позволящими задать цвет и
         * значение узла.
         *
         * @param value - значение, которое будет сохранено в узле.
         * @param color - цвет узла.
         */
        public Node(T value, NodeColor color) {
            this.value = value;
            this.color = color;
            parent = nil;
            left = nil;
            right = nil;
        }

        /**
         * Конструктор копий.
         *
         * @param node - другой узел.
         */
        public Node(Node node) {
            value = node.value;
            color = node.color;
            parent = node.parent;
            left = node.left;
            right = node.right;
        }

        public boolean isFree() {
            return value == null || value == nil;
        }

        public boolean isLeftFree() {
            return left == null || left == nil;
        }

        public boolean isRightFree() {
            return right == null || right == nil;
        }

        public boolean isParentFree() {
            return parent == null || parent == nil;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node node) {
            parent = node;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node node) {
            left = node;
            if (left != null && left != nil) left.parent = this;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node node) {
            right = node;
            if (right != null && right != nil) right.parent = this;
        }

        public boolean isBlack() {
            return color == NodeColor.BLACK;
        }

        public void makeBlack() {
            color = NodeColor.BLACK;
        }

        public boolean isRed() {
            return color == NodeColor.RED;
        }

        public void makeRed() {
            color = NodeColor.RED;
        }

        public NodeColor getColor() {
            return color;
        }

        public void setColor(NodeColor color) {
            this.color = color;
        }

        /**
         * Возвращет "дедушку" узла дерева.
         */
        public Node getGrandfather() {
            if (parent != null && parent != nil)
                return parent.parent;
            return null;
        }

        /**
         * Возвращает "дядю" узла дерева.
         */
        public Node getUncle() {
            Node grand = getGrandfather();
            if (grand != null) {
                if (grand.left == parent)
                    return grand.right;
                else if (grand.right == parent)
                    return grand.left;
            }
            return null;
        }

        /**
         * Возвращает следующий по значению узел дерева.
         */
        public Node getSuccessor() {
            Node temp = null;
            Node node = this;
            if (!node.isRightFree()) {
                temp = node.getRight();
                while (!temp.isLeftFree())
                    temp = temp.getLeft();
                return temp;
            }
            temp = node.getParent();
            while (temp != nil && node == temp.getRight()) {
                node = temp;
                temp = temp.getParent();
            }
            return temp;
        }

        public String getColorName() {
            return ((isBlack()) ? "B" : "R");
        }

    }

    /**
     * Корень дерева.
     */
    private Node root;
    /**
     * Ограничитель, который обозначает нулевую ссылку.
     */
    private Node nil;

    /**
     * Ссылка на элемент на который указывает итератор.
     */
    private Node current;

    /**
     * Флаг удаления элемента через итератор, необходимый для того, чтобы
     * корректно работали {@link Iterator#hasNext()} и {@link Iterator#next()}
     */
    private boolean isRemoved;

    /**
     * Конструктор по-умолчанию.
     */
    public RedBlackTree() {
        root = new Node();
        nil = new Node();
        nil.color = NodeColor.BLACK;
        root.parent = nil;
        root.right = nil;
        root.left = nil;
        isRemoved = false;
    }

    /**
     * Статический метод, осуществляюший левый поворот дерева tree относительно узла node.
     *
     * @param tree - дерево.
     * @param node - узел, относительно которого осущетвляется левый поворот.
     */


    private static <T extends Comparable<T>> void leftRotate(RedBlackTree<T> tree, RedBlackTree<T>.Node node) {
        RedBlackTree<T>.Node nodeParent = node.getParent();
        RedBlackTree<T>.Node nodeRight = node.getRight();
        if (nodeParent != tree.nil) {
            if (nodeParent.getLeft() == node)
                nodeParent.setLeft(nodeRight);
            else
                nodeParent.setRight(nodeRight);
        } else {
            tree.root = nodeRight;
            tree.root.setParent(tree.nil);
        }
        node.setRight(nodeRight.getLeft());
        nodeRight.setLeft(node);
    }

    /**
     * Статический метод, осуществляюший правый поворот дерева tree относительно узла node.
     *
     * @param tree - дерево.
     * @param node - узел, относительно которого осущетвляется правый поворот.
     */
    private static <T extends Comparable<T>> void rightRotate(RedBlackTree<T> tree, RedBlackTree<T>.Node node) {
        RedBlackTree<T>.Node nodeParent = node.getParent();
        RedBlackTree<T>.Node nodeLeft = node.getLeft();
        if (nodeParent != tree.nil) {
            if (nodeParent.getLeft() == node)
                nodeParent.setLeft(nodeLeft);
            else
                nodeParent.setRight(nodeLeft);
        } else {
            tree.root = nodeLeft;
            tree.root.setParent(tree.nil);
        }
        node.setLeft(nodeLeft.getRight());
        nodeLeft.setRight(node);
    }

    /**
     * Печать дерева
     */
    public void printTree() { // метод для вывода дерева в консоль
        Stack globalStack = new Stack();
        globalStack.push(root);
        int gaps = 32;
        boolean isRowEmpty = false;
        String separator = "-----------------------------------------------------------------";
        System.out.println(separator);
        while (isRowEmpty == false) {
            Stack localStack = new Stack();
            isRowEmpty = true;
            for (int j = 0; j < gaps; j++)
                System.out.print(' ' + "\u001B[30m");
            while (globalStack.isEmpty() == false) {
                Node temp = (Node) globalStack.pop();
                if (temp != null) {
                    if (temp.getValue() == null) System.out.print("nl");
                    else if (temp.color == NodeColor.RED)
                        System.out.print("\u001B[31m" + temp.getValue() + "\u001B[30m");
                    else System.out.print(temp.getValue());
                    localStack.push(temp.left);
                    localStack.push(temp.right);
                    if (temp.left != null ||
                            temp.right != null) isRowEmpty = false;
                } else {
                    System.out.print("__");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < gaps * 2 - 2; j++) System.out.print(' ');
            }
            System.out.println();
            gaps /= 2;
            while (localStack.isEmpty() == false) globalStack.push(localStack.pop());
        }
        System.out.println(separator);
    }


    /**
     * Печать информации об узле дерева.
     *
     * @param tree  - ссылка на дерево.
     * @param nodes - список узлов на уровне дерева.
     */
    private static <T extends Comparable<T>> void printNodes(RedBlackTree<T> tree, ArrayList<RedBlackTree<T>.Node> nodes) {
        int childsCounter = 0;
        int nodesAmount = nodes.size();
        ArrayList<RedBlackTree<T>.Node> childs = new ArrayList<RedBlackTree<T>.Node>();

        for (int i = 0; i < nodesAmount; i++) {
            if (nodes.get(i) != null && nodes.get(i) != tree.nil) {
                System.out.print("(" + nodes.get(i).getValue().toString() + "," + nodes.get(i).getColorName() + ")");
                if (!nodes.get(i).isLeftFree()) {
                    childs.add(nodes.get(i).getLeft());
                    childsCounter++;
                } else
                    childs.add(null);
                if (!nodes.get(i).isRightFree()) {
                    childs.add(nodes.get(i).getRight());
                    childsCounter++;
                } else
                    childs.add(null);
            } else {
                System.out.print("(nil)");
            }
        }
        System.out.print("\n");

        if (childsCounter != 0)
            printNodes(tree, childs);
    }

    /**
     * Реализация метода добавления элемента дарева. На основе добавляемого значения
     * создается узел дерева типа {@link Node} красного цвета.
     *
     * @param o - значение типа {@link Comparable} для вставки в дерево.
     */

    public void add(T o) {
        Node node = root, temp = nil;
        Node newNode = new Node((T) o, NodeColor.RED);
        while (node != null && node != nil && !node.isFree()) {
            temp = node;
            if (newNode.getValue().compareTo(node.getValue()) < 0)
                node = node.getLeft();
            else
                node = node.getRight();
        }
        newNode.setParent(temp);
        if (temp == nil)
            root.setValue(newNode.getValue());
        else {
            if (newNode.getValue().compareTo(temp.getValue()) < 0)
                temp.setLeft(newNode);
            else
                temp.setRight(newNode);
        }
        newNode.setLeft(nil);
        newNode.setRight(nil);
        fixInsert(newNode);
    }

    /**
     * Исправление древа для сохранения свойств красно-черного дерева.
     *
     * @param node - добавленный узел.
     */
    private void fixInsert(Node node) {
        Node temp;
        while (!node.isParentFree() && node.getParent().isRed()) {
            if (node.getParent() == node.getGrandfather().getLeft()) {
                temp = node.getGrandfather().getRight();
                if (temp.isRed()) {
                    temp.makeBlack();
                    node.getParent().makeBlack();
                    node.getGrandfather().makeRed();
                    node = node.getGrandfather();
                } else {
                    if (node == node.getParent().getRight()) {
                        node = node.getParent();
                        leftRotate(this, node);
                    }
                    node.getParent().makeBlack();
                    node.getGrandfather().makeRed();
                    rightRotate(this, node.getGrandfather());
                }
            } else {
                temp = node.getGrandfather().getLeft();
                if (temp.isRed()) {
                    temp.makeBlack();
                    node.getParent().makeBlack();
                    node.getGrandfather().makeRed();
                    node = node.getGrandfather();
                } else {
                    if (node == node.getParent().getLeft()) {
                        node = node.getParent();
                        rightRotate(this, node);
                    }
                    node.getParent().makeBlack();
                    node.getGrandfather().makeRed();
                    leftRotate(this, node.getGrandfather());
                }
            }
        }
        root.makeBlack();
    }

    /**
     * Реализация удаления элемента дерева.
     *
     * @param o - значение типа {@link Comparable} для удаления из дерева.
     * @return true - если элемент был удален;
     * false - если элемента в дереве нет и удаление его невозможно.
     */
    public boolean remove(T o) {
        return remove(findNode(o));
    }

    /**
     *
     */
    private boolean remove(Node node) {
        Node temp = nil, successor = nil;

        if (node == null || node == nil)
            return false;

        if (node.isLeftFree() || node.isRightFree())
            successor = node;
        else
            successor = node.getSuccessor();

        if (!successor.isLeftFree())
            temp = successor.getLeft();
        else
            temp = successor.getRight();
        temp.setParent(successor.getParent());

        if (successor.isParentFree())
            root = temp;
        else if (successor == successor.getParent().getLeft())
            successor.getParent().setLeft(temp);
        else
            successor.getParent().setRight(temp);

        if (successor != node) {
            node.setValue(successor.getValue());
        }
        if (successor.isBlack())
            fixRemove(temp);
        return true;
    }

    /**
     * Исправляет дерево после удаления элемента для сохранения
     * красно-черных свойств дерева.
     *
     * @param node - значение относительно которого необходимо производить
     *             исправление дерева.
     */
    private void fixRemove(Node node) {
        Node temp;
        while (node != root && node.isBlack()) {
            if (node == node.getParent().getLeft()) {
                temp = node.getParent().getRight();
                if (temp.isRed()) {
                    temp.makeBlack();
                    node.getParent().makeRed();
                    leftRotate(this, node.getParent());
                    temp = node.getParent().getRight();
                }
                if (temp.getLeft().isBlack() && temp.getRight().isBlack()) {
                    temp.makeRed();
                    node = node.getParent();
                } else {
                    if (temp.getRight().isBlack()) {
                        temp.getLeft().makeBlack();
                        temp.makeRed();
                        rightRotate(this, temp);
                        temp = node.getParent().getRight();
                    }
                    temp.setColor(node.getParent().getColor());
                    node.getParent().makeBlack();
                    temp.getRight().makeBlack();
                    leftRotate(this, node.getParent());
                    node = root;
                }
            } else {
                temp = node.getParent().getLeft();
                if (temp.isRed()) {
                    temp.makeBlack();
                    node.getParent().makeRed();
                    rightRotate(this, node.getParent());
                    temp = node.getParent().getLeft();
                }
                if (temp.getLeft().isBlack() && temp.getRight().isBlack()) {
                    temp.makeRed();
                    node = node.getParent();
                } else {
                    if (temp.getLeft().isBlack()) {
                        temp.getRight().makeBlack();
                        temp.makeRed();
                        leftRotate(this, temp);
                        temp = node.getParent().getLeft();
                    }
                    temp.setColor(node.getParent().getColor());
                    node.getParent().makeBlack();
                    temp.getLeft().makeBlack();
                    rightRotate(this, node.getParent());
                    node = root;
                }
            }
        }
        node.makeBlack();
    }

    /**
     * Реализует функцию проверки на содержание элемента в дереве.
     *
     * @param o - значение типа {@link Comparable} для поиска в дерева.
     * @return true - если элемент найден; false - если элемент не найда.
     */
    public boolean contains(T o) {
        if (root.isFree()) return false;
        else return (findNode(o) != nil);
    }


    private Node findNode(T o) {
        Node node = root;
        while (node != null && node != nil && node.getValue().compareTo(o) != 0) {
            if (node.getValue().compareTo(o) > 0)
                node = node.getLeft();
            else
                node = node.getRight();
        }
        return node;
    }

    /**
     * Метод для получения первого(наименьшего) элемента структуры.
     *
     * @return наименьший элемент дерева
     */
    private Node first() {
        Node node = root;
        while (node.getLeft() != null && node.getLeft() != nil) {
            if (!node.isLeftFree())
                node = node.getLeft();
        }
        return node;
    }

    @Override
    public Iterator<T> iterator() {
        current = null;
        isRemoved = false;
        return this;
    }

    @Override
    public boolean hasNext() {
        if (current != null) {
            if (!isRemoved) {
                RedBlackTree<T>.Node node = current.getSuccessor();
                return (node != null && node != nil);
            }
            return (current != null && current != nil);
        } else {
            return (root != null && !root.isFree());
        }
    }

    @Override
    public T next() {
        if (current != null) {
            if (!isRemoved)
                current = current.getSuccessor();
            else
                isRemoved = false;
        } else {
            current = first();
        }
        if (current == null || current == nil)
            throw new NoSuchElementException();
        return current.getValue();
    }

    @Override
    public void remove() {
        if (current != null && !isRemoved) {
            remove(current);
            isRemoved = true;
        } else
            throw new IllegalStateException();
    }

}
