package br.com.Infnet.relatorio;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o nickname do jogador:");
        String nickname = scanner.nextLine();

        try {
            RelatorioJogador relatorioJogador = new RelatorioJogador(nickname);
            System.out.println("Estatísticas do jogador " + nickname);
            System.out.println("Herói mais jogado: " + relatorioJogador.heroiMaisJogado());
            System.out.println("Monstro mais enfrentado: " + relatorioJogador.monstroMaisEnfrentado());
            System.out.println("Quantidade total de pontos: " + relatorioJogador.quantidadePontos());
        } catch (FileNotFoundException e) {
            System.out.println("Usuário não encontrado!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
