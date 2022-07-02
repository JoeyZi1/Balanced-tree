public class AVLTree<T extends Comparable<T>> implements Tree<T> {
    private Node<T> root;
    public AVLTree (){
        root = null;
    }

    public Node<T> getRoot() {
        return root;
    }

    public Node<T> rotateBalance(Node<T> node) {
        if (balance(node) < -1) {
            if (balance(node.getRightChild()) > 0) {
                node.setRightChild(rotateRight(node.getRightChild()));
                updateHeight(node);
            }
            return rotateLeft(node);
        } else if (balance(node) > 1) {
            if (balance(node.getLeftChild()) < 0) {
                node.setLeftChild(rotateLeft(node.getLeftChild()));
                updateHeight(node);
            }
            return rotateRight(node);
        }
        return node;
    }

    public Node<T> rotateLeft(Node<T> node) {
        Node<T> rightChild = node.getRightChild();
        Node<T> transChild = rightChild.getLeftChild();
        rightChild.setLeftChild(node);
        node.setRightChild(transChild);
        updateHeight(node);
        updateHeight(rightChild);
        return rightChild;
    }

    public Node<T> rotateRight(Node<T> node) {
        Node<T> leftChild = node.getLeftChild();
        Node<T> transChild = leftChild.getRightChild();
        leftChild.setRightChild(node);
        node.setLeftChild(transChild);
        updateHeight(node);
        updateHeight(leftChild);
        return leftChild;
    }

    public void updateHeight(Node<T> node) {
        node.setHeight(Math.max(height(node.getLeftChild()), height(node.getRightChild()))+1);
    }

    public int balance(Node<T> node) {
        return (node == null) ? 0 : height(node.getLeftChild()) - height(node.getRightChild());
    }

    public int height(Node<T> node) {
        return (node == null) ? 0 : node.getHeight();
    }

    @Override
    public void delete(T data){
        root = delete(data, root);
    }

    public Node<T> delete(T data, Node<T> node){
        if (node == null){ //base case1
            return null;
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeftChild(delete(data, node.getLeftChild()));//recursive case
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRightChild(delete(data, node.getRightChild()));//recursive case
        } else {//base case2
            //target has no child or one child
            if (node.getLeftChild() == null){
                return node.getRightChild();
            } else if (node.getRightChild() == null){
                return node.getLeftChild();
            }
            //target has two children, delete replacing node
            node.setData(getMin(node.getRightChild()));
            node.setRightChild(delete(node.getData(), node.getRightChild()));
        }
        //rebalance
        updateHeight(node);
        return rotateBalance(node);
    }

    @Override
    public Tree<T> insert(T data){
        root = insert(data, root);
        return this;
    }

    public Node<T> insert(T data, Node<T> node){
        if (node == null)
            return new Node<T>(data); //base case
        else if (data.compareTo(node.getData()) < 0) {
            node.setLeftChild(insert(data, node.getLeftChild())); //recursive case
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRightChild(insert(data, node.getRightChild()));// recursive case
        }
        // rebalance
        updateHeight(node);
        return rotateBalance(node);
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
