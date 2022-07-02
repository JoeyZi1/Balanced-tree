public class RedBlackTree<T extends Comparable<T>> implements Tree<T> {
    private Node<T> root;

    public RedBlackTree (){
        root = null;
    }

    public Node<T> getRoot() {
        return root;
    }

    @Override
    public void delete(T data){
        delete(data, root);
    }

    private void delete(T data, Node<T> node){
        if (node == null){
            return;
        } else if (data.compareTo(node.getData()) < 0) {
            delete(data, node.getLeftChild());
        } else if (data.compareTo(node.getData()) > 0) {
            delete(data, node.getRightChild());
        } else {
            //target has one child
            if (node.getLeftChild() == null && node.getRightChild() != null){
                node.setData(node.getRightChild().getData());
                node.setRightChild(null);
                return;
            } else if (node.getRightChild() == null && node.getLeftChild() != null){
                node.setData(node.getLeftChild().getData());
                node.setLeftChild(null);
                return;
            }
            //target has no child
            if (node.getLeftChild() == null && node.getRightChild() == null){
                fixDelete(node);
                if (node.getParent() == null)
                    root = null;
                else if (node.isLeftChild())
                    node.getParent().setLeftChild(null);
                else
                    node.getParent().setRightChild(null);
                return;
            }
            //target has two children, delete predecessor
            node.setData(getMax(node.getLeftChild()));
            delete(node.getData(), node.getLeftChild());
        }
        return;
    }

    private void fixDelete(Node<T> node) {
        /*
        In deleltion we only have two cases, delete a 2-node or delete a 3-node (delete 4-node will reduce to deleting
        a 2-node), and the deleted node must be leaf node in 2-3-4 tree, then we can consider following few cases:
        1.delete node with one child, this child must be red(because this is a 3-node), only need to colour child black,
        and use it to replace deleted node
        2.delete leaf node
            2.1 red leaf node, delete directly
            2.2 black leaf node, we must fix the black height change because of deleting it
                2.2.1 brother node in 2-3-4 tree has red node, then rotate to compensate it's height
                2.2.2 brother node in 2-3-4 tree has no red node, then ask for father, if father is red, then use father
                to compensate
                2.2.3 if both brother and father cannot compensate, then recursively ask for grandfather
        */
        if (node != root && !getColor(node)){
            Node<T> father = node.getParent();
            if (node.isLeftChild()) {
                Node<T> brother = father.getRightChild();
                if (getColor(brother)){
                    father.shiftColor();
                    brother.shiftColor();
                    rotateLeft(father);
                    brother = father.getRightChild();
                }
                if (!getColor(brother.getLeftChild()) && !getColor(brother.getRightChild())){
                    brother.shiftColor();
                    fixDelete(father);
                } else {
                    if (!getColor(brother.getRightChild())){
                        rotateRight(brother);
                        brother = father.getRightChild();
                    }
                    brother.setColor(father.isColor());
                    brother.getRightChild().setColor(false);
                    father.setColor(false);
                    rotateLeft(father);
                }
            } else {
                Node<T> brother = node.getParent().getLeftChild();
                if (getColor(brother)){
                    father.shiftColor();
                    brother.shiftColor();
                    rotateRight(father);
                    brother = father.getLeftChild();
                }
                if (!getColor(brother.getLeftChild()) && !getColor(brother.getRightChild())){
                    brother.shiftColor();
                    fixDelete(father);
                } else {
                    if (!getColor(brother.getLeftChild())){
                        rotateLeft(brother);
                        brother = father.getLeftChild();
                    }
                    brother.setColor(father.isColor());
                    brother.getLeftChild().setColor(false);
                    father.setColor(false);
                    rotateRight(father);
                }
            }
        }
        root.setColor(false);
        node.setColor(false);
    }


    private boolean getColor(Node<T> node){
        return node == null ? false : node.isColor();
    }

    @Override
    public Tree<T> insert(T data){
        Node<T> node = new Node<>(data);
        root = insert(root, node);
        fixInsert(node);
        return this;
    }

    private Node<T> insert(Node<T> node, Node<T> nodeToInsert){
        if (node == null)
            return nodeToInsert;
        else if (nodeToInsert.getData().compareTo(node.getData()) < 0) {
            node.setLeftChild(insert(node.getLeftChild(), nodeToInsert));
            node.getLeftChild().setParent(node);
        } else if (nodeToInsert.getData().compareTo(node.getData()) > 0) {
            node.setRightChild(insert(node.getRightChild(), nodeToInsert));
            node.getRightChild().setParent(node);
        }
        return node;
    }

    private void fixInsert(Node<T> node){
        /*
        three cases:
        1.father is black, insert into 2-node in 2-3-4 tree, become 3-node, father is balck, no need to fix
        2.father is red, uncle is black(null), insert into 3-node in 2-3-4 tree, become 4-node, father is red, need rotate to fix, L,
        R, RL, LR
        3.father is red, uncle is red, insert into 4-node in 2-3-4 tree, make it split into a 2-node and a 3-node, father node become
        red 2-node, recursively fix it
        */
        if (node != null && node != root && node.getParent()!= null && node.getParent().isColor()){
            Node<T> father = node.getParent();
            Node<T> grandfather = father.getParent();
            Node<T> uncle = father.isLeftChild() ? grandfather.getRightChild():grandfather.getLeftChild();
            if (getColor(uncle)){
                father.shiftColor();
                uncle.shiftColor();
                grandfather.shiftColor();
                fixInsert(grandfather);
            } else if (father.isLeftChild()) {
                if (!node.isLeftChild()){
                    father = rotateLeft(father);
                }
                father.shiftColor();
                grandfather.shiftColor();
                rotateRight(grandfather);
            } else if (!father.isLeftChild()) {
                if (node.isLeftChild()){
                    father = rotateRight(father);
                }
                father.shiftColor();
                grandfather.shiftColor();
                rotateLeft(grandfather);
            }
        }
        root.setColor(false);
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
            return getMax(node.getRightChild());
        return node.getData();
    }

    public Node<T> rotateLeft(Node<T> node) {
        Node<T> rightNode = node.getRightChild();
        node.setRightChild(rightNode.getLeftChild());
        if (node.getRightChild() != null)
            node.getRightChild().setParent(node);
        rightNode.setLeftChild(node);
        rightNode.setParent(node.getParent());
        if (node.getParent() == null)
            root = rightNode;
        else if (node.isLeftChild())
            node.getParent().setLeftChild(rightNode);
        else
            node.getParent().setRightChild(rightNode);
        node.setParent(rightNode);
        return rightNode;
    }

    public Node<T> rotateRight(Node<T> node) {
        Node<T> leftNode = node.getLeftChild();
        node.setLeftChild(leftNode.getRightChild());
        if (node.getLeftChild() != null)
            node.getLeftChild().setParent(node);
        leftNode.setRightChild(node);
        leftNode.setParent(node.getParent());
        if (node.getParent() == null)
            root = leftNode;
        else if (node.isLeftChild())
            node.getParent().setLeftChild(leftNode);
        else
            node.getParent().setRightChild(leftNode);
        node.setParent(leftNode);
        return leftNode;
    }

}
