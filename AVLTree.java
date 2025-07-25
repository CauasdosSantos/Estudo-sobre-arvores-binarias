class AVLTree {
    private AVLNode root;

    public void insert(int key) {
        root = insert(root, key);
    }

    private AVLNode insert(AVLNode node, int key) {
        if (node == null) return new AVLNode(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private int height(AVLNode N) {
        return (N == null) ? 0 : N.height;
    }

    private int getBalance(AVLNode N) {
        return (N == null) ? 0 : height(N.left) - height(N.right);
    }

    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    public void display() {
        if (root != null) {
            System.out.println(root.key + "(" + root.height + ") FB:(" + getBalance(root) + ") -> Raiz da arvore AVL");
            printTree(root.left, "  ", true);
            printTree(root.right, "  ", false);
        }
    }

    private void printTree(AVLNode node, String indent, boolean isLeft) {
        if (node != null) {
            String position = (node == root) ? "Raiz da arvore AVL" : (isLeft ? "Esq" : "Dir");
            System.out.println(indent + "|__" + node.key + "(" + node.height + ") FB:(" + getBalance(node) + ") -> " + position);
            printTree(node.left, indent + "    ", true);
            printTree(node.right, indent + "    ", false);
        }
    }

    public int getHeight() {
        return height(root);
    }

    public double averageLevel() {
        int totalLevels = totalLevels(root, 0);
        int totalNodes = nodeCount(root);
        return totalNodes == 0 ? 0 : (double) totalLevels / totalNodes;
    }

    private int totalLevels(AVLNode node, int level) {
        if (node == null) return 0;
        return level + totalLevels(node.left, level + 1) + totalLevels(node.right, level + 1);
    }

    private int nodeCount(AVLNode node) {
        if (node == null) return 0;
        return 1 + nodeCount(node.left) + nodeCount(node.right);
    }
}
