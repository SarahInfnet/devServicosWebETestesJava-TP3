package br.com.Infnet.jogo;

class Monstro extends Personagem {
  private String monstro;

  public Monstro(String monstro, int pontosVida, int forca, int defesa, int agilidade, int[] fatorDano) {
    super(pontosVida, forca, defesa, agilidade, fatorDano);
    this.monstro = monstro;
  }

  public void setMonstro(String monstro){
    this.monstro = monstro;
  }
  
  public String getMonstro() {
        return monstro;
    }
}