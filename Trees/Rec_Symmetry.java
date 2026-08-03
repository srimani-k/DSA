import java.util.Scanner;

public class Rec_Symmetry {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Scanner scanner = new Scanner(System.in);
        tree.insert(scanner);
        tree.display();
        boolean isSymmetric = isSymmetric(tree.root.left, tree.root.right);
        System.out.println("Is the binary tree symmetric? " + isSymmetric);
    }

    private static boolean isSymmetric(BinaryTree.Node left, BinaryTree.Node right){
        if(left ==null && right==null){
            return true;
        }
        if(left == null || right==null){
            return false;
        }

        return (left.value == right.value) && isSymmetric(left.left,right.right) && isSymmetric(left.right,right.left);
    }
    
}
