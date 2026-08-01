import java.util.*;

public class BinaryTree{

    static class Node{
        int value;
        Node left;
        Node right;

        Node(int value){
            this.value=value;
            this.left=null;
            this.right=null;
        }
    }

    Node root;

    //insert elements
    public void insert(Scanner scanner){
        System.out.println("Enter the root node value: ");
        int value = scanner.nextInt();
        root = new Node(value); //here value is assigned to root and left,right are null
        populate(scanner,root);
        
    }
    public void populate(Scanner scanner, Node root){
        System.out.println("Enter left child of "+root.value+" (enter -1 for no child): ");
        int leftValue = scanner.nextInt();
        if(leftValue!=-1){
            root.left=new Node(leftValue);
            populate(scanner, root.left);
        }

        System.out.println("Enter right child of "+root.value+" (enter -1 for no child): ");
        int rightValue = scanner.nextInt();
        if(rightValue!=-1){
            root.right=new Node(rightValue);
            populate(scanner,root.right);
        }
    
    }

    //display binary tree
    public void display(){
        display(this.root,"");
    }
    public void display(Node root, String indent){
        if(root==null){
            return;
        }
        System.out.println(indent+root.value);
        display(root.left,indent+"  ");
        display(root.right,indent+"  ");
    }

    private static void inorder(Node root){
        if(root==null){
            return;
        }
        inorder(root.left);
        System.out.println(root.value+" ");
        inorder(root.right);

    }

    private static void preorder(Node root){
        if(root==null){
            return;
        }
        System.out.println(root.value+" ");
        preorder(root.left);
        preorder(root.right);
    }
    private static void postorder(Node root){
        if(root==null){
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.println(root.value+" ");
    }


    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        BinaryTree tree = new BinaryTree();
        tree.insert(scanner);
        //tree.display();
        System.out.println("Inorder traversal: ");
        inorder(tree.root);
        System.out.println("Preorder traversal: ");
        preorder(tree.root);
        System.out.println("Postorder traversal: ");
        postorder(tree.root);
    }
    
}