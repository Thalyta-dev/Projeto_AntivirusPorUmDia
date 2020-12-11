package antivirusporumdia;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Setor {

    private int jogada;
    private ArrayList<Inimigo> inimigos;
    private Player1 jogador, jogadorAux;
    int[][] matriz = new int[5][5];
    Inimigo inimigo = new Inimigo();

    private Tabuleiro tab;

    public Player1 getJogador() {
        return jogador;
    }

    public void setJogador(Player1 jogador) {
        this.jogador = jogador;
    }

    public Setor() {
    }

    public Setor(Player1 p1, ArrayList<Inimigo> inimigos, Tabuleiro tab, int[][] matriz) {

        this.tab = tab;
        this.jogador = p1;
        this.inimigos = inimigos;
        jogadorAux = new Player1();
        this.matriz = matriz;

    }

    public Setor(Player2 p2, ArrayList<Inimigo> inimigos, Tabuleiro tab, int[][] matriz) {

        this.tab = tab;
        this.jogador = p2;
        this.inimigos = inimigos;
        jogadorAux = new Player1();
        this.matriz = matriz;

    }

    public Tabuleiro getTab() {
        return tab;
    }

    public void setTab(Tabuleiro tab) {
        this.tab = tab;
    }

    public int getJogada() {
        return jogada;
    }

    public void setJogada(int jogada) {
        this.jogada = jogada;
    }

    public int tipoSetor() {

        Random aleatorio = new Random();
        int tipoDeSetor = aleatorio.nextInt(3) + 1;
        return tipoDeSetor;

    }

    public ArrayList<Inimigo> Inimigos() {

        Random aleatorio = new Random();
        int QuantInimigo;

        QuantInimigo = aleatorio.nextInt(5) + 1;

        ArrayList<Inimigo> inimigos = new ArrayList<Inimigo>();

        // System.out.println("NUMERO DE INIMIGOS" +QuantInimigo);
        for (int i = 0; i < QuantInimigo; i++) {
            inimigos.add(this.inimigo.criarInimigo());
            // System.out.println(inimigos.get(i).getDefesa());

        }
        return inimigos;
    }

    public boolean inimigosMortos(ArrayList<Inimigo> inimigos) {

        int cont = 0;
        for (int i = 0; i < inimigos.size(); i++) {
            if (inimigos.get(i).getDefesa() <= 0) {
                cont += 1;
            }
        }
        if (inimigos.size() == cont) { //SE TODAS AS POSIÇÕES TIVER 0 É PQ ESTAO TODOS MORTOS
            return true;
        }
        return false;
    }

    public boolean verificarExtremidade(int a, int b) {

        if (a <= 4 && a >= 0 && b <= 4 && b >= 0) {
            return true;
        }

        return false;
    }

    public void gerarPortas(Tabuleiro tab) {

        int pos[] = new int[8];
        int baixo = this.jogador.i + 1;
        int cima = this.jogador.i - 1;
        int ladoEsquerdo = this.jogador.j - 1;
        int ladoDireito = this.jogador.j + 1;

        pos[0] = this.jogador.indiceInicialEmcima[0];
        pos[1] = this.jogador.indiceInicialEmcima[1];
        pos[2] = this.jogador.indiceInicialDireita[0];
        pos[3] = this.jogador.indiceInicialDireita[1];
        pos[4] = this.jogador.indiceInicialEsquerda[0];
        pos[5] = this.jogador.indiceInicialEsquerda[1];
        pos[6] = this.jogador.indiceInicialEmBaixo[0];
        pos[7] = this.jogador.indiceInicialEmBaixo[1];

        if (matriz[this.jogador.i][this.jogador.j] != 0) {  //verificar se o setor ja foi visitado
            System.out.println("SETOR JA VISITADO" + this.jogador.i + "/" + this.jogador.j);
        } else {

            if (verificarExtremidade(this.jogador.i, ladoEsquerdo)) {

                if (matriz[this.jogador.i][ladoEsquerdo] != 0) {

                    pos[4] = -1;
                    pos[5] = -1;
                }
            }

            if (verificarExtremidade(this.jogador.i, ladoDireito)) { //verificando setor a direita
                if (matriz[this.jogador.i][ladoDireito] != 0) {
                    pos[2] = -1;
                    pos[3] = -1;

                }
            }

            if (verificarExtremidade(cima, this.jogador.j)) { //verificando setor a cima
                if (matriz[cima][this.jogador.j] != 0) {
                    pos[0] = -1;
                    pos[1] = -1;
                }
            }

            if (verificarExtremidade(baixo, this.jogador.j)) { //verificando setor a baixo
                if (matriz[baixo][this.jogador.j] != 0) {
                    pos[6] = -1;
                    pos[7] = -1;
                }
            }
            Random portas = new Random();

            for (int j = 0; j < 8; j += 2) { //percorrendo o vetor respeitando quanbtidades de possiveis portas
                int k = j + 1;

                int numeroDePortas = portas.nextInt(10); // gerar aleatoriamente se tera verificarPorta ou n
                System.out.println("Random:" + numeroDePortas);
                System.out.print("indice1:" + pos[j]);
                System.out.println("indice2:" + pos[k]);
                if (numeroDePortas > 2) {

                    if (pos[j] != -1) {
                        if (tab.tracos.equals(tab.matrizPrint[pos[j]][pos[k]]) && pos[j] > 0) { // verificando se ja não é uma porta
                            tab.matrizPrint[pos[j]][pos[k]] = tab.tracosPorta;
                        }
                        if (tab.separacao.equals(tab.matrizPrint[pos[j]][pos[k]]) && pos[k] > 0) {  // verificando se ja não é uma porta
                            tab.matrizPrint[pos[j]][pos[k]] = tab.porta;
                        }
                        if (tab.infeccao.equals(tab.matrizPrint[pos[j]][pos[k]]) && pos[k] > 0) {  // verificando se ja não é uma porta
                            tab.matrizPrint[pos[j]][pos[k]] = "* X  ";
                        }
                    }
                }

            }

        }
    }

    public int jogar(int playerMesmoSetor, int vezJogador, Tabuleiro tab, int setor, Player1 jogador1) {

        Scanner input = new Scanner(System.in);
        int j;
        int linhaSetor = 1;
        int atacar = 0;
        this.setJogada(2);
        int decisao = 0;
        int varAuxiliar;
        boolean continuar = true;
        int auxiliar;

        System.out.println(" ---- TURNO JOGADOR " + vezJogador + " ----");
        if (vezJogador == 2) {
            tab.setorPrint[2][3] = "  P2 "; // printando jogador
        } else {
            tab.setorPrint[2][3] = "  P1 "; // printando jogador  
        }
        tab.setorPrint[3][3] = " " + Integer.toString(this.jogador.getAtaque()) + "/" + Integer.toString(this.jogador.getDefesa()) + " "; // printando ataque e defesa do jogador

        while (jogada > 0) {

            if (matriz[this.jogador.i][this.jogador.j] != 0) { // zero é quando o setor ja foi visitado
                if (matriz[this.jogador.i][this.jogador.j] != 3) {
                    System.out.printf("--------------------\n"
                            + "|   SETOR VISITADO   |\n"
                            + "--------------------\n");
                    System.out.printf("--------------------\n"
                            + "|   APERTE 1 PARA    |\n"
                            + "|   PARA PROCURAR    |\n"
                            + "--------------------\n");
                    do {
                        try {

                            do {
                                decisao = input.nextInt();
                            } while (decisao != 1);

                            continuar = false;
                        } catch (InputMismatchException e) {
                            System.out.printf("--------------------\n"
                                    + "|   NÃO É UM NÚMERO  |\n"
                                    + "--------------------\n");
                            input.nextLine();
                        }
                    } while (continuar);
                    if (decisao == 1) {

                        this.jogador.procurar(inimigos);
                        tab.setorPrint[3][3] = " " + Integer.toString(this.jogador.getAtaque()) + "/" + Integer.toString(this.jogador.getDefesa()) + " "; // printando ataque e defesa do jogador
                    }
                    this.setJogada(this.getJogada() - 2);
                } else {
                    System.out.printf("--------------------\n"
                            + "|   SEM PROCURA     |\n"
                            + "|   SETOR PRIVADO   |\n"
                            + "---------------------\n");
                    this.setJogada(this.getJogada() - 2);
                }

            } else {
                linhaSetor=1;
                for (j = 0; j < this.inimigos.size(); j++) { // printar no setor os inimigos
                   // if (this.inimigos.get(j).getDefesa() > 0) {
                        tab.setorPrint[1][linhaSetor] = " " + Integer.toString(this.inimigos.get(j).getAtaque()) + "/" + Integer.toString(this.inimigos.get(j).getDefesa()) + " ";
                        linhaSetor += 1;
                    //}

                }
                if (vezJogador == 2) {
                    tab.matrizAtual(this.jogador.i, this.jogador.j, (Player1) jogador1, this.jogador);
                } else {
                    tab.matrizAtual(this.jogador.i, this.jogador.j, this.jogador, this.jogadorAux);
                }
                if (vezJogador == 2) {

                    System.out.printf("---------------------\n"
                            + "|    QUAL AÇÃO       |\n"
                            + "|   DESEJA FAZER?    |\n"
                            + "---------------------\n");
                    if (this.inimigosMortos(inimigos)) {
                        System.out.printf("---------------------\n"
                                + "| 2| PROCURAR         |\n"
                                + "| 3| RECUPERAR DEFESA |\n"
                                + "----------------------\n");

                    } else {
                        System.out.printf("---------------------\n"
                                + "| 1| ATAQUE           |\n"
                                + "| 2| PROCURAR         |\n"
                                + "| 3| RECUPERAR DEFESA |\n"
                                + "---------------------\n");
                    }
                    do {
                        try {
                            do {
                                decisao = input.nextInt();
                            } while (decisao > 3 || decisao < 1);
                            continuar = false;
                        } catch (InputMismatchException e) {
                            System.out.printf("---------------------\n"
                                    + "|   NÃO É UM NÚMERO   |\n"
                                    + "----------------------\n");
                            input.nextLine();
                        }
                    } while (continuar);

                } else {
                    //

                    System.out.printf("---------------------\n"
                            + "|    QUAL AÇÃO        |\n"
                            + "|   DESEJA FAZER?     |\n"
                            + "---------------------\n");
                    if (this.inimigosMortos(inimigos)) {

                        System.out.printf("---------------------\n"
                                + "|   2| PROCURAR      |\n"
                                + "----------------------\n");
                        do {
                            try {
                                do {
                                    decisao = input.nextInt();
                                } while (decisao > 2 || decisao < 1);

                                continuar = false;
                            } catch (InputMismatchException e) {

                                System.out.printf("---------------------\n"
                                        + "|   NÃO É UM NÚMERO   |\n"
                                        + "----------------------\n");
                                input.nextLine();
                            }
                        } while (continuar);

                    } else {
                        System.out.printf("---------------------\n"
                                + "| 1| ATACAR           |\n"
                                + "| 2| PROCURAR         |\n"
                                + "----------------------\n");
                        do {
                            try {
                                do {
                                    decisao = input.nextInt();
                                    auxiliar = decisao;
                                } while (auxiliar > 2 || auxiliar < 1);
                                continuar = false;
                            } catch (InputMismatchException e) {

                                System.out.printf("---------------------\n"
                                        + "|   NÃO É UM NÚMERO   |\n"
                                        + "----------------------\n");
                                input.nextLine();
                            }
                        } while (continuar);
                    }
                }
            }

            switch (decisao) {

                case 1:
                    if (this.inimigosMortos(this.inimigos) == false) {

                        do {
                            System.out.printf("---------------------\n"
                                    + "|    QUAL INIMIGO     |\n"
                                    + "|   DESEJA ATACAR?    |\n"
                                    + "---------------------\n");
                            System.out.printf("---------------------\n"
                                    + "| PARA CANCELAR AÇÃO  |\n"
                                    + "|      DIGITE 0       |\n"
                                    + "---------------------\n");

                            do {
                                try {
                                    do {
                                        decisao = input.nextInt();
                                    } while (decisao < 0 || (decisao - 1) > this.inimigos.size());

                                    continuar = false;
                                } catch (InputMismatchException e) {

                                    System.out.printf("---------------------\n"
                                            + "|   NÃO É UM NÚMERO   |\n"
                                            + "----------------------\n");
                                    input.nextLine();
                                }
                            } while (continuar);

                            if (decisao == 0) {
                                break;
                            }

                            varAuxiliar = decisao - 1;
                            //  do{    
                            //  try{   
                            if (this.inimigos.get(varAuxiliar).getDefesa() > 0) { // complemntar para que o usuario n digite um numero menor que 0

                                if (setor == 2) {
                                    System.out.printf("---------------------\n"
                                            + "|   SETOR OCULTO      |\n"
                                            + "----------------------\n");
                                    Random aleatorio = new Random();
                                    int ataque = aleatorio.nextInt(10) + 1;

                                    if (ataque > 3) {
                                        atacar = this.jogador.atacar(this.inimigos.get(varAuxiliar));

                                    } else {
                                        atacar = 2;
                                    }
                                } else {
                                    atacar = this.jogador.atacar(this.inimigos.get(varAuxiliar));
                                }
                                // continuar = false;

                            } else {
                                System.out.printf("---------------------\n"
                                        + "|     INIMIGO NÃO     |\n"
                                        + "|        EXISTE       |\n"
                                        + "---------------------\n");
                                atacar = 0;

                                //continuar = false;
                                break;
                            }
                            // }
                            /* catch(ArrayIndexOutOfBoundsException e){
                                 System.out.printf("---------------------\n"
                                                    +"|     INIMIGO NÃO     |\n"
                                                    +"|        EXISTE  atta      |\n"
                                                    + "---------------------\n");
                                input.nextLine();
                            }
                        }while(continuar);    */
                            if (atacar != 0) {

                                // varAuxiliar = decisao - 1;
                                if (atacar == 2) {
                                    this.setJogada(getJogada() - 1);
                                    System.out.printf("---------------------\n"
                                            + "|    VOCÊ ERROU       |\n"
                                            + "----------------------\n");
                                    System.out.printf("ENTROU AQUI errro");

                                } else {
                                    if (this.inimigos.get(varAuxiliar).getDefesa() <= 0) {
                                        System.out.printf("---------------------\n"
                                                + "|     INIMIGO " + decisao + " |\n"
                                                + "|        MORREU       |\n"
                                                + "---------------------\n");

                                        this.inimigos.remove(varAuxiliar);
                                    } else {
                                        System.out.printf("---------------------\n"
                                                + "| INIMIGO " + decisao + "|\n"
                                                + "| PERDEU " + this.jogador.getAtaque() + " DE DEFESA|\n"
                                                + "---------------------\n");
                                    }
                                    /* for (j = 0; j < inimigosPlay1.length; j++) { // printar no setor os inimigos
                                                 tab.setor[1][linhaSetor] = " " +Integer.toString(inimigosPlay1[j].getAtk())+"/"+Integer.toString(inimigosPlay1[j].getDef())+" ";
                                            }*/

                                    this.setJogada(getJogada() - 1);
                                    System.out.printf("---------------------\n"
                                            + "|  RESTAM " + this.getJogada() + " jogada|\n"
                                            + "---------------------\n");
                                }
                            }
                        } while (atacar == 0);

                    } else {
                        System.out.printf("---------------------\n"
                                + "|    NÃO HÁ INIMIGO   |\n"
                                + "---------------------\n");
                    }
                    break;

                case 2:

                    this.jogador.procurar(this.inimigos);

                    /* for (j = 0; j < this.inimigos.length; j++) { // printar no setor os inimigos
                        if (this.inimigos[j].getDefesa() > 0) {
                            tab.setorPrint[1][linhaSetor] = " " + Integer.toString(this.inimigos[j].getAtaque()) + "/" + Integer.toString(this.inimigos[j].getDefesa()) + " ";
                        }
                        linhaSetor += 1;
                    }*/
                    this.setJogada(getJogada() - 1);
                    System.out.printf("---------------------\n"
                            + "|  RESTAM " + this.getJogada() + "  |\n"
                            + "---------------------\n");
                    tab.setorPrint[3][3] = " " + Integer.toString(this.jogador.getAtaque()) + "/" + Integer.toString(this.jogador.getDefesa()) + " "; // printando ataque e defesa do jogador
                    break;

                case 3:

                    if (playerMesmoSetor == 1) {

                        System.out.printf("-----------------------------\n"
                                + "| 1| RECUPERAR DEFESA PLAYER2 |\n"
                                + "| 2| RECUPERAR DEFESA PLAYER1 |\n"
                                + "-----------------------------\n");
                        do {
                            try {
                                do {
                                    decisao = input.nextInt();

                                } while (decisao < 1 || decisao > 2);

                                continuar = false;
                            } catch (InputMismatchException e) {

                                System.out.printf("---------------------\n"
                                        + "|   NÃO É UM NÚMERO   |\n"
                                        + "----------------------\n");
                                input.nextLine();
                            }
                        } while (continuar);

                        this.jogador.recuperarDefesa(decisao, jogador1);
                    } else {

                        this.jogador.recuperarDefesa(1, jogador1);
                    }
                    tab.setorPrint[3][3] = " " + Integer.toString(this.jogador.getAtaque()) + "/" + Integer.toString(this.jogador.getDefesa()) + " "; // printando ataque e defesa do jogador
                    this.setJogada(getJogada() - 1);
                    System.out.printf("---------------------\n"
                            + "|  RESTAM " + this.getJogada() + "  |\n"
                            + "---------------------\n");
                    break;

                default:

                    System.out.printf("----------------------\n"
                            + "| ESCOLHA OPÇÃO VALIDA |\n"
                            + "----------------------\n");
                    break;
            }

        }
        return 0;
    }

}
