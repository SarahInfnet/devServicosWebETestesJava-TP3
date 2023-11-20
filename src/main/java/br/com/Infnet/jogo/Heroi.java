package br.com.Infnet.jogo;

class Heroi extends Personagem {
  private String heroi;

  public Heroi(String heroi, int pontosVida, int forca, int defesa, int agilidade, int[] fatorDano) {
    super(pontosVida, forca, defesa, agilidade, fatorDano);
    this.heroi = heroi;
  }

  public void setHeroi(String heroi) {
    this.heroi = heroi;
  }

  public String getHeroi() {
    return heroi;
  }
}