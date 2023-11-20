package br.com.Infnet.Exception;

public class PersonagemNotFoundException extends RuntimeException {
    public PersonagemNotFoundException(String message) {
        super(message);
    }
}