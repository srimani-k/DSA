import java.util.Scanner;

public class Rec_SearchValueInBT {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Scanner scanner = new Scanner(System.in);
        tree.insert(scanner);
        tree.display();
        System.out.print("Enter the value to search: ");
        int x = scanner.nextInt();
        boolean found = searchvalue(tree.root, x);
        if (found) {
            System.out.println("Value " + x + " found in the binary tree.");
        } else {
            System.out.println("Value " + x + " not found in the binary tree.");
        }
    }
    private static boolean searchvalue(BinaryTree.Node root, int x){
        if(root==null){
            return false;
        }

         if(root.value==x){
            return true;
        } //check before left,right bcoz if you found it, no need to go left,right

        boolean left=searchvalue(root.left, x);
        boolean right=searchvalue(root.right, x);

       
        return left || right;
    }
    
}
