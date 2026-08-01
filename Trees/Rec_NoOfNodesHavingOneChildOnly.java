import java.util.Scanner;

public class Rec_NoOfNodesHavingOneChildOnly {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Scanner scanner = new Scanner(System.in);
        tree.insert(scanner);
        tree.display();
        int count = havingOneChildOnly(tree.root);
        System.out.println("Number of nodes having only one child in the binary tree: " + count);
    }
    private static int havingOneChildOnly(BinaryTree.Node root){
        if(root==null){
            return 0;
        }

        int left = havingOneChildOnly(root.left);
        int right = havingOneChildOnly(root.right);

        if((root.left!=null && root.right==null) || (root.left==null && root.right!=null)){
            return 1+left+right;
        }
        return left+right;
    }
    
}
