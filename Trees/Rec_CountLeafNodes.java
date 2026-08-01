import java.util.Scanner;

public class Rec_CountLeafNodes {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Scanner scanner = new Scanner(System.in);
        tree.insert(scanner);
        tree.display();
        int leafCount = countLeafNodes(tree.root);
        System.out.println("Number of leaf nodes in the binary tree: " + leafCount);
    }
    

    private static int countLeafNodes(BinaryTree.Node root){
        if(root==null){
            return 0;
        }
        if(root.left==null && root.right==null){
            return 1;
        }
        return countLeafNodes(root.left)+countLeafNodes(root.right);
    }
}
