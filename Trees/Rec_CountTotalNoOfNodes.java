import java.util.Scanner;

public class Rec_CountTotalNoOfNodes {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Scanner scanner = new Scanner(System.in);
        tree.insert(scanner);
        tree.display();
        int totalNodes = countTotalNodes(tree.root);
        System.out.println("Total number of nodes in the binary tree: " + totalNodes);
    }
    private static int countTotalNodes(BinaryTree.Node root){
        if(root==null){
            return 0;
        }

        return 1 + countTotalNodes(root.left)+countTotalNodes(root.right);
    }
    
}
