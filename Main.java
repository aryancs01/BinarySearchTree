/* Name - Aryan Saxena
 * Date - 22/11/2024
 * Main File - Takes user input and run different function for different methods
 */

package BinarySearchTree;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static BinaryTree bst1 = new BinaryTree();
    private static BinaryTree bst2 = new BinaryTree();
    private static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        boolean exit = false;
        System.out.println(Constants.TREE1);
        startBinaryTree(bst1);
        System.out.println(Constants.TREE2);
        startBinaryTree(bst2);
        
        while (!exit) {
            System.out.println(Constants.OPTIONS);
            System.out.println(Constants.OPTIONS1);
            System.out.println(Constants.OPTIONS2);
            System.out.println(Constants.OPTIONS3);
            System.out.println(Constants.OPTIONS4);
            System.out.print(Constants.USER_OPTION);
             
            try {
            	int choice = input.nextInt();
            	switch (choice) {
                case 1:
                    compareTrees();
                    break;
                case 2:
                    deleteNodeFromTree();
                    break;
                case 3:
                    updateAllNodeValue();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println(Constants.INVALID_NUMBER);
            }
            }catch(Exception error) {
            	System.out.println(Constants.INVALID);
            	input.nextLine();
            	continue;
            }
            
            
        }
    }
    
    public static void startBinaryTree(BinaryTree bst) {
        bst.clearNodesArray();
        
        int numberOfNodes;
        
        while(true) {
        	System.out.print(Constants.ENTER_NODES);
        	try {
        		numberOfNodes = input.nextInt();
        		if(numberOfNodes<=20) {
        			break;
        		}
        	}catch(Exception error) {
        		System.out.println(Constants.INVALID);
        		input.nextLine();
        		continue;
        	}
        }
        addNodesInTree(numberOfNodes, bst);
        System.out.println(Constants.TREE_VISUAL);
        bst.printEachLineOfBinaryTree(bst.getRoot(), 0);
    }
    
    public static void addNodesInTree(int numberOfNodes, BinaryTree bst) {
        bst.setRoot(null);
        ArrayList<Integer> nodesArray = bst.getNodesArray();
        
        for (int i = 1; i <= numberOfNodes; i++) {
        	int value;
        	while(true) {
        		try {
        			System.out.print(Constants.ENTER_NODE + i + ": ");
                    value = input.nextInt();
                    if(value<=999) {
                    	break;
                    }
        		}catch(Exception error) {
        			System.out.println(Constants.INVALID);
        			input.nextLine();
        			continue;
        		}
        	}
            
            if (!nodesArray.contains(value)) {
                nodesArray.add(value);
            }
        }
        
        for (int value : nodesArray) {
            bst.setRoot(bst.addNode(bst.getRoot(), value));
        }
    }
    
    public static void compareTrees() {
        System.out.println(Constants.TREE_COMPARE);
        if (BinaryTree.areTreesEqual(bst1.getRoot(), bst2.getRoot())) {
            System.out.println(Constants.TREE_EQUAL);
        } else {
            System.out.println(Constants.TREE_UNEQUAL);
        }
    }
    
    public static void deleteNodeFromTree() {
        System.out.println("\nDelete from:");
        System.out.println("1. Tree 1");
        System.out.println("2. Tree 2");
        System.out.print("Choose a tree: ");
        int treeChoice = input.nextInt();
        
        BinaryTree selectedTree = (treeChoice == 1) ? bst1 : bst2;
        System.out.print(Constants.ENTER_NODE_DELETE);
        int value = input.nextInt();
        
        selectedTree.setRoot(selectedTree.deleteNode(selectedTree.getRoot(), value));
        System.out.println("\nUpdated "+ Constants.TREE_VISUAL);
        selectedTree.printEachLineOfBinaryTree(selectedTree.getRoot(), 0);
    }
    
    public static void updateAllNodeValue() {
        System.out.println("\nUpdate value in:");
        System.out.println("1. Tree 1");
        System.out.println("2. Tree 2");
        System.out.print("Choose a tree: ");
        int treeChoice = input.nextInt();
        
        BinaryTree selectedTree = (treeChoice == 1) ? bst1 : bst2;
        if (selectedTree.getRoot() == null) {
            System.out.println(Constants.TREE_EMPTY);
            return;
        }
        
        System.out.println(Constants.INCREMENT_BY_5);
        int incrementValue = 5;
        selectedTree.incrementValueBy5(incrementValue);
    }
}