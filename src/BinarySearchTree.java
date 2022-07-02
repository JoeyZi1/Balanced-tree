import java.util.TreeMap;

public class BinarySearchTree<T extends Comparable<T>>
        implements Tree<T> {
    private Node<T> root;

    public BinarySearchTree (){
        root = null;
    }


    @Override
    public void delete(T data){
        root = delete(data, root);
    }

    public Node<T> delete(T data, Node<T> node){
        if (node == null){
            return null;
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeftChild(delete(data, node.getLeftChild()));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRightChild(delete(data, node.getRightChild()));
        } else {
            if (node.getLeftChild() == null){
                return node.getRightChild();
            } else if (node.getRightChild() == null){
                return node.getLeftChild();
            }
            node.setData(getMin(node.getRightChild()));
            node.setRightChild(delete(node.getData(), node.getRightChild()));
        }
        return node;
    }


    @Override
    public Tree<T> insert(T data){
        root = insert(data, root);
        return this;
    }

    public Node<T> insert(T data, Node<T> node){
        if (node == null)
            return new Node<T>(data);
        else if (data.compareTo(node.getData()) < 0) {
            node.setLeftChild(insert(data, node.getLeftChild()));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRightChild(insert(data, node.getRightChild()));
        }
        return node;
    }


    @Override
    public void traverse(){
        inOrderTravers(root);
    }

    public void inOrderTravers(Node<T> node){
        if (node != null){
            inOrderTravers(node.getLeftChild());
            System.out.println(node);
            inOrderTravers(node.getRightChild());
        }
    }



    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public T getMin() {
        if (isEmpty())
            return null;
        return getMin(root);
    }

    public T getMin(Node<T> node) {
        if (node.getLeftChild() != null)
            return getMin(node.getLeftChild());
        return node.getData();
    }

    @Override
    public T getMax() {
        if (isEmpty())
            return null;
        return getMax(root);
    }

    public T getMax(Node<T> node) {
        if (node.getRightChild() != null)
            return getMin(node.getRightChild());
        return node.getData();
    }

}
