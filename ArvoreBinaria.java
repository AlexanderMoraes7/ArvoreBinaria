package ArvoreBinaria;

public class No {
    private long id;
    private Object elemento;
    private No esq;
    private No dir;

    public No(long id, Object elemento, No esq, No dir) {
        this.id = id;
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void setElemento(Object elemento) {
        this.elemento = elemento;
    }

    public Object getElemento() {
        return this.elemento;
    }

    public void setEsq(No esq) {
        this.esq = esq;
    }

    public No getEsq() {
        return this.esq;
    }

    public void setDir(No dir) {
        this.dir = dir;
    }

    public No getDir() {
        return this.dir;
    }

    private void preFixado(No atual) {
        if (atual != null) {
            System.out.println("Id: " + atual.getId() + " Elemento: " + atual.getElemento());
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
            e = calcAltura(atual.getEsq(), a)+1;
            d = calcAltura(atual.getDir(), a)+1;
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
}
