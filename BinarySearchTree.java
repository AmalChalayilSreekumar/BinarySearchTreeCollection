import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree{


    public TreeNode root;

    private TreeNode addRecursive(TreeNode node, int value) {
        if (node == null) {
            return new TreeNode(value);
        }
        if (value < node.value) {  // Goes leftwards on the tree if value smaller
            node.leftChild = addRecursive(node.leftChild, value);
        } else if (value > node.value) { // Goes rightwards on the tree if value bigger
            node.rightChild = addRecursive(node.rightChild, value);
        }
        return node; // ignore duplicates
    }

    public void addNode(int value){
        root = addRecursive(root, value); //calls the helper method and initializes the root
    }

    public int getNodeCount() { //Uses bfs traversal to count the nodes

        if (root == null){
            return 0;
        } 

        int count = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            count++; // This is the counter incriment that increments every time a node is removed from queue

            if (current.leftChild != null){
                 queue.add(current.leftChild);
            }
            if (current.rightChild != null) {
               queue.add(current.rightChild);
            } 
        }

        return count;
    }

    public ArrayList<Integer> bfsTraversal() { //Performs bfs traversal  
        ArrayList<Integer> bfsNums = new ArrayList<>();

        if (root == null) {
            return bfsNums;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            bfsNums.add(current.value); // Adds the node that was remoced from the queue to the ArrayList 

            if (current.leftChild != null){
                 queue.add(current.leftChild);
            }
            if (current.rightChild != null) {
               queue.add(current.rightChild);
            } 
        }

        return bfsNums;
    }

    public boolean containsNode(int value) { //Does bfs and compares each node to the value inputted

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current.value == value){
                return true;
            }

            if (current.leftChild != null){
                 queue.add(current.leftChild);
            }
            if (current.rightChild != null) {
               queue.add(current.rightChild);
            } 
        }

        return false;
    }

    public int getHeight(TreeNode root){
        if (root == null){ //Returns -1 as height is the tree is empty as height 0 indicates tree exists
            return -1;
        }
        //Recursively goes
        int left = getHeight(root.leftChild);
        int right = getHeight(root.rightChild);
        return Math.max(left, right) + 1;
    }




}