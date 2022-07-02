import java.awt.*;
import java.util.TreeMap;

public class Node<T extends Comparable<T>> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private T data;
    private Node<T> leftChild;
    private Node<T> rightChild;
    private int height = 1; //used in AVL tree
    private boolean color = RED; //used in red black tree
    private Node<T> parent; //used in red black tree

    public Node(){
        this.data = null;
        this.leftChild = null;
        this.rightChild = null;
    }

    public Node(T data){
        this.data = data;
        this.leftChild = null;
        this.rightChild = null;
    }

    public void setData(T data){
        this.data = data;
    }

    public T getData(){
        return data;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    public Node<T> getParent() {
        return parent;
    }

    public boolean isColor() {
        return color;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }


    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", leftChild=" + leftChild +
                ", rightChild=" + rightChild +
                '}';
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isLeftChild() {
        return this == this.parent.getLeftChild();
    }

    public void shiftColor() {
        color = !color;
    }
}
