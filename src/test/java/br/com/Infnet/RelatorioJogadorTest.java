package br.com.Infnet;

import br.com.Infnet.jogo.Main;
import br.com.Infnet.relatorio.RelatorioJogador;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RelatorioJogadorTest {
    @Test
    @DisplayName("Verifica se o herói mais jogado corresponde com o herói mais frequente no arquivo de registro de partidas")
    public void heroiMaisJogado(){
        Main.registraPartida("17/11/2023","Guerreiro","GANHOU","Morto-vivo",8, "Leonardo");
        Main.registraPartida("17/11/2023","Paladino","GANHOU","Morto-vivo",8, "Leonardo");
        Main.registraPartida("17/11/2023","Guerreiro","GANHOU","Morto-vivo",8, "Leonardo");
        try {
            RelatorioJogador relatorioJogador = new RelatorioJogador("Leonardo");
            assertTrue(relatorioJogador.heroiMaisJogado().equalsIgnoreCase("Guerreiro"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
