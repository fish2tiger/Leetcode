package Tree;

import java.util.*;

import org.junit.Test;

/**
 * In-Order Binary tree traversal
 */
public class BinaryTreeInOrderTraversal {

    public List<Integer> inOrderTraversal(TreeNode root) {
        List<Integer> tree = new ArrayList<>();
        Stack<TreeNode> nodes = new Stack<>();
        Stack<TreeNode> visited = new Stack<>();
        if(root==null)
            return tree;

        nodes.push(root);

        while(!nodes.isEmpty()){
            TreeNode oleft = null;
            root = nodes.peek();
            if(!visited.isEmpty()){
                oleft = visited.peek();
            }
            if(root.left==null){
                tree.add(root.val);
                nodes.pop();
                if(root.right!=null)
                    nodes.push(root.right);
            }
            else if(root.left!=oleft){
                visited.push(root.left);
                nodes.push(root.left);
            }
            else{
                tree.add(root.val);
                nodes.pop();
                visited.pop();
                if(root.right!=null)
                    nodes.push(root.right);
            }
        }

        return tree;
    }

    /**
     * Travel the binary tree In-Order way.
     *
     * @param root tree topmost root
     * @return in
     */
    public List<Integer> inOrderTraversalE(TreeNode root) {
        List<Integer> tree = new ArrayList<>();
        Stack<TreeNode> nodes = new Stack<>();

        if(root==null)
            return tree;
        // Inserting node only when it's first met,
        // this avoid adding duplicate nodes.
        while(root!=null || !nodes.isEmpty()){

            if(root!=null){
                nodes.push(root);
                root = root.left;
            }
            else{
                tree.add(nodes.peek().val);
                root = nodes.pop();
                root = root.right;
            }
        }

        return tree;
    }

    @Test
    public void test(){

    }

}
