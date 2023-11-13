package ArvoreBinaria;

public class ExemploArvoreBinaria {
    public static void main(String[] args) {
        ArvoreBinaria a = new ArvoreBinaria(); // cria a árvore binária
        a.insere(10,"A");
        a.insere(5,"B");
        a.insere(15,"C");
        a.insere(2,"D");
        a.insere(7,"E");
        a.insere(12,"F");
        a.insere(6,"G");
        a.insere(8,"H");
        a.insere(1, "I");
        a.insere(11, "J");
        a.insere(20, "K");
        a.insere(3, "L");
        a.insere(13, "M");
        a.imprimeElementosArvore();
        System.out.println("Altura: " + a.alturaArvore());
    }
}
