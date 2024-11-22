/* Name - Aryan Saxena
 * Date - 22/11/2024
 * This file create binary search tree in tree format 
 * it also compare two tree (equals or not)
 * and can delete Node and updated value of nodes
 */
package BinarySearchTree;
import java.util.ArrayList;
import java.util.Scanner;

public class BinaryTree {
    private Node root; 
    private ArrayList<Integer> nodesArray = new ArrayList<Integer>();

    //Creation of Node
    public static class Node {
        int data;
        Node right;
        Node left;

        Node(int value) {
            this.data = value;
        }
    }
    
    // Add Node in tree
    public Node addNode(Node current, int value) {
        if(current == null) {
            return new Node(value);
        }
        
        if(current.data > value) {
            current.left = addNode(current.left, value);
        } else if(current.data < value) {
            current.right = addNode(current.right, value);
        }
        
        return current;
    }
    
    //Method to get root
    public Node getRoot() {
        return root;
    }

    // Method to set root
    public void setRoot(Node newRoot) {
        this.root = newRoot;
    }
    
    private static int findGreaterNumber(int number1, int number2) {
        return (number1 > number2) ? number1 : number2;
    }

    // Get the Height of Tree
    private static int getDepth(Node root) {
        if (root == null) return 0;
        return 1 + findGreaterNumber(getDepth(root.left), getDepth(root.right));
    }

    // Store every line of binary search tree, with spaces and root data
    public void printEachLineOfBinaryTree(Node root, int value) {
        int depth = getDepth(root);
        ArrayList<String> lines = new ArrayList<>();
        binarySearchTreeStructure(root, 0, depth, lines, value);
        for(String line : lines) {
            System.out.println(line);
        }
    }

    // Here is a Binary Search Tree Structure, it will calculate how much space is needed and allignemnt of data
    private static void binarySearchTreeStructure(Node root, int level, int depth, ArrayList<String> lines, int value) {
        int maxWidth = (int) Math.pow(2, depth) * 2;
    
        for (int i = lines.size(); i <= level; i++) {      
            lines.add("");
        }
    
        StringBuilder currentLine = new StringBuilder(lines.get(level));
    
        if (root == null) {
            int spacesToAdd = maxWidth / (level + 1);
            currentLine.append(" ".repeat(spacesToAdd));
            lines.set(level, currentLine.toString());
            return;
        }
    
        int spacing = maxWidth / (level + 1) / 2;
        String nodeString = root.data + value + " ";
        String underscores = "_".repeat(spacing / 2);
        String formatted = " ".repeat(spacing / 2) + underscores + nodeString + underscores + " ".repeat(spacing / 2);
        currentLine.append(formatted);
        lines.set(level, currentLine.toString());
     
        binarySearchTreeStructure(root.left, level + 1, depth, lines, value);
        binarySearchTreeStructure(root.right, level + 1, depth, lines, value);
    }
    
    // Check Binary Tree equals or not
    public static boolean areTreesEqual(Node root1, Node root2) {
        ArrayList<Integer> inorder1 = new ArrayList<>();
        ArrayList<Integer> inorder2 = new ArrayList<>();
        
        getInorderTraversal(root1, inorder1);
        getInorderTraversal(root2, inorder2);

        return inorder1.equals(inorder2);
    }

    // Get Inorder Traversal of binary tree
    private static void getInorderTraversal(Node root, ArrayList<Integer> result) {
        if (root == null) return;
        getInorderTraversal(root.left, result);
        result.add(root.data);
        getInorderTraversal(root.right, result);
    }

    // Delete particular node in binary tree
    public Node deleteNode(Node current, int value) {
        if (current == null) {
            System.out.println("Node not found.");
            return null;
        }

        if (value < current.data) {
            current.left = deleteNode(current.left, value);
        } else if (value > current.data) {
            current.right = deleteNode(current.right, value);
        } else {
            if (current.left == null) {
                return current.right;
            } else if (current.right == null) {
                return current.left;
            }

            current.data = findMin(current.right);
            current.right = deleteNode(current.right, current.data);
        }

        return current;
    }
    
    private static int findMin(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root.data;
    }
    
    // Increment Value by 5 of all nodes and except root
    public void incrementValueBy5(int incrementValue) {
        if (this.root == null) {
            System.out.println("Tree is empty, nothing to update.");
            return;
        }

        ArrayList<Integer> updatedValues = new ArrayList<>();
        updatedValues.add(this.root.data); // Add root value without increment
        
        // Gather updated values
        gatherUpdatedValues(this.root.left, incrementValue, updatedValues);
        gatherUpdatedValues(this.root.right, incrementValue, updatedValues);

        // Reset and rebuild tree
        this.root = null;
        for (int newValue : updatedValues) {
            this.root = addNode(this.root, newValue);
        }

        System.out.println("\nUpdated Tree Visualization:");
        printEachLineOfBinaryTree(this.root, 0);
    }

    //Update the values of binary tree with incremented values by 5
    private void gatherUpdatedValues(Node node, int incrementValue, ArrayList<Integer> updatedValues) {
        if (node == null) return;
        updatedValues.add(node.data + incrementValue);
        gatherUpdatedValues(node.left, incrementValue, updatedValues);
        gatherUpdatedValues(node.right, incrementValue, updatedValues);
    }

    // Get Binary tree data array
    public ArrayList<Integer> getNodesArray() {
        return nodesArray;
    }

    //Clear Binary tree data array
    public void clearNodesArray() {
        nodesArray.clear();
    }
}