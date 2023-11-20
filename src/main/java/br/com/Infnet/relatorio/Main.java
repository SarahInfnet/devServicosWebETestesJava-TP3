package br.com.Infnet.relatorio;

import java.io.*;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o nickname do jogador:");
        String nickname = scanner.nextLine();

        try {
            RelatorioJogador relatorioJogador = new RelatorioJogador(nickname);
            LOGGER.info("Estatísticas do jogador " + nickname);
            LOGGER.info("Herói mais jogado: " + relatorioJogador.heroiMaisJogado());
            LOGGER.info("Monstro mais enfrentado: " + relatorioJogador.monstroMaisEnfrentado());
            LOGGER.info("Quantidade total de pontos: " + relatorioJogador.quantidadePontos());
        } catch (FileNotFoundException e) {
            LOGGER.error("Usuário não encontrado!");
        } catch (IOException e) {
            LOGGER.error("Erro ao ler o histórico do jogador: " + nickname, e);
            e.printStackTrace();
        }
    }
}
