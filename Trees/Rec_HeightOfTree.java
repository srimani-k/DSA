import java.util.Scanner;

public class Rec_HeightOfTree {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Scanner scanner = new Scanner(System.in);
        tree.insert(scanner);
        tree.display();
        int height = height(tree.root);
        System.out.println("Height of the binary tree: " + height);
    }
    private static int height(BinaryTree.Node root){
        if(root==null){
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return 1 + Math.max(leftHeight,rightHeight);
    }
    
}
