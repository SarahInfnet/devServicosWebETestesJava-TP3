package br.com.Infnet.jogo;

import br.com.Infnet.Exception.PersonagemNotFoundException;

public class PersonagemCriador {
    public static Heroi getHeroi(String heroi) {
        if (heroi.equalsIgnoreCase("Guerreiro")) {
            return new Heroi("Guerreiro", 12, 4, 3, 3, new int[]{2,4});
        }
        if (heroi.equalsIgnoreCase("Barbaro") || heroi.equalsIgnoreCase("Bárbaro")) {
            return new Heroi("Bárbaro", 13, 6, 1, 3, new int[]{2,6});
        }
        if (heroi.equalsIgnoreCase("Paladino")) {
            return new Heroi("Paladino", 15, 2, 5, 1, new int[]{2,4});
        }
        throw new PersonagemNotFoundException("Personagem não encontrado");
    }
}
