import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) {
        try {
            // Configurar saída para o arquivo "saida.txt"
            PrintStream fileOut = new PrintStream(new FileOutputStream("saida.txt"));
            System.setOut(fileOut);

            // Exercício 1: Inserção na AVL com {10, 5, 12, 3, 8, 15, 18}
            System.out.println("Exercício 1 - Inserção em Árvore AVL:");
            AVLTree avlExercise1 = new AVLTree();
            int[] valuesEx1 = {10, 5, 12, 3, 8, 15, 18};
            for (int value : valuesEx1) {
                System.out.println("\nInserindo " + value + " na AVL:");
                avlExercise1.insert(value);
                avlExercise1.display();
            }

            System.out.println("\n-----------------------------\n");

            // Exercício 2: Inserção na AVL com {50, 30, 70, 20, 40, 60, 80}
            System.out.println("Exercício 2 - Inserção em Árvore AVL:");
            AVLTree avlExercise2 = new AVLTree();
            int[] valuesEx2 = {50, 30, 70, 20, 40, 60, 80};
            for (int value : valuesEx2) {
                System.out.println("\nInserindo " + value + " na AVL:");
                avlExercise2.insert(value);
                avlExercise2.display();
            }

            System.out.println("\n-----------------------------\n");

            // Exercício 3: Inserção em BST e AVL com {10, 5, 2, 8, 20, 15, 25}
            System.out.println("Exercício 3 - Comparação entre BST e AVL:");

            // Inicializando as árvores BST e AVL
            BSTree bst = new BSTree();
            AVLTree avl = new AVLTree();
            int[] valuesEx3 = {10, 5, 2, 8, 20, 15, 25};

            // Inserindo valores na BST
            System.out.println("Estrutura da Árvore Binária de Busca (BST):");
            for (int value : valuesEx3) {
                bst.insert(value);
            }
            bst.display();
            System.out.println("Altura da BST: " + bst.getHeight());
            System.out.println("Média dos níveis da BST: " + bst.averageLevel());

            System.out.println("\n-----------------------------\n");

            // Inserindo valores na AVL
            System.out.println("Estrutura da Árvore AVL:");
            for (int value : valuesEx3) {
                avl.insert(value);
            }
            avl.display();
            System.out.println("Altura da AVL: " + avl.getHeight());
            System.out.println("Média dos níveis da AVL: " + avl.averageLevel());

            // Fechar o arquivo de saída
            fileOut.close();

        } catch (FileNotFoundException e) {
            System.err.println("Erro ao criar o arquivo de saída: " + e.getMessage());
        }
    }
}



