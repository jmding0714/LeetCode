import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by jmding on 3/5/17.
 */
public class BinaryTreeInorderTraversal {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static List<Integer> inorderTraversal(TreeNode root) {

        // Create a list to store the result
        List<Integer> result = new ArrayList<>();
        // Create a stack to keep track of left nodes along the path
        Stack<TreeNode> stack = new Stack<>();

        // Create a tree node pointer points to the current node. Initialize it with root.
        TreeNode current = root;
        // While the current node is not null, or the stack is empty
        // (If the current node is null and stack is empty, it means we reach to the last node)
        while (current != null || !stack.empty()) {
            // We keep finding the left child of the current node, while pushing the left node on the path to the stack
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            // Now, the current is null, we pop the stack, get the parent node (which is also a left node)
            current = stack.pop();
            // Add the value to the result list
            result.add(current.val);
            // Set the current to the right child.
            // (If there is no right child, current is null, we pop the next left node in the stack)
            current = current.right;
        }

        return result;
    }
}
