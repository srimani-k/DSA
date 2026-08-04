import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Rec_PathSumII_LC113 {
    public static void main(String[] args) { 
        BinaryTree tree = new BinaryTree();
        tree.insert(new Scanner(System.in));
        tree.display();
        int targetSum = 22;
        List<List<Integer>> result = new ArrayList<>();
        
        Rec_PathSumII_LC113 obj = new Rec_PathSumII_LC113();
        result = obj.pathSum(tree.root,targetSum);
        System.out.println("All root to leaf paths with sum "+targetSum+":");
        for(List<Integer> path:result){
            System.out.println(path);
        }
    }
       public List<List<Integer>> pathSum(BinaryTree.Node root, int targetSum) {

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        findPaths(root,targetSum,currentPath,result);
        return result;
        
    }
    private void findPaths(BinaryTree.Node root, int targetSum, List<Integer> currentPath, List<List<Integer>> result){
        if(root==null){
            return;
        }

        currentPath.add(root.value);                                    // ADD current node
        int remainingSum = targetSum-root.value;                        // calculate remaining sum
        if(remainingSum==0 && root.left==null && root.right==null){     // check if leaf node and remaining sum is 0 
            result.add(new ArrayList<>(currentPath));
        }
        findPaths(root.left,remainingSum,currentPath,result);   
        findPaths(root.right,remainingSum,currentPath,result);
        currentPath.remove(currentPath.size()-1);                       // remove current node from path to backtrack
    }
    
}
