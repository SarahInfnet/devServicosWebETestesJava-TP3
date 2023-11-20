package br.com.Infnet.jogo;

import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.util.Random;

 public abstract class Personagem{
  private int pontosVida;
  private int forca;
  private int defesa;
  private int agilidade;
  private int[] fatorDano;

  public Personagem(int pontosVida, int forca, int defesa, int agilidade, int[] fatorDano){
    this.pontosVida = pontosVida;
    this.forca = forca;
    this.defesa = defesa;
    this.agilidade = agilidade;
    this.fatorDano = fatorDano;
  }
   
  public void setPontosVida(int pontosVida){
    this.pontosVida = pontosVida;
  }

  public int getPontosVida(){
    return pontosVida;
  }

   public void setForca(int forca){
    this.forca = forca;
  }

  public int getForca(){
    return forca;
  }

   public void setDefesa(int defesa){
    this.defesa = defesa;
  }

  public int getDefesa(){
    return defesa;
  }

  public void setAgilidade(int agilidade){
    this.agilidade = agilidade;
  }

  public int getAgilidade(){
    return agilidade;
  }

   public void setFatorDano(int[] fatorDano){
    this.fatorDano = fatorDano;
  }

  public int[] getFatorDano(){
    return fatorDano;
  }

   public int calculaIniciativa(){
     Random aleatorio = new Random();
     int numeroAleatorio = aleatorio.nextInt(10) + 1; 
     return numeroAleatorio + agilidade;
   }

   public int calculaFatorAtaque(){
     Random aleatorio = new Random();
     int numeroAleatorio = aleatorio.nextInt(10) + 1; 
     return numeroAleatorio + agilidade + forca;
   }

   public int calculaFatorDefesa(){
     Random aleatorio = new Random();
     int numeroAleatorio = aleatorio.nextInt(10) + 1; 
     return numeroAleatorio + agilidade + defesa;
   }

   public int calculaFatorDano() {
     Random aleatorio = new Random();
     int dano = 0;
     for (int i = 0; i < fatorDano[0]; i++) {
       dano += aleatorio.nextInt(fatorDano[1]) + 1; 
     }
     dano += forca;
     return dano;
   }

   public void sofrerDano(int dano) {
     pontosVida -= dano;
     if (pontosVida < 0) {
       pontosVida = 0;
     }
   }
}