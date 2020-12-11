
package antivirusporumdia;

import java.util.Random;


public class Inimigo{
    
    private int ataque;
    private int defesa;
    
    public Inimigo() {}
    
    public Inimigo(int ataque, int defesa) {
        this.ataque = ataque;
        this.defesa = defesa;
    }

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
    
   public Inimigo  criarInimigo(){
        
        Random aleatorio = new Random();
        
        // valor aleatorio de 1 a 3 para atk e defesa
        int atkDef= aleatorio.nextInt(3)+1;
        
         return new Inimigo(atkDef,atkDef );
    } 
    
        
    public static void atacar(Player1 play, Inimigo ini, int jogador){
        Random aleatorio = new Random();
        int rand = aleatorio.nextInt(5)+1;
        
        if(rand%2 == 0){
            System.out.println("Inimigo: " +ini.getAtaque()+ "/" + ini.getAtaque());
            if(ini.getDefesa()>0){
                play.setDefesa(play.getDefesa()-ini.getAtaque());
                System.out.printf("--------------------\n"
                                +"|   O JOGADOR "+jogador+"     |\n"
                                +"|SOFREU "+ ini.getAtaque() +" DE ATAQUE|\n"
                                + "--------------------\n");  
            }
        } 
    }
    
}
