package educaplus.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import educaplus.enums.NivelDificuldade;
import educaplus.enums.StatusCurso;
import java.util.UUID;

public class Curso {
    private final UUID id = UUID.randomUUID();
    private String titulo;
    private String descricao;
    private Instrutor instrutor;
    private NivelDificuldade nivel;
    private BigDecimal valorMatricula;
    private StatusCurso status = StatusCurso.ATIVO;

    private double avaliacaoMedia;
    private int totalNotas;
    private int totalMatriculados;

    public Curso(String titulo, String descricao, Instrutor instrutor, NivelDificuldade nivel, BigDecimal valorMatricula) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.instrutor = instrutor;
        this.nivel = nivel;
        this.valorMatricula = valorMatricula;
        this.status = StatusCurso.ATIVO;
        instrutor.adicionarCurso(this);
    }
    public void receberAvaliacao(int nota) {
        if(nota < 1 || nota > 5) {
            throw new IllegalArgumentException("Nota deve ser entre 1 e 5");
        }
        avaliacaoMedia = ((avaliacaoMedia * totalNotas) + nota) / (++totalNotas);
    }
    public void setStatus(StatusCurso status) {
        this.status = status;
    }

    public void incrementarMatriculados() {
        this.totalMatriculados++;
    }
    public UUID getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getDescricao() {
        return descricao;
    }
    public Instrutor getInstrutor() {
        return instrutor;
    }
    public NivelDificuldade getNivel() {
        return nivel;
    }
    public BigDecimal getValorMatricula() {
        return valorMatricula;
    }
    public StatusCurso getStatus() {
        return status;
    }
    public double getAvaliacaoMedia() {
        return BigDecimal.valueOf(avaliacaoMedia).setScale(2, RoundingMode.HALF_UP).doubleValue()
;
    }
    public int getTotalMatriculados() {
        return totalMatriculados;
    }
    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", instrutor=" + instrutor.getNome() +
                ", nivel=" + nivel +
                ", valorMatricula=" + valorMatricula +
                ", status=" + status +
                ", avaliacaoMedia=" + getAvaliacaoMedia() +
                ", totalMatriculados=" + totalMatriculados +
                '}';
    }
}
