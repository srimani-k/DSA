import java.util.Scanner;

public class Rec_CountEvenValuedNodes {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Scanner scanner = new Scanner(System.in);
        tree.insert(scanner);
        tree.display();
        int evenCount = countEvenValuedNodes(tree.root);
        System.out.println("Number of even-valued nodes in the binary tree: " + evenCount);
    }
    private static int countEvenValuedNodes(BinaryTree.Node root){
        if(root==null){
            return 0;
        }
        int left =countEvenValuedNodes(root.left);
        int right=countEvenValuedNodes(root.right);

        if(root.value%2==0){
            return 1+left+right;
        }
        return left+right;
    }
}
