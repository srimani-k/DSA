import java.util.Scanner;


public class Rec_MinDepth {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Scanner scanner = new Scanner(System.in);
        tree.insert(scanner);
        tree.display();
        int minDepth = minDepth(tree.root);
        System.out.println("Minimum depth of the binary tree: " + minDepth);
    }

    private static int minDepth(BinaryTree.Node root) {
        if(root==null){
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if(root.left==null){
            return right+1;
        }
        if(root.right==null){
            return left+1;
        }

        return Math.min(left,right)+1;
    }
    
}
