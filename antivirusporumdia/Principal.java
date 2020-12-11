package antivirusporumdia;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

    public static void naoSei() {
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Jogador play1 = new Player1(2, 6); //polimorfismo 
        Player1 play2 = new Player2(1, 7);
        Tabuleiro tab = new Tabuleiro();
        Setor set = new Setor();
        ArrayList<Inimigo> inimigos1 = set.Inimigos();
        ArrayList<Inimigo> inimigos2 = set.Inimigos();
        Setor set2;
        int quantidadeJogador = 0;
        int turnos = 0;
        tab.contruindoMatriz();
        tab.construirSetor((Player1) play1);
        boolean movimento = false;
        boolean continuar = true;
        char inputMovimento;
        int jogadores;
        int[][] matrizVisitado = new int[5][5];

        System.out.printf("   -----------------------"
                + "\n | Antivirus por um dia | \n"
                + " ------------------------\n");
        System.out.printf(" -------------------------------\n "
                + "| Qual a quantidade de jogador | "
                + "\n-------------------------------\n");
        System.out.printf("--------------------\n"
                + "| 1| UM JOGADOR     |\n"
                + "| 2| DOIS JOGADORES |\n"
                + "--------------------\n");
        do {
            try {

                quantidadeJogador = input.nextInt();
                continuar = false;
            } catch (InputMismatchException e) {
                System.out.printf(" NÃO É UM NÚMERO");
                input.nextLine();
            }

            jogadores = quantidadeJogador;
        } while (continuar);

        while (turnos <= 25) { //turnos dos jogadores

            System.out.printf("\n-----TURNO " + turnos + "--------\n");
            int setorPlay1 = set.tipoSetor();
            int setorPlay2 = set.tipoSetor();

            if (turnos == 0) {

                System.out.println("\n-----TURNO DO JOGADOR 1--------\n");
                System.out.println();
                // System.out.printf("\n-----SETOR " + setorPlay1 + "--------\n");
                tab.construirSetor((Player1) play1);
                set2 = new Setor((Player1) play1, inimigos1, tab, matrizVisitado);
                tab.matrizAtual(play2.i, play2.j, (Player1) play1, play2);
                play1.movimentoJogador(input.next().charAt(0), tab);
                if (tab.infeccao.equals(tab.matrizPrint[play1.indiceInicialEsquerda[0]][play1.indiceInicialEsquerda[1]])) {

                    System.out.printf("--------------------\n"
                            + "|     PLAYER1       |\n"
                            + "|     GANHOU          |\n"
                            + "---------------------\n");
                    break;
                }

                set2.gerarPortas(tab);
                tab.construirSetor((Player1) play1);
                set2.jogar(0, 1, tab, setorPlay1, (Player1) play1);

            } else {
                System.out.printf("\n-----TURNO DO JOGADOR 1--------\n");

                if (play1.i == play2.i && play1.j == play2.j) { //verificando se esta no mesmo setor 

                    inimigos1 = inimigos2;
                    System.out.println("\n-----TURNO DO JOGADOR 1--------\n");
                    if (set.inimigosMortos(inimigos1)) {

                        tab.matrizAtual(play2.i, play2.j, (Player1) play1, play2);
                        while (movimento == false) {

                            inputMovimento = input.next().charAt(0);
                            movimento = play1.movimentoJogador(inputMovimento, tab);
                            System.out.println(movimento);
                        }
                        movimento = false;
                        tab.construirSetor((Player1) play1);
                        setorPlay1 = setorPlay2;
                        System.out.printf("\n-----SETOR " + setorPlay1 + "--------\n");
                        if (tab.infeccao.equals(tab.matrizPrint[play1.indiceInicialEsquerda[0]][play1.indiceInicialEsquerda[1]])) {

                            System.out.printf("--------------------\n"
                                    + "|     PLAYER1       |\n"
                                    + "|     GANHOU          |\n"
                                    + "---------------------\n");
                            break;
                        }
                        if (matrizVisitado[play1.i][play1.j] == 0) {

                            set2 = new Setor((Player1) play1, inimigos1 = set.Inimigos(), tab, matrizVisitado);
                            set2.gerarPortas(tab);
                            tab.matrizAtual(play2.i, play2.j, (Player1) play1, play2);
                            tab.construirSetor((Player1) play1);
                            set2.jogar(1, 1, tab, setorPlay2, (Player1) play1);
                            tab.construirSetor((Player1) play1);
                        } else {
                            set2 = new Setor((Player1) play1, inimigos1, tab, matrizVisitado);
                            tab.matrizAtual(play2.i, play2.j, (Player1) play1, play2);
                            tab.construirSetor((Player1) play1);
                            set2.jogar(1, 1, tab, setorPlay2, (Player1) play1);
                            tab.construirSetor((Player1) play1);
                        }
                    } else {

                        set2 = new Setor((Player1) play1, inimigos1, tab, matrizVisitado);
                        tab.matrizAtual(play2.i, play2.j, (Player1) play1, play2);
                        tab.construirSetor((Player1) play1);
                        set2.jogar(1, 1, tab, setorPlay2, (Player1) play1);
                        tab.construirSetor((Player1) play1);

                    }
                } else {

                    if (set.inimigosMortos(inimigos1)) {
                        System.out.println("\n-----TURNO DO JOGADOR 1--------\n");
                        tab.matrizAtual(play2.i, play2.j, (Player1) play1, play2);
                        while (movimento == false) {

                            inputMovimento = input.next().charAt(0);
                            movimento = play1.movimentoJogador(inputMovimento, tab);
                            System.out.println(movimento);

                        }
                        movimento = false;
                        System.out.printf("\n-----SETOR " + setorPlay1 + "--------\n");

                        if (tab.infeccao.equals(tab.matrizPrint[play1.indiceInicialEsquerda[0]][play1.indiceInicialEsquerda[1]])) {

                            System.out.printf("--------------------\n"
                                    + "|     PLAYER1       |\n"
                                    + "|     GANHOU          |\n"
                                    + "---------------------\n");
                            break;
                        }
                        if (matrizVisitado[play1.i][play1.j] == 0) {
                            set2 = new Setor((Player1) play1, inimigos1 = set.Inimigos(), tab, matrizVisitado);
                            set2.gerarPortas(tab);
                            tab.matrizAtual(play2.i, play2.j, (Player1) play1, play2);
                            tab.construirSetor((Player1) play1);
                            set2.jogar(0, 1, tab, setorPlay1, (Player1) play1);
                            tab.construirSetor((Player1) play1);
                        } else {
                            set2 = new Setor((Player1) play1, inimigos1, tab, matrizVisitado);
                            set2.gerarPortas(tab);
                            tab.matrizAtual(play2.i, play2.j, (Player1) play1, play2);
                            tab.construirSetor((Player1) play1);
                            set2.jogar(0, 1, tab, setorPlay1, (Player1) play1);
                            tab.construirSetor((Player1) play1);
                        }
                    } else {

                        set2 = new Setor((Player1) play1, inimigos1, tab, matrizVisitado);
                        tab.construirSetor((Player1) play1);
                        set2.jogar(0, 1, tab, setorPlay1, (Player1) play1);
                        tab.construirSetor((Player1) play1);

                    }

                }
            }
            if (jogadores == 2) {

                if (turnos == 0) {

                    System.out.printf("\n-----TURNO DO JOGADOR 2--------\n");

                    tab.construirSetor(play2);

                    tab.matrizAtual(play2.i, play2.j, (Player1) play1, play2);

                    while (movimento == false) {

                        inputMovimento = input.next().charAt(0);
                        movimento = play2.movimentoJogador(inputMovimento, tab);
                        System.out.println(movimento);
                    }
                    movimento = false;
                    System.out.printf("\n-----SETOR " + setorPlay2 + "--------\n");
                    if (tab.infeccao.equals(tab.matrizPrint[play1.indiceInicialEsquerda[0]][play1.indiceInicialEsquerda[1]])) {

                        System.out.printf("--------------------\n"
                                + "|     PLAYER1       |\n"
                                + "|     GANHOU          |\n"
                                + "---------------------\n");
                        break;
                    }
                    if (play1.i == play2.i && play1.j == play2.j) {
                        inimigos2 = inimigos1;
                        tab.construirSetor(play2);
                        set2 = new Setor((Player2) play2, inimigos2, tab, matrizVisitado);
                        set2.jogar(1, 2, tab, setorPlay1, (Player1) play1);
                        tab.matrizAtual(play2.i, play2.j, (Player1) play1, play2);

                    } else {
                        if (matrizVisitado[play2.i][play2.j] == 0) {
                            set2 = new Setor((Player2) play2, inimigos2 = set.Inimigos(), tab, matrizVisitado);
                            set2.gerarPortas(tab);
                            tab.matrizAtual(play2.i, play2.j, (Player1) play1, play2);
                            tab.construirSetor(play2);
                            set2.jogar(0, 2, tab, setorPlay2, (Player1) play1);
                            tab.construirSetor(play2);
                        } else {
                            set2 = new Setor((Player2) play2, inimigos2, tab, matrizVisitado);
                            set2.gerarPortas(tab);
                            tab.matrizAtual(play2.i, play2.j, (Player1) play1, play2);
                            tab.construirSetor(play2);
                            set2.jogar(0, 2, tab, setorPlay2, (Player1) play1);
                            tab.construirSetor(play2);
                        }
                    }

                    if (tab.infeccao.equals(tab.matrizPrint[play1.indiceInicialEsquerda[0]][play1.indiceInicialEsquerda[1]])) {

                        System.out.printf("--------------------\n"
                                + "|     PLAYER2       |\n"
                                + "|     GANHOU          |\n"
                                + "---------------------\n");
                        break;
                    }

                } else {
                    if (play1.i == play2.i && play1.j == play2.j) { //verificando se esta no mesmo setor

                        inimigos2 = inimigos1;
                        if (set.inimigosMortos(inimigos2)) {
                            System.out.printf("\n-----TURNO DO JOGADOR 1--------\n");
                            tab.matrizAtual(play2.i, play2.j, (Player1) play1, play2);
                            while (movimento == false) {

                                inputMovimento = input.next().charAt(0);
                                movimento = play2.movimentoJogador(inputMovimento, tab);
                                System.out.println(movimento);
                            }
                            movimento = false;
                            tab.construirSetor(play2);
                            setorPlay2 = setorPlay1;
                            System.out.printf("\n-----SETOR " + setorPlay2 + "--------\n");
                            if (tab.infeccao.equals(tab.matrizPrint[play1.indiceInicialEsquerda[0]][play1.indiceInicialEsquerda[1]])) {

                                System.out.printf("--------------------\n"
                                        + "|     PLAYER2       |\n"
                                        + "|     GANHOU          |\n"
                                        + "---------------------\n");
                                break;
                            }
                            if (matrizVisitado[play2.i][play2.j] == 0) {
                                set2.gerarPortas(tab);
                                set2 = new Setor((Player2) play2, inimigos2 = set.Inimigos(), tab, matrizVisitado);
                                set2.jogar(1, 2, tab, setorPlay1, (Player1) play1);
                                tab.matrizAtual(play2.i, play2.j, (Player1) play1, play2);
                            } else {
                                set2.gerarPortas(tab);
                                set2 = new Setor((Player2) play2, inimigos2, tab, matrizVisitado);
                                set2.jogar(1, 2, tab, setorPlay1, (Player1) play1);
                                tab.matrizAtual(play2.i, play2.j, (Player1) play1, play2);
                            }
                            set2 = new Setor((Player2) play2, inimigos2, tab, matrizVisitado);
                            tab.construirSetor(play2);
                            set2.jogar(1, 2, tab, setorPlay1, (Player1) play1);
                            tab.matrizAtual(play2.i, play2.j, (Player1) play1, play2);

                        } else {
                            if (tab.infeccao.equals(tab.matrizPrint[play1.indiceInicialEsquerda[0]][play1.indiceInicialEsquerda[1]])) {

                                System.out.printf("--------------------\n"
                                        + "|     PLAYER1       |\n"
                                        + "|     GANHOU          |\n"
                                        + "---------------------\n");
                                break;
                            }

                            set2 = new Setor((Player2) play2, inimigos2, tab, matrizVisitado);
                            tab.construirSetor(play2);
                            set2.jogar(1, 2, tab, setorPlay1, (Player1) play1);
                            tab.construirSetor(play2);

                        }
                    } else {

                        if (set.inimigosMortos(inimigos2)) {
                            System.out.printf("\n-----TURNO DO JOGADOR 2--------\n");
                            tab.matrizAtual(play2.i, play2.j, (Player1) play1, play2);
                            while (movimento == false) {

                                inputMovimento = input.next().charAt(0);
                                movimento = play2.movimentoJogador(inputMovimento, tab);
                                System.out.println(movimento);

                            }
                            movimento = false;
                            tab.construirSetor(play2);
                            System.out.printf("\n-----SETOR " + setorPlay2 + "--------\n");
                            if (tab.infeccao.equals(tab.matrizPrint[play1.indiceInicialEsquerda[0]][play1.indiceInicialEsquerda[1]])) {

                                System.out.printf("--------------------\n"
                                        + "|     PLAYER1       |\n"
                                        + "|     GANHOU          |\n"
                                        + "---------------------\n");
                                break;
                            }
                            if (matrizVisitado[play2.i][play2.j] == 0) {
                                set2 = new Setor((Player2) play2, inimigos2 = set.Inimigos(), tab, matrizVisitado);
                                set2.gerarPortas(tab);
                                tab.matrizAtual(play2.i, play2.j, (Player1) play1, play2);
                                set2.jogar(0, 2, tab, setorPlay2, (Player1) play1);
                                tab.construirSetor(play2);
                            } else {
                                set2 = new Setor((Player2) play2, inimigos2, tab, matrizVisitado);
                                set2.gerarPortas(tab);
                                tab.matrizAtual(play2.i, play2.j, (Player1) play1, play2);
                                set2.jogar(0, 2, tab, setorPlay2, (Player1) play1);
                                tab.construirSetor(play2);
                            }

                        } else {
                            if (tab.infeccao.equals(tab.matrizPrint[play1.indiceInicialEsquerda[0]][play1.indiceInicialEsquerda[1]])) {

                                System.out.printf("--------------------\n"
                                        + "|     PLAYER1       |\n"
                                        + "|     GANHOU          |\n"
                                        + "---------------------\n");
                                break;
                            }
                            tab.construirSetor(play2);
                            set2 = new Setor((Player2) play2, inimigos2, tab, matrizVisitado);
                            tab.matrizAtual(play2.i, play2.j, (Player1) play1, play2);
                            set2.jogar(0, 2, tab, setorPlay2, (Player1) play1);
                            tab.construirSetor(play2);
                        }

                    }
                }
            }

            if (jogadores == 2) {

                System.out.println("\n----- TURNO DOS INIMIGOS ------\n");

                try {
                    if (play1.indiceInicialEsquerda[0] == play2.indiceInicialEsquerda[0] && play1.indiceInicialEsquerda[1] == play2.indiceInicialEsquerda[1]) {

                        for (int j = 0; j < inimigos1.size(); j++) {
                            Inimigo.atacar((Player1) play1, inimigos1.get(j), 1);
                            Inimigo.atacar(play2, inimigos1.get(j), 2);
                        }
                    } else {
                        for (int j = 0; j < inimigos1.size(); j++) {
                            Inimigo.atacar((Player1) play1, inimigos1.get(j), 1);
                        }

                        for (int j = 0; j < inimigos2.size(); j++) {
                            Inimigo.atacar(play2, inimigos2.get(j), 2);
                        }

                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("TODOS INIMIGOS MORTOS");
                }
                if (play1.getDefesa() <= 0) {
                    System.out.printf("--------------------\n"
                            + "|      GAME         |\n"
                            + "|      OVER         |\n"
                            + "---------------------\n");
                    break;
                }
                if (play2.getDefesa() <= 0) {
                    System.out.printf("--------------------\n"
                            + "|     PLAYER2       |\n"
                            + "|     MORREU        |\n"
                            + "---------------------\n");
                    jogadores = 1;
                }
            } else {
                if (turnos == 0) {

                }

                System.out.println("\n----- TURNO DOS INIMIGOS ------\n");
                try {
                    for (int j = 0; j < inimigos1.size(); j++) {
                        Inimigo.atacar((Player1) play1, inimigos1.get(j), 1);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("TODOS INIMIGOS MORTOS");
                }
                if (play1.getDefesa() <= 0) {
                    System.out.printf("--------------------\n"
                            + "|      GAME         |\n"
                            + "|      OVER         |\n"
                            + "---------------------\n");
                    break;
                }
            }
            System.out.printf("INIMIGOS PALY 1" + set.inimigosMortos(inimigos1));
            if (set.inimigosMortos(inimigos1)) {
                matrizVisitado[play1.i][play1.j] = setorPlay1;
            }
            System.out.printf("INIMIGOS PALY 2" + set.inimigosMortos(inimigos2));

            System.out.print(matrizVisitado[play2.i][play2.j]);
            if (set.inimigosMortos(inimigos2)) {
                matrizVisitado[play2.i][play2.j] = setorPlay2;
            }
            System.out.print(matrizVisitado[play2.i][play2.j]);

            matrizVisitado[2][2] = 1;
            /*
            set2.matriz[play1.i][play1.j] = setorPlay1;
            set2.matriz[play2.i][play2.j] = setorPlay2;
            set2.matriz[2][2] = 1;
            System.out.print(set.matriz[play1.i][play1.j]);
            System.out.print(set.matriz[play2.i][play2.j]);
            System.out.print(set.matriz[2][2]);*/

            turnos += 1;
        }

        // colocar o tipo de setor
        //System.out.println(matriz[this.jogador.i][this.jogador.j]);
        // }
        /* for(int i=0; i<16; i++){
           // do
                if(set.getJogador().movimentoJogador(input.next().charAt(0), set.getTab())){
                    set.gerarPortas();
                    set.getTab().matrizAtual();
                    
                }
           // while(set.getJogador().movimentoJogador(input.next().charAt(0), set.getTab()) == false);*/
    }
    /* public void mesmoSetor(Play1){
        inimigos2 = inimigos1;
                        if (set.inimigosMortos(inimigos2)) {
                            System.out.printf("INIMIGOS MORTOS");
                            while (movimento == false) {

                                inputMovimento = input.next().charAt(0);
                                movimento = play2.movimentoJogador(inputMovimento, tab);
                                System.out.println(movimento);
                            }
                            movimento = false;
                            if (tab.infeccao.equals(tab.matrizPrint[play1.indiceInicialEsquerda[0]][play1.indiceInicialEsquerda[1]])) {

                                System.out.printf("--------------------\n"
                                        + "|     PLAYER2       |\n"
                                        + "|     GANHOU          |\n"
                                        + "---------------------\n");
                                break;
                            }

                            set2 = new Setor((Player2) play2, inimigos1, tab);  
                            set2.jogar(1, 2,tab, setorPlay1,  (Player1) play1);
                            tab.matrizAtual(play2.i, play2.j);

    }*/

}
