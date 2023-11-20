package br.com.Infnet.jogo;

import br.com.Infnet.Exception.PersonagemNotFoundException;

import java.util.Scanner;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.Random;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Main { 
  public static void limparTela() {
    System.out.print("\033\143");
  }

  public static void jogarPartida() {
    File tempFolder = new File("../temp");
    if (!tempFolder.exists()) {
      tempFolder.mkdirs();
    }

    Scanner scanner = new Scanner(System.in);
    String escolha;
    String nickname;
    Heroi heroi;
    Monstro monstro;
    LocalDate dataPartida = LocalDate.now();
    int quantidadeRodadas = 0;
    boolean venceu;

    limparTela();
    System.out.println();
    System.out.println("SEJA BEM-VINDO AO MEDIEVAL BATTLE!");
    System.out.println();
    System.out.println("─▄▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▄");
    System.out.println("█░░░█░░░░░░░░░░▄▄░██░█");
    System.out.println("█░▀▀█▀▀░▄▀░▄▀░░▀▀░▄▄░█");
    System.out.println("█░░░▀░░░▄▄▄▄▄░░██░▀▀░█");
    System.out.println("─▀▄▄▄▄▄▀─────▀▄▄▄▄▄▄▀");
    System.out.println();

    System.out.println("Aperte ENTER para continuar");
    scanner.nextLine();
    limparTela();
    
    System.out.println("Informe seu nickname:");
    nickname = scanner.nextLine();
    System.out.println();

    limparTela();
    
    while(true){
      System.out.println("╔═════════════════════════════════════════════════════════════════════════╗");    
      System.out.println("║   Herói   ║ Pontos de vida ║ Força ║ Defesa ║ Agilidade ║ Fator de dano ║");    
      System.out.println("║ Guerreiro ║       12       ║   4   ║    3   ║     3     ║      2d4      ║");    
      System.out.println("║  Bárbaro  ║       13       ║   6   ║    1   ║     3     ║      2d6      ║");    
      System.out.println("║  Paladino ║       15       ║   2   ║    5   ║     1     ║      2d4      ║");    
      System.out.println("╚═════════════════════════════════════════════════════════════════════════╝");    
      System.out.println("Digite uma classe de herói ");
      escolha = scanner.nextLine();
      try{
        heroi = PersonagemCriador.getHeroi(escolha);
        System.out.println("Herói escolhido: " + heroi.getHeroi());
        break;
      } catch (PersonagemNotFoundException e)  {
        System.out.println("Personagem não encontrado. Escolha um dos personagens da tabela.");
      }
    }
    System.out.println();

    System.out.println("Aperte ENTER para continuar");
    scanner.nextLine();
    limparTela();
    
    Random aleatorio = new Random();
    String[] monstros = {"Morto-vivo", "Orc", "Kobold"};
    int indiceAleatorio = aleatorio.nextInt(monstros.length);
    String monstroAleatorio = monstros[indiceAleatorio];
    
    if (monstroAleatorio.equals("Morto-vivo")) {
      monstro = new Monstro("Morto-vivo", 25, 4, 0, 1, new int[] {2,4}); 
    }else if(monstroAleatorio.equals("Orc")){
      monstro = new Monstro("Orc", 20, 6, 2, 2, new int[] {1,8});  
    }else{
      monstro = new Monstro("Kobold", 20, 4, 2, 4, new int[] {3,2});  
    }
    System.out.println("Lutando contra: " + monstroAleatorio); 
    System.out.println();

    System.out.println("Aperte ENTER para continuar");
    scanner.nextLine();
    limparTela();

    int iniciativaHeroi;
    int iniciativaMonstro;
    int fatorDeAtaque;
    int fatorDeDefesa;
    while(monstro.getPontosVida() > 0 && heroi.getPontosVida() > 0){
      do{
        iniciativaHeroi = heroi.calculaIniciativa();
        iniciativaMonstro = monstro.calculaIniciativa();
      }while(iniciativaHeroi == iniciativaMonstro);
      
      if(iniciativaHeroi > iniciativaMonstro){
        System.out.println(heroi.getHeroi() + " Atacou!");
        System.out.println();
        System.out.println("(҂`_´)");
        System.out.println("<,︻╦̵̵̿╤─ ҉ ~ •");
        System.out.println("█۞███████]▄▄▄▄▄▄▄▄▄▄▃ ●●●");
        System.out.println("▂▄▅█████████▅▄▃▂…");
        System.out.println("[███████████████████]");
        System.out.println("◥⊙▲⊙▲⊙▲⊙▲⊙▲⊙▲⊙\n");
        
        fatorDeAtaque = heroi.calculaFatorAtaque();
        fatorDeDefesa = monstro.calculaFatorDefesa();
        if (fatorDeAtaque > fatorDeDefesa) {
          int dano = heroi.calculaFatorDano();
          monstro.sofrerDano(dano);
          System.out.println("O monstro recebeu um dano de " + dano);
        } else {
          System.out.println("O monstro defendeu e não sofreu nenhum dano");
        }
      }else{
        System.out.println(monstro.getMonstro() + " Atacou!");
        System.out.println();
        System.out.println("──▄█▀█▄─────────██");
        System.out.println("▄████████▄───▄▀█▄▄▄▄");
        System.out.println("██▀▼▼▼▼▼─▄▀──█▄▄");
        System.out.println("█████▄▲▲▲─▄▄▄▀───▀▄");
        System.out.println("██████▀▀▀▀─▀────────▀▀\n");
        fatorDeAtaque = monstro.calculaFatorAtaque();
        fatorDeDefesa = heroi.calculaFatorDefesa();
        if (fatorDeAtaque > fatorDeDefesa) {
          int dano = monstro.calculaFatorDano();
          heroi.sofrerDano(dano);
          System.out.println("\nO herói recebeu um dano de " + dano);
        } else {
          System.out.println("O herói defendeu e não sofreu nenhum dano");
        }
      }
      System.out.println("Vida do herói: " + heroi.getPontosVida());
      System.out.println("Vida do monstro: " + monstro.getPontosVida());
      System.out.println();

    quantidadeRodadas++;
      
    System.out.println("Aperte ENTER para continuar");
    scanner.nextLine();
    limparTela();
    }

    venceu = heroi.getPontosVida() == 0 ? false : true;
    
    if(!venceu){
      System.out.println("Você foi derrotado!");
      System.out.println("▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄");
      System.out.println("████▌▄▌▄▐▐▌█████");
      System.out.println("████▌▄▌▄▐▐▌▀████");
      System.out.println("▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
    } else{
      System.out.println("Você venceu!");
      System.out.println("─────────────────────────────▄██▄");
      System.out.println("─────────────────────────────▀███");
      System.out.println("───────────────▄▄▄▄▄────────────█");
      System.out.println("──────────────▀▄────▀▄──────────█");
      System.out.println("──────────▄▀▀▀▄─█▄▄▄▄█▄▄─▄▀▀▀▄──█");
      System.out.println("─────────█──▄──█────────█───▄─█─█");
      System.out.println("─────────▀▄───▄▀────────▀▄───▄▀─█");
      System.out.println("──────────█▀▀▀────────────▀▀▀─█─█");
      System.out.println("▄▀▄▄▀▄────█──▄█▀█▀█▀█▀█▀█▄────█─█");
      System.out.println("█▒▒▒▒█────█──█████████████▄───█─█");
      System.out.println("█▒▒▒▒█────█──██████████████▄──█─█");
      System.out.println("█▒▒▒▒█────█───██████████████▄─█─█");
      System.out.println("█▒▒▒▒█────█────██████████████─█─█");
      System.out.println("█▒▒▒▒█────█───██████████████▀─█─█");
      System.out.println("▀████▀──██▀█──█████████████▀──█▄█");
      System.out.println("──██───██──▀█──█▄█▄█▄█▄█▄█▀──▄█▀");
      System.out.println("──██──██────▀█─────────────▄▀▓█");
      System.out.println("──██─██──────▀█▀▄▄▄▄▄▄▄▄▄▀▀▓▓▓█");
      System.out.println("──████────────█▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓█");
      System.out.println("──███─────────█▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓█");
    }

  registraPartida(dataPartida.getDayOfMonth() + "/" + dataPartida.getMonthValue() + "/" + dataPartida.getYear(), heroi.getHeroi(), venceu ? "GANHOU" : "PERDEU", monstro.getMonstro(), quantidadeRodadas, nickname);
  }


  public static void registraPartida(String dataPartida, String heroi, String venceu, String monstro, int quantidadeRodadas) {
    System.out.println(dataPartida + "," + heroi + "," + venceu + "," + monstro + "," + quantidadeRodadas);
  }
  
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    boolean continuarJogando = true;
    while (continuarJogando) {
      jogarPartida();
      System.out.println("Continuar jogando? (sim/nao)");
      if (scanner.nextLine().compareToIgnoreCase("sim") != 0) {
        continuarJogando = false;
      } 
    }
  }

  public static void registraPartida(String dataPartida, String heroi, String venceu, String monstro, int quantidadeRodadas, String nicknameJogador) {
        String nomeArquivo = "../temp/" + nicknameJogador + ".csv";

        try {
            File arquivo = new File(nomeArquivo);
            boolean arquivoExiste = arquivo.exists();

            BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo, true));

            if (!arquivoExiste) {
                writer.write("Data da partida;Herói escolhido;Resultado;Monstro enfrentado;Quantidade de Rodadas");
                writer.newLine();
            }

            String linha = dataPartida + ";" + heroi + ";" + venceu + ";" + monstro + ";" + quantidadeRodadas;
            writer.write(linha);
            writer.newLine();

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}