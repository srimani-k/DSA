import java.util.Scanner;

public class Rec_SumOfNodes {
    
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Scanner scanner = new Scanner(System.in);
        tree.insert(scanner);
        tree.display();
        int sum = sumOfAllNodes(tree.root);
        System.out.println("Sum of all nodes in the binary tree: " + sum);
    }
    
    private static int sumOfAllNodes(BinaryTree.Node root){
        if(root==null){
            return 0;
        }
        int leftSum = sumOfAllNodes(root.left);
        int rightSum = sumOfAllNodes(root.right);

        return root.value+leftSum+rightSum;
    }
}
