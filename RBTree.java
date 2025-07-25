enum Color { RED, BLACK }

class RBTree {
    private RBNode root;
    private final RBNode TNULL;

    public RBTree() {
        TNULL = new RBNode(0);
        TNULL.color = Color.BLACK;
        root = TNULL;
    }

    private void leftRotate(RBNode x) {
        RBNode y = x.right;
        x.right = y.left;
        if (y.left != TNULL) y.left.parent = x;
        y.parent = x.parent;
        if (x.parent == null) root = y;
        else if (x == x.parent.left) x.parent.left = y;
        else x.parent.right = y;
        y.left = x;
        x.parent = y;
    }

    private void rightRotate(RBNode x) {
        RBNode y = x.left;
        x.left = y.right;
        if (y.right != TNULL) y.right.parent = x;
        y.parent = x.parent;
        if (x.parent == null) root = y;
        else if (x == x.parent.right) x.parent.right = y;
        else x.parent.left = y;
        y.right = x;
        x.parent = y;
    }

    public void insert(int key) {
        RBNode node = new RBNode(key);
        node.parent = null;
        node.left = TNULL;
        node.right = TNULL;
        node.color = Color.RED;

        RBNode y = null;
        RBNode x = root;

        while (x != TNULL) {
            y = x;
            if (node.data < x.data) x = x.left;
            else x = x.right;
        }

        node.parent = y;
        if (y == null) root = node;
        else if (node.data < y.data) y.left = node;
        else y.right = node;

        if (node.parent == null) {
            node.color = Color.BLACK;
            return;
        }

        if (node.parent.parent == null) return;

        fixInsert(node);
    }

    private void fixInsert(RBNode k) {
        RBNode u;
        while (k.parent.color == Color.RED) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left;
                if (u.color == Color.RED) {
                    u.color = Color.BLACK;
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        k = k.parent;
                        rightRotate(k);
                    }
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    leftRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.right;
                if (u.color == Color.RED) {
                    u.color = Color.BLACK;
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        k = k.parent;
                        leftRotate(k);
                    }
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    rightRotate(k.parent.parent);
                }
            }
            if (k == root) break;
        }
        root.color = Color.BLACK;
    }

    // Método para exibir a árvore no formato similar ao solicitado
    private void printTree(RBNode node, String indent, boolean isLeft) {
        if (node != TNULL) {
            System.out.print(indent);
            if (isLeft) {
                System.out.print("├──");
                indent += "│   ";
            } else {
                System.out.print("└──");
                indent += "    ";
            }
            String color = node.color == Color.RED ? "R" : "B";
            System.out.println(node.data + "(" + color + ")");

            printTree(node.left, indent, true);
            printTree(node.right, indent, false);
        }
    }

    public void display() {
        if (root == TNULL) {
            System.out.println("Empty Tree!");
        } else {
            printTree(this.root, "", false);
        }
    }
}
