public class TreeVisualize<T extends Comparable<T>> {
    /**
     * 较为直观地打印红黑树
     * 修改自打印二叉树代码 原文链接：https://blog.csdn.net/troubleshooter/article/details/122638070
     */

    public static int getTreeDepth(Node root) {
        return root == null ? 0 : (1 + Math.max(getTreeDepth(root.getLeftChild()), getTreeDepth(root.getRightChild())));
    }


    private static void writeArray(Node currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
        if (currNode == null) return;
        res[rowIndex][columnIndex] = ("\033[30;3m" + currNode.getData()+"\033[0m") ;
        int currLevel = ((rowIndex + 1) / 2);
        if (currLevel == treeDepth) return;
        int gap = treeDepth - currLevel - 1;
        if (currNode.getLeftChild() != null) {
            res[rowIndex + 1][columnIndex - gap] = "/";
            writeArray(currNode.getLeftChild(), rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
        }
        if (currNode.getRightChild() != null) {
            res[rowIndex + 1][columnIndex + gap] = "\\";
            writeArray(currNode.getRightChild(), rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
        }
    }

    public static void show(Node root) {
        if (root == null) System.out.println("EMPTY!");
        int treeDepth = getTreeDepth(root);
        int arrayHeight = treeDepth * 2 - 1;
        int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
        String[][] res = new String[arrayHeight][arrayWidth];
        for (int i = 0; i < arrayHeight; i ++) {
            for (int j = 0; j < arrayWidth; j ++) {
                res[i][j] = " ";
            }
        }
        writeArray(root, 0, arrayWidth/2, res, treeDepth);
        for (String[] line: res) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < line.length; i ++) {
                sb.append(line[i]);
                if (line[i].length() > 1 && i <= line.length - 1) {
                    i += line[i].length() > 4 ? 2: line[i].length() - 1;
                }
            }
            System.out.println(sb.toString());
        }
    }

    private static void writeRBArray(Node currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
        if (currNode == null) return;
        if(!currNode.isColor()){
            res[rowIndex][columnIndex] = ("\033[30;3m" + currNode.getData()+"\033[0m") ;
        }else {
            res[rowIndex][columnIndex] = ("\033[31;3m" + currNode.getData()+"\033[0m") ;
        }
        int currLevel = ((rowIndex + 1) / 2);
        if (currLevel == treeDepth) return;
        int gap = treeDepth - currLevel - 1;
        if (currNode.getLeftChild() != null) {
            res[rowIndex + 1][columnIndex - gap] = "/";
            writeRBArray(currNode.getLeftChild(), rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
        }
        if (currNode.getRightChild() != null) {
            res[rowIndex + 1][columnIndex + gap] = "\\";
            writeRBArray(currNode.getRightChild(), rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
        }
    }

    public static void RBshow(Node root) {
        if (root == null) System.out.println("EMPTY!");
        int treeDepth = getTreeDepth(root);
        int arrayHeight = treeDepth * 2 - 1;
        int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
        String[][] res = new String[arrayHeight][arrayWidth];
        for (int i = 0; i < arrayHeight; i ++) {
            for (int j = 0; j < arrayWidth; j ++) {
                res[i][j] = " ";
            }
        }
        writeRBArray(root, 0, arrayWidth/2, res, treeDepth);
        for (String[] line: res) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < line.length; i ++) {
                sb.append(line[i]);
                if (line[i].length() > 1 && i <= line.length - 1) {
                    i += line[i].length() > 4 ? 2: line[i].length() - 1;
                }
            }
            System.out.println(sb.toString());
        }
    }
}

