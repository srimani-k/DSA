import java.util.Scanner;


public class Rec_BalancedTree {
    
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Scanner scanner = new Scanner(System.in);
        tree.insert(scanner);
        tree.display();
        boolean isBalanced = isBalanced(tree.root);
        System.out.println("Is the binary tree balanced? " + isBalanced);
    }


    public static boolean isBalanced(BinaryTree.Node  root) {

        // heightCheck() returns:
        // - Actual height if subtree is balanced.
        // - -1 if subtree is unbalanced.
        return heightCheck(root) != -1;
    }

    private static int heightCheck(BinaryTree.Node root) {
        if (root == null) {
            return 0;
        }

        int left = heightCheck(root.left);
        int right = heightCheck(root.right);
        if (left == -1 || right == -1) {
            return -1;
        }
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;

        // ===========================
        // BASE CASE
        // ===========================
        // An empty tree has height 0.
        // ===========================
        // ASK THE CHILDREN
        // ===========================
        // Left child:
        // "Tell me your height.
        // If you're unbalanced, return -1."
        

        // Right child:
        // "Tell me your height.
        // If you're unbalanced, return -1."
        

        // ===========================
        // CHILD ALREADY FAILED?
        // ===========================
        // -1 is NOT a height.
        // It is a special signal saying:
        // "This subtree is already unbalanced."
        //
        // If either child is unbalanced,
        // no need to check anything else.
        // Just pass the signal upward.
        

        // ===========================
        // CHECK CURRENT NODE
        // ===========================
        // Now both left and right are VALID heights.
        //
        // A node is balanced only if
        // |leftHeight - rightHeight| <= 1
        


        // ===========================
        // RETURN MY HEIGHT
        // ===========================
        // Current subtree is balanced.
        //
        // Height =
        // Taller child's height + myself.
    }

    /*private static boolean isBalancedInEfficient(BinaryTree.Node root){
        if(root==null){
            return true;
        }

        //inefficient approach as we are calculating height for each node which is O(n^2)
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        return Math.abs(leftHeight-rightHeight)<=1 && isBalancedInEfficient(root.left) && isBalancedInEfficient(root.right);
    }

    private static int height(BinaryTree.Node root){
        if(root==null){
            return 0;
        }

        return 1+Math.max(height(root.left),height(root.right));
    }*/
    
}