public class ArvoreBinaria {
    private No raiz;

    public ArvoreBinaria() {
        this.raiz = raiz;
    }

    private void preFixado(No atual) {
        if (atual != null) {
            System.out.println("Id :" + atual.getId() + " Elemento: " + atual.getElemento());
            preFixado(atual.getEsq());
            preFixado(atual.getDir());
        }
    }

    private void posFixado(No atual) {
        if (atual != null) {
            posFixado(atual.getEsq());
            posFixado(atual.getDir());
            System.out.println("Id: " + atual.getId() + " Elemento: " + atual.getElemento());
        }
    }

    private void simFixado(No atual) {
        if (atual != null) {
            simFixado(atual.getEsq());
            System.out.println("Id: " + atual.getId() + " Elemento: " + atual.getElemento());
            simFixado(atual.getDir());
        }
    }

    public void imprimeElementosArvore() {
        preFixado(raiz);
    }

    private long calcAltura(No atual, long a) {
        if (atual != null) {
            long e, d;
            e = calcAltura(atual.getEsq(), a) + 1;
            d = calcAltura(atual.getDir(), a) + 1;
            if (e > d) {
                return a + e;
            } else {
                return a + d;
            }
        }
        return a;
    }

    public long alturaArvore() {
        long a = 0;
        return calcAltura(raiz, a);
    }

    public void insere(long id, Object elemento) {
        No novoNo = new No(id, elemento, null, null);
        if (raiz == null) {
            raiz = novoNo;
        } else {
            No atual = raiz;
            No pai;
            while (true) {
                pai = atual;
                if (id < atual.getId()) {
                    atual = atual.getEsq();
                    if (atual == null) {
                        pai.setEsq(novoNo);
                        return;
                    }
                } else {
                    atual = atual.getDir();
                    if (atual == null) {
                        pai.setDir(novoNo);
                        return;
                    }
                }
            }
        }
    }
}
