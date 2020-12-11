
package antivirusporumdia;

import java.util.ArrayList;
import java.util.Random;
        

public class Player1 extends Jogador{
    
    protected Inimigo ini;
    
    public Player1(){}
    
    
    public Player1(int ataque, int defesa) {
        this.ataque = ataque;
        this.defesa = defesa;
    }

        public Inimigo getIni() {
        return ini;
    }

    public void setIni(Inimigo ini) {
        this.ini = ini;
    }
    
    @Override
    public int atacar(Inimigo inimigo){
        
        if(inimigo.getDefesa() >0){
            inimigo.setDefesa(inimigo.getDefesa() - this.ataque);
            return 1;
        }else{
            System.out.printf("---------------------\n"
                            +"|    INIMIGO MORTO   |\n"
                            + "---------------------\n");
        }
        return 0;
    }
    
    @Override
    public void procurar(ArrayList<Inimigo> inimigos){ //Metódo procurar incompleto
        Random aleatorio = new Random();
        int procurar = aleatorio.nextInt(5)+1;
        System.out.println(procurar);
        
        switch(procurar){
            
            case 2:

                System.out.printf("---------------------\n"
                               + "|  NADA ENCONTRADO   |\n"
                                + "---------------------\n");
            break;
            
            case 3:
            
            this.setDefesa(this.getDefesa()+1); //setando a defesa do jogador
                System.out.printf("--------------------\n"
                                +"|   GANHOU 1 DE      |\n"
                                +"|      DEFESA        |\n"
                                + "--------------------\n"); 
            
            break;
            
            case 4:
            
            this.setDefesa(this.getDefesa()+2);  // Setando a defesa do jogador
                System.out.printf("--------------------\n"
                                + "|   GANHOU 2 DE     |\n"
                                + "|      DEFESA       |\n"
                                + "--------------------\n");

            break;
            
            case 5:
            
            for(int i =0; i<inimigos.size(); i++){  // Percorrendo o vetor de inimigos
                inimigos.get(i).setDefesa(inimigos.get(i).getDefesa() -1);
                if(inimigos.get(i).getDefesa()<0){
                    inimigos.remove(i);
                }
            }
                System.out.printf("--------------------\n"
                               + "|   INIMIGOS -1 DE   |\n"
                               + "|       DEFESA       |\n"
                                + "--------------------\n");
            break;
            
            default:
                 break;
        }
        
    }
    
    @Override
    public boolean movimentoJogador(char choice, Tabuleiro tab){
        
        switch(choice){
          
            case 'w':
            if ((tab.tracosPorta.equals(tab.matrizPrint[indiceInicialEmcima[0]][indiceInicialEmcima[1]]) && (indiceInicialEmcima[0] != 0))) {
                
                indiceInicialEmcima[0] -= 2;    // colocando o jogador no setorPrint atual dele
                indiceInicialEsquerda[0] -= 2; // colocando o jogador no setorPrint atual dele
                indiceInicialDireita[0] -= 2; // colocando o jogador no setorPrint atual dele
                indiceInicialEmBaixo[0] -= 2;// colocando o jogador no setorPrint atual dele
                
                i = (indiceInicialEsquerda[0] - 1)/2; // correspondente na matriz 5x5
                return true;
                
            }else{ 
                System.out.println("----NÃO É UMA PORTA----");
                  return false;
            }

            case 's':
                if (tab.tracosPorta.equals(tab.matrizPrint[indiceInicialEmBaixo[0]][indiceInicialEmBaixo[1]]) && indiceInicialEmBaixo[0] <= 9) {

                    indiceInicialEmcima[0] += 2;
                    indiceInicialEsquerda[0] += 2; // colocando o jogador no setorPrint atual dele
                    indiceInicialDireita[0] += 2;
                    indiceInicialEmBaixo[0] += 2;
                    i = (indiceInicialEsquerda[0] + 1) / 2 - 1;
                    return true;
                    
                }else{
                   System.out.println("----NÃO É UMA PORTA----");
                   return false;
                }
          

            case 'd':
                if (tab.porta.equals(tab.matrizPrint[indiceInicialDireita[0]][indiceInicialDireita[1]]) || "* X  ".equals(tab.matrizPrint[indiceInicialDireita[0]][indiceInicialDireita[1]]) && indiceInicialDireita[1] <= 4) {

                    indiceInicialEmcima[1] += 1;
                    indiceInicialEsquerda[1] += 1;
                    indiceInicialDireita[1] += 1; // colocando o jogador no setorPrint atual dele
                    indiceInicialEmBaixo[1] += 1;
                    j = indiceInicialEmBaixo[1];
                    return true;
                    

                }else{
                    System.out.println("----NÃO É UMA PORTA----");
                    return false;
                }
               

            case 'a':
            if (tab.porta.equals(tab.matrizPrint[indiceInicialEsquerda[0]][indiceInicialEsquerda[1]]) && indiceInicialEsquerda[1] > 0) {

                
                indiceInicialEmcima[1]-= 1;
                indiceInicialEsquerda[1]-= 1;
                indiceInicialDireita[1]-= 1;
                indiceInicialEmBaixo[1]-= 1;// colocando o jogador no setorPrint atual dele
                j = indiceInicialEmBaixo[1];
                return true;
     
            }else{  
                System.out.println("----NÃO É UMA PORTA----");
                return false;
            }
            
            
            
            default: 
                System.out.println("");
                return false;
        }
  
    }

}
