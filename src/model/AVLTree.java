package model;

/**
 *
 * @author jhonm
 */
public class AVLTree {

    private TreeNode root;

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    // Para calcular la altura de algún nodo
    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    // Para obtener el factor de equilibrio de alguno de los nodos
    private int getBalance(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    // Para realizar rotación a la derecha
    private TreeNode rightRotate(TreeNode y) {
        TreeNode x = y.left;
        TreeNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // Al igual que el método superior, pero a la izquierda
    private TreeNode leftRotate(TreeNode x) {
        TreeNode y = x.right;
        TreeNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // Para insertar un nodo en el árbol AVL
    private TreeNode insert(TreeNode node, int data) {
        if (node == null) {
            return new TreeNode(data);
        }

        if (data < node.data) {
            node.left = insert(node.left, data);
        } else if (data > node.data) {
            node.right = insert(node.right, data);
        } else {
            return node;
        }

        // Actualizar la altura del nodo actual
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Obtener el factor de equilibrio del nodo actual
        int balance = getBalance(node);

        // Realizar rotaciones si es necesario para mantener el equilibrio
        if (balance > 1 && data < node.left.data) {
            return rightRotate(node);
        }
        if (balance < -1 && data > node.right.data) {
            return leftRotate(node);
        }
        if (balance > 1 && data > node.left.data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && data < node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Para eliminar un nodo con un valor dado del árbol AVL
    public TreeNode deleteNode(TreeNode root, int data) throws Exception {
        if (root == null) {
            throw new Exception("El valor a eliminar no existe en el árbol.");
        }

        // Primero se elimina el nodo
        if (data < root.data) {
            root.left = deleteNode(root.left, data);
        } else if (data > root.data) {
            root.right = deleteNode(root.right, data);
        } else {
            if ((root.left == null) || (root.right == null)) {
                TreeNode temp = (root.left != null) ? root.left : root.right;
                if (temp == null) {
                    temp = root;
                    root = null;
                } else {
                    root = temp;
                }
            } else {
                TreeNode temp = minValueNode(root.right);
                root.data = temp.data;
                root.right = deleteNode(root.right, temp.data);
            }
        }

        // Si el árbol tenía solo un nodo, se retorna la ruta
        if (root == null) {
            return root;
        }

        // Después actualizamos la altura del nodo actual
        root.height = 1 + Math.max(height(root.left), height(root.right));

        // Ahora obtenemos el factor de equilibrio del nodo actual
        int balance = getBalance(root);

        // Realizamos rotaciones si es necesario para mantener el equilibrio con los if's
        if (balance > 1 && getBalance(root.left) >= 0) {
            return rightRotate(root);
        }
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        if (balance < -1 && getBalance(root.right) <= 0) {
            return leftRotate(root);
        }
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    // Método auxiliar para encontrar el nodo con el valor mínimo en un subárbol
    private TreeNode minValueNode(TreeNode node) {
        TreeNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // Para insertar un valor en el árbol
    public void insert(int data) {
        root = insert(root, data);
    }

    // Método para eliminar un valor del árbol
    public void delete(int data) {
        try {
            root = deleteNode(root, data);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
