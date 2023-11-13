package ArvoreBinaria;

public class ArvoreBinaria {
    private No raiz;

    public ArvoreBinaria() {// construtor da classe Arvore Binaria
    this.raiz = null; // inicia a árvore vazia
    }

    public void insere(long id, Object elemento) { // método para inserção de elemento
        No novoNo = new No(id,elemento,null,null);
        if (raiz == null) {
            raiz = novoNo;
        } else {
            No atual = raiz;
            No pai;
            while (true) {
                pai = atual;
                if (id < atual.getId()) {     // vai inserir à esquerda
                    atual = atual.getEsq();   // Nó da raiz que virou atual 
                    if (atual == null) {      // Se atual for nulo, pode inserir à esquerda
                        pai.setEsq(novoNo);
                        return;
                    } // se não é nulo, vai tentar o próximo filho
                } else { // vai inserir à direita
                    atual = atual.getDir();
                    if (atual == null) { // pode inserir à direita
                        pai.setDir(novoNo);
                        return;
                    }
                }
            }
        }
    } // final método insere

    private void preFixado(No atual) {// caminhamento pré-fixado da árvore binária
        if (atual != null) {
            System.out.println("Id: " + atual.getId() + " Elemento: " + atual.getElemento());
            preFixado(atual.getEsq());
            preFixado(atual.getDir());
        }
    }

    private void posFixado(No atual) {// caminhamento pós-fixado da árvore binária
        if (atual != null) {
            posFixado(atual.getEsq());
            posFixado(atual.getDir());
            System.out.println("Id: " + atual.getId()+ " Elemento: " + atual.getElemento());
        }
    }

    private void inFixado(No atual) {// caminhamento simétrico fixado da árvore binária
        if (atual != null) {
            inFixado(atual.getEsq());
            System.out.println("Id: " + atual.getId() + " Elemento: " + atual.getElemento());
            inFixado(atual.getDir());
        }
    }

    public void imprimeElementosArvore() {// impressão dos elementos da árvore
        inFixado(raiz);
        System.out.println("\n\n");
        posFixado(raiz);
    }

    private long calcAltura(No atual, long a) {// calcula a altura da árvore
        if (atual != null) {
            long e,d;
            e = calcAltura(atual.getEsq(),a)+1;
            d = calcAltura(atual.getDir(),a)+1;
            if (e > d) {
                return a+e;
            } else {
                return a+d;
            }
        }
        return a;
    }

    public long alturaArvore() {
        long a = 0;
        return calcAltura(raiz,a);
    }

    public void insereABB(long id, Object elemento) { // Inserção de ABB 
        if (raiz == null) {
            raiz = new No(id, elemento, null, null);
        } else {
            No novoNo = new No(id, elemento, null, null);
            insere(raiz,novoNo);
        }
    } // Final do método insereABB

    public void insere(No atual, No novo) {  // Inserção Ordenada
        if (atual.getId() == novo.getId()) { // Achou o elemento, nada inserido
            return;
        }
        if (novo.getId() < atual.getId()) { // Vai inserir à esquerda
            if(atual.getEsq() == null) { // Achou a posição e vai inserir
                atual.setEsq(novo);
            } else { // Continua buscando recursivamente à esquerda
                insere(atual.getEsq(), novo);
            }
        }
        if (novo.getId() > atual.getId()) { // Vai inserir à direita
            if(atual.getDir() == null) { // Achou a posição e vai inserir
                atual.setDir(novo);
            } else { // Continua buscando recursivamente à direita
                insere(atual.getDir(), novo);
            }
        }
    }

    public No buscaABB(long id) { // Busca de um elemento na ABB
        return busca(raiz, id);
    }

    private No busca(No atual, long id) { // Busca recursiva na ABB
        if(atual == null) { // não encontrou e retorna null
            return null;
        } else {
            if(id == atual.getId()) { // Achou o elemento
                return atual;
            } else {
                if(id < atual.getId()) {
                    // busca só na subárvore esquerda
                    return busca(atual.getEsq(), id);
                } else {
                    // Busca só na subárvore direita
                    return busca(atual.getDir(), id);
                }
            }
        }
    }

    public boolean removeABB(long id) { // Remove um elemento da ABB
        No atual = raiz;                // a ABB não pode ser vazia
        No pai = raiz;
        boolean filhoEsq = true;
        while (atual.getId() != id) { // Busca o elemento
            pai = atual;
            if(id < atual.getId()){ // Busca à esquerda
                filhoEsq = true;
                atual = atual.getEsq();
            } else { // Busca à direita
                filhoEsq = false;
                atual = atual.getDir();
            }
            if(atual == null) { // Não achou e termina
                return false;
            }
        }
        // Caso 1: elemento não possui filhos
        if(atual.getEsq() == null && atual.getDir() == null) {
            if(atual == raiz) { // Eliminando a raiz da ABB
                raiz = null;
            } else {
                if(filhoEsq) { // O elemento era filho esquerdo
                    pai.setEsq(null);
                } else { // O elemento era filho direito
                    pai.setDir(null);
                }
            }
        } else {
            if(atual.getDir() == null) { // Caso 2: Com apenas o filho esquerdo
                if(atual == raiz) { // Eliminando a raiz
                    raiz = atual.getEsq();
                } else {
                    if(filhoEsq) { // O elemento era filho esquerdo
                        pai.setEsq(atual.getEsq());
                    } else { // O elemento era filho esquerdo
                        pai.setDir(atual.getEsq());
                    }
                }
            } else {
                if(atual.getEsq() == null) { // Caso 2: Com apenas o filho direito
                    if(atual == raiz) { // Eliminando a raiz
                        raiz = atual.getDir();
                    } else {
                        if(filhoEsq) { // O elemento era filho esquerdo
                            pai.setEsq(atual.getDir());
                        }
                    }
                } else { // Caso 3: elemento possui os dois filhos
                    No sucessor = getSucessor(atual); // Busca o elemento sucessor
                    if(atual == raiz){ // Raiz passa a ser o sucessor
                        raiz = sucessor;
                    } else {
                        if(filhoEsq) { // O elemento era filho esquerdo
                            pai.setEsq(sucessor);
                        } else { // O elemento era filho direito
                            pai.setDir(sucessor);
                        }
                    }
                    sucessor.setEsq(atual.getEsq());
                }
            }
        }
        return true;
    }

    private No getSucessor(No eliminado) { // Encontra o próximo menor valor
        No sucessorPai = eliminado;
        No sucessor = eliminado;
        No atual = eliminado.getDir(); // Ir para o filho da direita
        while (atual != null) { // Até não haver mais filhos à esquerda
            sucessorPai = sucessor;
            sucessor = atual;
            atual = atual.getEsq();
        }
        if(sucessor != eliminado.getDir()) { // Se não for o próprio filho direito
            sucessorPai.setEsq(sucessor.getDir());
            sucessor.setDir(eliminado.getDir());
        }
        return sucessor;
    }
} // Final da classe ArvoreBinaria
