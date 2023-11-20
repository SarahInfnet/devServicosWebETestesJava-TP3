package br.com.Infnet.jogo;

import java.util.Scanner;
import java.io.*;
import java.time.LocalDate;
import java.util.Random;
import java.io.IOException;

import br.com.Infnet.Exception.PersonagemNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

  private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

  public static void limparTela() {
    System.out.print("\033\143");
  }

  public static void jogarPartida() {
    File tempFolder = new File("./temp");
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

    LOGGER.info("Iniciando uma nova partida");

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

    LOGGER.info("Nickname do jogador: " + nickname);

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
        LOGGER.info("Herói escolhido: " + heroi.getHeroi());
        break;
      } catch (PersonagemNotFoundException e)  {
        LOGGER.error("Personagem não encontrado. Escolha um dos personagens da tabela.");
      }
    }

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
    LOGGER.info("Lutando contra: " + monstroAleatorio);

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

      LOGGER.debug("Iniciativa do herói: " + iniciativaHeroi);
      LOGGER.debug("Iniciativa do monstro: " + iniciativaMonstro);

      if(iniciativaHeroi > iniciativaMonstro){
        LOGGER.info(heroi.getHeroi() + " Atacou!");
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
          LOGGER.info("O monstro recebeu um dano de " + dano);
        } else {
          LOGGER.info("O monstro defendeu e não sofreu nenhum dano");
        }
      }else{
        LOGGER.info(monstro.getMonstro() + " Atacou!");
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
          LOGGER.info("O herói recebeu um dano de " + dano);
        } else {
          LOGGER.info("O herói defendeu e não sofreu nenhum dano");
        }
      }
      LOGGER.info("Vida do herói: " + heroi.getPontosVida());
      LOGGER.info("Vida do monstro: " + monstro.getPontosVida());

      quantidadeRodadas++;

      System.out.println("Aperte ENTER para continuar");
      scanner.nextLine();
      limparTela();
    }

    venceu = heroi.getPontosVida() == 0 ? false : true;

    if(!venceu){
      LOGGER.info("Você foi derrotado!");
      System.out.println("▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄");
      System.out.println("████▌▄▌▄▐▐▌█████");
      System.out.println("████▌▄▌▄▐▐▌▀████");
      System.out.println("▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
    } else{
      LOGGER.info("Você venceu!");
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


    String nomeArquivo = "./temp/" + nicknameJogador + ".csv";
    LOGGER.debug("Registrando resultado da partida no arquivo: " + nomeArquivo);

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

      LOGGER.debug("Resultado da partida registrado com sucesso");

    } catch (IOException e) {
      LOGGER.error("Erro ao registrar os resultados da partida", e);
      e.printStackTrace();
    }
  }

}