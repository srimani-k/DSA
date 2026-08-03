public class Rec_PathSum_LC112 {
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