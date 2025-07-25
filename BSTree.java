class BSTree {
    private BSTNode root;

    // Método de inserção na árvore BST
    public void insert(int key) {
        root = insertRec(root, key);
    }

    private BSTNode insertRec(BSTNode root, int key) {
        if (root == null) {
            root = new BSTNode(key);
            return root;
        }
        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);

        return root;
    }

    // Função para exibir a árvore no formato especificado
    public void display() {
        if (root != null) {
            System.out.println(root.key + "(" + height(root) + ") -> Raiz da arvore BST");
            printTree(root.left, "  ", true);
            printTree(root.right, "  ", false);
        }
    }

    private void printTree(BSTNode node, String indent, boolean isLeft) {
        if (node != null) {
            String position = (node == root) ? "Raiz da arvore BST" : (isLeft ? "Esq" : "Dir");
            int nodeHeight = height(node);
            System.out.println(indent + "|__" + node.key + "(" + nodeHeight + ") -> " + position);
            printTree(node.left, indent + "    ", true);
            printTree(node.right, indent + "    ", false);
        }
    }

    // Função para calcular a altura da árvore BST
    public int getHeight() {
        return height(root);
    }

    private int height(BSTNode node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    // Funções para calcular a média dos níveis dos nós
    public double averageLevel() {
        int totalLevels = totalLevels(root, 0);
        int totalNodes = nodeCount(root);
        return totalNodes == 0 ? 0 : (double) totalLevels / totalNodes;
    }

    private int totalLevels(BSTNode node, int level) {
        if (node == null) return 0;
        return level + totalLevels(node.left, level + 1) + totalLevels(node.right, level + 1);
    }

    private int nodeCount(BSTNode node) {
        if (node == null) return 0;
        return 1 + nodeCount(node.left) + nodeCount(node.right);
    }
}