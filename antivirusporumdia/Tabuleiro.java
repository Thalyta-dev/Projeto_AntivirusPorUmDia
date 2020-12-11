/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antivirusporumdia;

import java.util.Random;

public class Tabuleiro {

    private Player1 p1;
    private Player2 p2;
    Jogador jogador;
    String tracos = " |---"; // parede
    String tracosPorta = " |-*-"; //porta
    String separacao = "|    ";  //parede
    String parede = " |";
    String parede2 = "|";
    String porta = "*    "; // porta
    String psInicial = "* C  ";
    String infeccao = "| X  ";
    String[][] matrizPrint = new String[11][6];
    String[][] setorPrint = new String[5][7];
    Setor setor;

    public Tabuleiro() {
        //  p1 = new Player1();
    }

    public Player1 getP1() {
        return p1;
    }

    public void setP1(Player1 p1) {
        this.p1 = p1;
    }

    public Player2 getP2() {
        return p2;
    }

    public void setP2(Player2 P2) {
        this.p2 = P2;
    }

    public void contruindoMatriz() { // tabuleiro inicial

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 6; j++) {

                if ((i == 4 && j == 2) || (i == 5 && j == 2) || (i == 5 && j == 3) || (i == 6 && j == 2)) { // Pos inicial

                    if (i == 5) {
                        if (j == 2) { //PosiçãoInicial
                            this.matrizPrint[i][j] = porta;
                        } else if (j == 3) {
                            this.matrizPrint[i][j] = porta;
                        }
                    } else {
                        this.matrizPrint[i][j] = tracosPorta;
                    }

                } else { // restante da matriz

                    if (i % 2 == 0) { //Linhas pares que contem os tracos
                        if (j == 5) {
                            this.matrizPrint[i][j] = parede; //final da matriz vertical
                        } else {
                            this.matrizPrint[i][j] = tracos;
                        }

                    } else {
                        if (j == 5) { // preeencher a matrizPrint do lado
                            this.matrizPrint[i][j] = parede2;

                        } else {
                            this.matrizPrint[i][j] = separacao;
                        }
                    }

                }

            }

            // System.out.println();
        }
        this.gerarInfeccao();
    }

    public void gerarInfeccao() { // gerando a localização do virus

        int linhaInfeccao;
        int colunaInfeccao;
        boolean condicaoSatisfeita = false;

        Random aleatorio = new Random();
        do {
            linhaInfeccao = aleatorio.nextInt(10);
            colunaInfeccao = aleatorio.nextInt(4);

            if (linhaInfeccao % 2 != 0 && linhaInfeccao != 5) {
                this.matrizPrint[linhaInfeccao][colunaInfeccao] = infeccao;

                condicaoSatisfeita = true;

            }
        } while (condicaoSatisfeita == false);

    }

    public void matrizAtual(int linha, int coluna, Player1 play, Player1 play2) {
        int cont = 1;
        // System.out.printf("  ----------------------\n | Antivirus por um dia | \n  ----------------------\n");
        System.out.printf("\n    1    2    3    4    5\n");

        for (int i = 0; i < 11; i++) {

            if (i % 2 != 0) {
                System.out.printf("%d", cont); // enumeração das linhas
                cont += 1;
            }
            for (int j = 0; j < 6; j++) {
                if (i == 5 && j == 2) { //PosiçãoInicial
                    System.out.printf(psInicial); // Se for a porta inicializa com C                  
                } else {

                    if (play.indiceInicialEsquerda[0] == i && play.indiceInicialEsquerda[1] == j) {
                        if (play.indiceInicialEsquerda[0] == play2.indiceInicialEsquerda[0] && play.indiceInicialEsquerda[1] == play2.indiceInicialEsquerda[1]) {
                            System.out.print(matrizPrint[i][j].charAt(0) + " P  ");
                        }else{
                            System.out.print(matrizPrint[i][j].charAt(0) + " P1 ");
                        }

                    } else if (play2.indiceInicialEsquerda[0] == i && play2.indiceInicialEsquerda[1] == j) {

                        System.out.print(matrizPrint[i][j].charAt(0) + " P2 ");

                    } else {

                        System.out.printf(matrizPrint[i][j]);
                    }
                }
            }
            System.out.println();
        }
        System.out.printf("        [" + (linha + 1) + "/" + (coluna + 1) + "]");
        this.printarSetor();
    }

    public void construirSetor(Player1 play) {
        //System.out.println();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {

                if (j == 0 || j == 6) { // extremidades verticais
                    if (i == 2 && j == 0) {

                        setorPrint[i][j] = setorPrint[i][j] = this.matrizPrint[play.indiceInicialEsquerda[0]][play.indiceInicialEsquerda[1]].charAt(0) +"";
                                

                    } else if (i == 2 && j == 6) {
                        setorPrint[i][j] = this.matrizPrint[play.indiceInicialDireita[0]][play.indiceInicialDireita[1]].charAt(0)+"";

                    } else {
                        setorPrint[i][j] = "|";

                    }
                } else if (i == 0 || i == 4) {
                    if (i == 0 && j == 3) {
                        setorPrint[i][j] = "--"+this.matrizPrint[play.indiceInicialEmcima[0]][play.indiceInicialEmcima[1]].charAt(3)+"--";

                    } else if (i == 4 && j == 3) {
                        setorPrint[i][j] = "--"+this.matrizPrint[play.indiceInicialEmBaixo[0]][play.indiceInicialEmBaixo[1]].charAt(3)+"--";
                        
                    } else {

                        setorPrint[i][j] = "-----";
                    }
                } else {
                    setorPrint[i][j] = "     ";
                }
            }
            // System.out.println();
        }
    }

    public void printarSetor() {

        System.out.println();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                // if (j == 0 || j == 4) { // extremidades verticais
                System.out.print(setorPrint[i][j]);
                // }

            }
            System.out.println();
        }
    }

}
