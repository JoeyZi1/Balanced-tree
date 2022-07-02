import java.util.Scanner;

public class RBTreeTest {
    public static void main(String[] args) {
        opt();
    }

    public static void opt(){
        Scanner scanner=new Scanner(System.in);
        RedBlackTree<String> rbt=new RedBlackTree<>();
        while (true){
            System.out.println("输入“+x”插入节点，输入“-x”删除节点(x超过两位数可能导致错位)：");
            String s = scanner.next();
            System.out.println();
            String data = s.substring(1);
            if(data.length()==1){
                data ="00"+data ;
            }else if(data.length()==2){
                data ="0"+data ;
            }
            if (s.charAt(0) == '+')
                rbt.insert(data);
            else if (s.charAt(0) == '-')
                rbt.delete(data);
            TreeVisualize.RBshow(rbt.getRoot());
        }
    }

}

