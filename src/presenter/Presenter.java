package presenter;

import model.*;
import view.View;

/**
 *
 * @author jhonm
 */
public class Presenter {

    private View view;
    private AVLTree tree;

    private int[] values = {50, 30, 70, 20, 40, 60, 80, 10, 25, 35, 45, 55, 65, 75, 85, 5, 15, 95, 28, 48, 22, 33, 42, 58, 68, 78, 88, 98, 3, 13, 23, 53, 63, 73, 83};

    public Presenter() {
        view = new View();
        tree = new AVLTree();
    }
    
    // Método para realizar un recorrido in-order del árbol
    public void inOrderTraversal(TreeNode node) {
        if (node != null) {
            inOrderTraversal(node.getLeft());
            view.printConsole(node.getData() + " ");
            inOrderTraversal(node.getRight());
        }
    }

    public void run() {
        // Insertar valores en el árbol AVL
        for (int value : values) {
            tree.insert(value);
        }
        
        view.showMessage("Árbol AVL antes de eliminar un nodo:");
        inOrderTraversal(tree.getRoot());
        
        // Eliminar un nodo del árbol AVL
        int valueToDelete = 45;
        tree.delete(valueToDelete);

        view.showMessage("Árbol AVL después de eliminar el nodo con valor " + valueToDelete + ":");
        view.printConsole("\nNuevo arbol: \n");
        inOrderTraversal(tree.getRoot());
    }

    public static void main(String[] args) {
        new Presenter().run();
    }
}
