
public class Rec_SumRootToLeafNumbers_LC129 {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.insert(new java.util.Scanner(System.in));
        tree.display();
        Rec_SumRootToLeafNumbers_LC129 obj = new Rec_SumRootToLeafNumbers_LC129();
        int result = obj.sumNumbers(tree.root);
        System.out.println("Sum of all root to leaf numbers: "+result);
    }
    public int sumNumbers(BinaryTree.Node root) {
        return helper(root,0);
    }

        public int helper(BinaryTree.Node node, int sum){
            if(node==null){
                return 0;
            }

            sum= sum*10 + node.value;
            if(node.left==null && node.right==null){
                return sum;
            }

            int leftSum = helper(node.left,sum);
            int rightSum = helper(node.right,sum);
            return leftSum+rightSum;
        }
}
