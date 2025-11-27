import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.TreeSet;

public class BinarySearchTreeCollection {
    private static ArrayList<BinarySearchTree> trees;

    public BinarySearchTreeCollection() {
        trees = new ArrayList(); // Creates the arraylist to store the collection of Binary Search Trees
    }

    public void addTree(BinarySearchTree tree) {
        trees.add(tree); // Adds a BinarySearchTree to the collection
    }

    public BinarySearchTree getTree(int index) { // Retrieves a tree at a specific index and if that index does not exist it throws an error
        try {
            return trees.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid argument!");
        }
    }

    public BinarySearchTree deleteTree(int index) { // Removes a tree at an index outlined in the parameter and if that index doesnt exist similar to getTree() it throws an error
        try {
            return trees.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid argument!");
        }
    }

    public int getNumberOfTrees() { // Returns the number of trees in the collection
        return trees.size();
    }

    public boolean areStructurallyEquivalent(int[] indices) { // Compares structure and values of multiple trees
        try {
            if (indices.length < 2) {
                throw new IllegalArgumentException("Invalid argument!"); // atleast 2 trees need to exist for comparison to be made
            }

            TreeNode refRoot = getTree(indices[0]).root; // Reference tree to compare others against

            for (int i = 1; i < indices.length; i++) {
                TreeNode otherRoot = getTree(indices[i]).root;
                Stack<TreeNode[]> stack = new Stack<>(); // Use a stack for iterative tree comparison
                stack.push(new TreeNode[]{refRoot, otherRoot});

                while (!stack.isEmpty()) {
                    TreeNode[] pair = stack.pop();
                    TreeNode a = pair[0], b = pair[1];

                    if (a == null && b == null){
                        continue;
                    } 
                    if (a == null || b == null || a.value != b.value){
                         return false; // Structural or value mismatch
                    }

                    stack.push(new TreeNode[]{a.leftChild, b.leftChild});
                    stack.push(new TreeNode[]{a.rightChild, b.rightChild});
                }
            }

            return true; // All trees structurally equivalent

        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid argument!");
        }
    }

    public void merge(int[] indices) { // Merges trees at given indices into one tree
        try {
            if (indices.length < 2){
                throw new IllegalArgumentException("Invalid argument!"); 
            } 

            TreeSet<Integer> values = new TreeSet<>(); // Auto-sorts and removes duplicates bty creating a set

            for (int index : indices) {
                BinarySearchTree currentTree = getTree(index);
                ArrayList<Integer> bfs = currentTree.bfsTraversal(); // Collect values via BFS
                values.addAll(bfs);
            }

            Arrays.sort(indices); // Sort indices so we can delete from largest to smallest
            for (int i = indices.length - 1; i >= 0; i--) {
                deleteTree(indices[i]); // Remove original trees
            }

            BinarySearchTree newTree = new BinarySearchTree();
            for (int val : values) {
                newTree.addNode(val); // Creates the new merged tree
            }
            addTree(newTree); // Add it to teh collection
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid argument!");
        }
    }

    public int getTotalNodes() { // Returns the total number of nodes across all trees
        int num = 0;
        for (BinarySearchTree tree : trees) {
            num += tree.getNodeCount(); // Sum node count from each tree
        }
        return num;
    }
}
