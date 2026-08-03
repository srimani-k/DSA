import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Rec_AllRootToLeafPaths {

    

    public static void main(String[] args) { 
        BinaryTree tree = new BinaryTree();
        Scanner scanner = new Scanner(System.in);
        tree.insert(scanner);
        tree.display();
        List<String> result = new ArrayList<>();
        
        binaryTreePaths(tree.root,"",result);
        System.out.println("All root to leaf paths:");
        for(String path:result){
            System.out.println(path);
        }
        
    }
    private static void binaryTreePaths(BinaryTree.Node root, String path, List<String> result){
        if(root==null){
            return;
        }

        if(path.isEmpty()){
            path=""+root.value;
        }else{
            path=path+"->"+root.value;
        }
        
        if(root.left==null && root.right==null){
            result.add(path);
            //path="";
            return ;
        }


        binaryTreePaths(root.left,path,result);
        binaryTreePaths(root.right,path,result);


    }
}