
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MorseCodeTree {
    private TreeNode<String> root;

    public MorseCodeTree() {
        buildTree();
    }

    private void buildTree() {
        root = new TreeNode<>(""); // root is empty string

        // Morse code to English alphabet mapping
        String[] morseCode = {
            ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---",
            "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-",
            "..-", "...-", ".--", "-..-", "-.--", "--.."
        };
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        // Adding all nodes to the tree based on Morse code mapping
        for (int i = 0; i < alphabet.length(); i++) {
            addNode(root, morseCode[i], String.valueOf(alphabet.charAt(i)));
        }
    }

    // Recursive method to add node to the tree
    private void addNode(TreeNode<String> current, String code, String letter) {
        if (code.isEmpty()) {
            current.setData(letter);
        } else {
            if (code.charAt(0) == '.') {
                if (current.getLeft() == null) {
                    current.setLeft(new TreeNode<>(""));
                }
                addNode(current.getLeft(), code.substring(1), letter);
            } else if (code.charAt(0) == '-') {
                if (current.getRight() == null) {
                    current.setRight(new TreeNode<>(""));
                }
                addNode(current.getRight(), code.substring(1), letter);
            }
        }
    }

    public String convertToEnglish(String morseCode) {
        TreeNode<String> current = root;
        for (char code : morseCode.toCharArray()) {
            current = (code == '.') ? current.getLeft() : current.getRight();
            if (current == null) {
                return "?"; // Returns '?' if invalid Morse code is encountered
            }
        }
        return current.getData();
    }

    // Method to print the data in the tree
    public String printTree() {
        if (root == null) {
            return "";
        }

        Stack<TreeNode<String>> stack = new Stack<>();
        Queue<TreeNode<String>> queue = new LinkedList<>();
        queue.offer(root);

        // Use a level-order traversal and store nodes in a stack to reverse the levels
        while (!queue.isEmpty()) {
            int levelCount = queue.size();

            // Temporary stack to hold nodes at the current level
            Stack<TreeNode<String>> tempStack = new Stack<>();
            for (int i = 0; i < levelCount; i++) {
                TreeNode<String> currentNode = queue.poll();
                if (currentNode.getLeft() != null) {
                    queue.offer(currentNode.getLeft());
                }
                if (currentNode.getRight() != null) {
                    queue.offer(currentNode.getRight());
                }
                if (!currentNode.getData().equals("")) {
                    tempStack.push(currentNode);
                }
            }
            while (!tempStack.isEmpty()) {
                stack.push(tempStack.pop());
            }
        }

        // Construct the output string from the stack to get the nodes in the desired order
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop().getData()).append(" ");
        }

        // Remove the trailing space and return the string
        return sb.toString().trim();
    }
}
