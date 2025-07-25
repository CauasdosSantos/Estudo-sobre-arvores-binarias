class RBNode {
    int data;
    Color color;
    RBNode left, right, parent;

    public RBNode(int data) {
        this.data = data;
        this.color = Color.RED;
        this.left = null;
        this.right = null;
        this.parent = null;
    }
}