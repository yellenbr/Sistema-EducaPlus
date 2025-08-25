package educaplus.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

public class Aluno extends Pessoa{
    private final List<Matricula> matriculas = new ArrayList<>();
    private BigDecimal gastoTotal = BigDecimal.ZERO;

    public Aluno(String nome, String email) {
        super(nome, email);
    }
    public void adicionarMatricula(Matricula m) {
        matriculas.add(m);
    }
    public void adicionarGasto(BigDecimal valor) {
        gastoTotal = gastoTotal.add(valor);
    }

    public List<Matricula> getMatriculas() {
        return List.copyOf(matriculas);
    }
    public BigDecimal getGastoTotal() {
        return gastoTotal;
    }
    
}
