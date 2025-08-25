package educaplus.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.math.BigDecimal;



public class Instrutor extends Pessoa {
    private String bio;
    private List<Curso> cursos = new ArrayList<>();
    private BigDecimal saldoRecebido = BigDecimal.ZERO;

    public Instrutor(String nome, String email, String bio) {
        super(nome, email);
        this.bio = bio;
    }
    void adicionarCurso(Curso curso) {
        cursos.add(curso);
    }
    public void creditarRepasse(BigDecimal valor) {
        saldoRecebido = saldoRecebido.add(valor);
    }

    public List<Curso> getCursos() {
        return Collections.unmodifiableList(cursos);
    }
    public BigDecimal getSaldoRecebido() {
        return saldoRecebido;
    }
    @Override
    public String toString() {
        return "Instrutor{" +
                "nome='" + getNome() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", bio='" + bio + '\'' +
                ", saldoRecebido=" + saldoRecebido +
                ", avaliacaoMedia=" + getAvaliacaoMedia() +
                '}';
    }
    
}
