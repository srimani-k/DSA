import java.util.Scanner;

public class Rec_InvertTree {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Scanner scanner = new Scanner(System.in);
        tree.insert(scanner);
        System.out.println("Original binary tree:");
        tree.display();
        invertTree(tree.root);
        System.out.println("Inverted binary tree:");
        tree.display();
    }

    private static BinaryTree.Node invertTree(BinaryTree.Node root){
        if(root==null){
            return null;
        }

        BinaryTree.Node left = invertTree(root.left);
        BinaryTree.Node right = invertTree(root.right);

        root.left=right;
        root.right=left;

        return root;
    }
    
}
