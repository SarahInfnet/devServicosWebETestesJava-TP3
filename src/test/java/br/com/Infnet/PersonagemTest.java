package br.com.Infnet;


import br.com.Infnet.Exception.PersonagemNotFoundException;
import br.com.Infnet.jogo.Heroi;
import br.com.Infnet.jogo.PersonagemCriador;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PersonagemTest {
    @Test
    @DisplayName("Deve retornar uma exception para um herói que não existe")
    public void heroiInexistente(){
        assertThrows(PersonagemNotFoundException.class, () ->{
            Heroi heroi = PersonagemCriador.getHeroi("Heroi inexistente");
        });
    }

    @Test
    @DisplayName("A vida mínima de um personagem deve ser zero")
    public void menorVidaPersonagem(){
        Heroi heroi = PersonagemCriador.getHeroi("Paladino");
        heroi.sofrerDano(40);
        assertEquals(0,heroi.getPontosVida());
    }
}


