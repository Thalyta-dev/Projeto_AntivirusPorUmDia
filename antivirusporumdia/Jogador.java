
package antivirusporumdia;

import java.util.ArrayList;

public abstract class Jogador {

    
    protected int defesa;
    protected int ataque;
    int i = 2;
    int j = 2; 
    int [][] matriz = new int [5][5];
    int [] indiceInicialEmcima = {4,2};
    int [] indiceInicialEsquerda = {5,2};
    int [] indiceInicialDireita = {5,3};
    int [] indiceInicialEmBaixo = {6,2};

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }
    
    public abstract int atacar(Inimigo inimigo);
    
    public abstract boolean movimentoJogador(char choice, Tabuleiro tab);
    
    public abstract void procurar(ArrayList <Inimigo> inimigos);
    
    
    public void recuperarDefesa(int choice, Jogador play){
        
        if (choice == 1){// se ele escolher a opção 1 é pq quer curar ele mesmo
            
            this.setDefesa(getDefesa() + 2);
            
            System.out.printf("--------------------\n"
                           + "|    SUA DEFESA      |\n"
                           + "|    RECUPEROU +2    |\n"
                           + "--------------------\n");
            
        }else if (choice ==2){ //se ele escolher a opção 2 é pq quer curar o Player 1 

            play.setDefesa(play.getDefesa()+2);
            
            System.out.printf("--------------------\n"
                           + "|   DEFESA PLAYER1   |\n"
                           + "|   RECUPERADA +2    |\n"
                           + "--------------------\n"); 
        }
    }
    
}
