import java.util.Scanner;

public class Rec_PathSum_LC112 {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Scanner scanner = new Scanner(System.in);
        tree.insert(scanner);
        tree.display();
        int targetSum = 22; // Example target sum
        boolean hasPath = hasPathSum(tree.root, targetSum);
        System.out.println("Does the binary tree have a root-to-leaf path with sum " + targetSum + "? " + hasPath);
    }
    private static boolean hasPathSum(BinaryTree.Node root, int targetSum){
        if(root==null){
            return false;
        }
        if(root.value==targetSum && root.left==null && root.right==null){
            return true;
        }

        int remainingSum = targetSum - root.value;

        // Ask children whether they can complete the remaining required sum.
        return hasPathSum(root.left, remainingSum) || hasPathSum(root.right, remainingSum);
    
    }
}