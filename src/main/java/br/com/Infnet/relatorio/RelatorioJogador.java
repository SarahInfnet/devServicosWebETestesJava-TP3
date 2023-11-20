package br.com.Infnet.relatorio;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.LinkedList;
import java.io.FileReader;  
import java.io.BufferedReader;  
import java.io.FileNotFoundException;
import java.io.IOException;

public class RelatorioJogador  {
    private LinkedList<RelatorioPartida> relatorioJogador = new LinkedList<RelatorioPartida>();

    public RelatorioJogador(String nickname) throws FileNotFoundException, IOException {
        FileReader leitor = new FileReader("./temp/" + nickname + ".csv");
        BufferedReader arquivo = new BufferedReader(leitor);

        arquivo.readLine();
        String[] campos = null;
        String linha;

        do {
            linha = arquivo.readLine();
            if (linha != null) {
                campos = linha.split(";");
                relatorioJogador.add(new RelatorioPartida(campos[0], campos[1], campos[2], campos[3], Integer.parseInt(campos[4])));
            }
        } while (linha != null);
    }
    
    public LinkedList<RelatorioPartida> getRelatorioJogador() {
        return relatorioJogador;
    }

    public void setRelatorioJogador(LinkedList<RelatorioPartida> relatorioJogador) {
        this.relatorioJogador = relatorioJogador;
    }

    public String heroiMaisJogado() {
        Map<String, Long> contagem = relatorioJogador.stream().collect(Collectors.groupingBy(RelatorioPartida::getHeroi, Collectors.counting()));
        Map.Entry<String, Long> heroiMaisJogado = contagem.entrySet().stream().reduce(null, (heroiMax, heroiAtual) -> heroiMax == null ? heroiAtual : (heroiAtual.getValue() > heroiMax.getValue() ? heroiAtual : heroiMax));
        return heroiMaisJogado.getKey();
    }

    public String monstroMaisEnfrentado() {
        Map<String, Long> contagem = relatorioJogador.stream().collect(Collectors.groupingBy(RelatorioPartida::getMonstro, Collectors.counting()));
        Map.Entry<String, Long> monstroMaisEnfrentado = contagem.entrySet().stream().reduce(null, (monstroMax, monstroAtual) -> monstroMax == null ? monstroAtual : (monstroAtual.getValue() > monstroMax.getValue() ? monstroAtual : monstroMax));
        return monstroMaisEnfrentado.getKey();
    }

    public int quantidadePontos() {
        int pontos = 0;
        for(RelatorioPartida partida : relatorioJogador){
            pontos += 100 - partida.getQuantidadeRodadas();
        }
        return pontos;
    }
}
