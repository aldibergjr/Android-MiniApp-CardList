package test.berg.cardlist;

/**
 * Created by Berg on 24/09/2017.
 */
//Classe
public class Exercicios {
    private final String Nomeatividade;
    private final String minutos;
    public Exercicios(String nomeatividade,String minutos)
    {
        this.Nomeatividade = nomeatividade;
        this.minutos = minutos;
    }
    public String getNomeatividade()
    {
        return Nomeatividade;
    }
    public String getMinutos(){
        return minutos;
    }

}
