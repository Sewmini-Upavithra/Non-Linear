package Trees;

import java.util.LinkedList;
import java.util.Queue;

class BinaryTree {

    // Node class representing each node in the binary tree
    class Node {
        int key;
        Node left, right;

        public Node(int item) {
            key = item;
            left = right = null;
        }
    }

    Node root;

    // Constructor to initialize the binary tree
    BinaryTree() {
        root = null;
    }

    // Method to insert a new key in the binary tree in level order
    void insert(int key) {
        root = insertLevelOrder(root, key);
    }

    // Method to insert a new key in the binary tree in level order
    Node insertLevelOrder(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node temp = queue.poll();

            if (temp.left == null) {
                temp.left = new Node(key);
                break;
            } else {
                queue.add(temp.left);
            }

            if (temp.right == null) {
                temp.right = new Node(key);
                break;
            } else {
                queue.add(temp.right);
            }
        }

        return root;
    }

    // Method to delete a key in the binary tree
    void delete(int key) {
        root = deleteRec(root, key);
    }

    // Method to delete a key in the binary tree
    Node deleteRec(Node root, int key) {
        if (root == null) return null;

        if (root.left == null && root.right == null) {
            if (root.key == key) return null;
            else return root;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        Node keyNode = null, temp = null;
        while (!queue.isEmpty()) {
            temp = queue.poll();

            if (temp.key == key) {
                keyNode = temp;
            }

            if (temp.left != null) {
                queue.add(temp.left);
            }

            if (temp.right != null) {
                queue.add(temp.right);
            }
        }

        if (keyNode != null) {
            int x = temp.key;
            deleteDeepest(root, temp);
            keyNode.key = x;
        }

        return root;
    }

    // Method to delete the deepest node in the binary tree
    void deleteDeepest(Node root, Node delNode) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        Node temp;
        while (!queue.isEmpty()) {
            temp = queue.poll();

            if (temp == delNode) {
                temp = null;
                return;
            }

            if (temp.right != null) {
                if (temp.right == delNode) {
                    temp.right = null;
                    return;
                } else {
                    queue.add(temp.right);
                }
            }

            if (temp.left != null) {
                if (temp.left == delNode) {
                    temp.left = null;
                    return;
                } else {
                    queue.add(temp.left);
                }
            }
        }
    }

    // Method to perform in-order traversal
    void inOrder() {
        inOrderRec(root);
    }

    // Recursive function for in-order traversal
    void inOrderRec(Node root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.print(root.key + " ");
            inOrderRec(root.right);
        }
    }

    // Method to perform pre-order traversal
    void preOrder() {
        preOrderRec(root);
    }

    // Recursive function for pre-order traversal
    void preOrderRec(Node root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preOrderRec(root.left);
            preOrderRec(root.right);
        }
    }

    // Method to perform post-order traversal
    void postOrder() {
        postOrderRec(root);
    }

    // Recursive function for post-order traversal
    void postOrderRec(Node root) {
        if (root != null) {
            postOrderRec(root.left);
            postOrderRec(root.right);
            System.out.print(root.key + " ");
        }
    }

    // Main method to test the binary tree implementation
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        

        tree.insert(10);
        tree.insert(11);
        tree.insert(9);
        tree.insert(7);
        tree.insert(12);
        tree.insert(15);
        tree.insert(8);

        System.out.println("In-order traversal:");
        tree.inOrder();
        System.out.println();

        System.out.println("Pre-order traversal:");
        tree.preOrder();
        System.out.println();

        System.out.println("Post-order traversal:");
        tree.postOrder();
        System.out.println();

        System.out.println("Delete 11");
        tree.delete(11);
        System.out.println("In-order traversal after deletion:");
        tree.inOrder();
        System.out.println();
    }
}
