package educaplus.model;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;


public class Pessoa {

    private final UUID id;
    private String nome;   
    private String email;
    private double avaliacaoMedia;
    private int totalNotas;

    public Pessoa(String nome, String email) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.email = email;
        this.avaliacaoMedia = 0.0;
        this.totalNotas = 0;
    }

    public void receberAvaliacao(int nota) {
        if(nota < 1 || nota > 5) {
            throw new IllegalArgumentException("Nota deve ser entre 1 e 5");
        }
        avaliacaoMedia = ((avaliacaoMedia * totalNotas) + nota) / (++totalNotas);
    }
    public UUID getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getEmail() {
        return email;
    }
    public double getAvaliacaoMedia() {
        return BigDecimal.valueOf(avaliacaoMedia).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }


}