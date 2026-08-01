import java.util.Scanner;

public class Rec_MaxValueInBT {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Scanner scanner = new Scanner(System.in);
        tree.insert(scanner);
        tree.display();
        int maxValue = maximumvalue(tree.root);
        System.out.println("Maximum value in the binary tree: " + maxValue);
    }
    private static int maximumvalue(BinaryTree.Node root){
        if(root==null){
            return Integer.MIN_VALUE;
        }
        int left = maximumvalue(root.left);
        int right = maximumvalue(root.right);
        return Math.max(root.value, Math.max(left, right));
    }
    
}
