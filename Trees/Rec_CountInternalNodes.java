import java.util.Scanner;

public class Rec_CountInternalNodes { //non-leaf nodes
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Scanner scanner = new Scanner(System.in);
        tree.insert(scanner);
        tree.display();
        int internalNodeCount = countInternalNodes(tree.root);
        System.out.println("Number of internal nodes in the binary tree: " + internalNodeCount);
    }
    private static int countInternalNodes(BinaryTree.Node root){

    if (root == null) {
        return 0;
    }

    int left = countInternalNodes(root.left);
    int right = countInternalNodes(root.right);

    int current = (root.left!=null || root.right!=null) ? 1 : 0;

    return current + left + right;
}
    
}
