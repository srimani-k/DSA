import java.util.Scanner;


public class Rec_SumOfLeftLeaves {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Scanner scanner = new Scanner(System.in);
        tree.insert(scanner);
        tree.display();
        int sum = sumOfLeftLeaves(tree.root);
        System.out.println("Sum of left leaves in the binary tree: " + sum);
    }
    public static int sumOfLeftLeaves(BinaryTree.Node root) {
        return sumOfLeftLeaves(root,false);
       
        
    }
    private static int sumOfLeftLeaves(BinaryTree.Node root, boolean isLeft){
        if(root==null){
            return 0;
        }

        if(isLeft==true && root.left==null && root.right==null) {
            return root.value;
        }
        int left = sumOfLeftLeaves(root.left,true);
        int right = sumOfLeftLeaves(root.right,false);

        
        return left + right;
    }
    
}
