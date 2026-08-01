import java.util.Scanner;

public class Rec_CountNodesGreaterThanX {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Scanner scanner = new Scanner(System.in);
        tree.insert(scanner);
        tree.display();
        System.out.print("Enter the value of X: ");
        int x = scanner.nextInt();
        int count = countNodesGreaterThanX(tree.root, x);
        System.out.println("Number of nodes greater than " + x + ": " + count);
    }
    private static int countNodesGreaterThanX(BinaryTree.Node root, int x){
        if(root==null){
            return 0;
        }

        int left=countNodesGreaterThanX(root.left, x);
        int right=countNodesGreaterThanX(root.right, x);

        if(root.value>x){
            return 1+left+right;
        }
        return left+right;
    }
    
}
