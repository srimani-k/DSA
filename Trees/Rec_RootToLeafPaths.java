import java.util.Scanner;

public class Rec_RootToLeafPaths {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Scanner scanner = new Scanner(System.in);
        tree.insert(scanner);
        tree.display();
        System.out.println("Root to leaf paths:");
        printPaths(tree.root,"");
    }

    private static void printPaths(BinaryTree.Node root,String path){

        if(root==null){
            return;
        }

        path = path + root.value + "->";

        //reached a leaf
        if(root.left==null && root.right==null){
            System.out.println(path);
        }

        printPaths(root.left,path);
        printPaths(root.right,path);
    }
    
}
