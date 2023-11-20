package br.com.Infnet.relatorio;

public class RelatorioPartida {
    private String dataPartida;
    private String heroi;
    private String resultado;
    private String monstro;
    private int quantidadeRodadas;

    public RelatorioPartida(String dataPartida, String heroi, String resultado, String monstro, int quantidadeRodadas){
        this.dataPartida = dataPartida;
        this.heroi = heroi;
        this.resultado = resultado;
        this.monstro = monstro;
        this.quantidadeRodadas = quantidadeRodadas;
    }

    public void setDataPartida(String dataPartida){
        this.dataPartida = dataPartida;
    }

    public String getDataPartida(){
        return dataPartida;
    }
    
    public void setHeroi(String heroi){
        this.heroi = heroi;
    }

    public String getHeroi(){
        return heroi;
    }
    
    public void setResultado(String resultado){
        this.resultado = resultado;
    }

    public String getResultado(){
        return resultado;
    }
    
    public void setMonstro(String monstro){
        this.monstro = monstro;
    }

    public String getMonstro(){
        return monstro;
    }
    
    public void setQuantidadeRodadas(int quantidadeRodadas){
        this.quantidadeRodadas = quantidadeRodadas;
    }

    public int getQuantidadeRodadas(){
        return quantidadeRodadas;
    }
}