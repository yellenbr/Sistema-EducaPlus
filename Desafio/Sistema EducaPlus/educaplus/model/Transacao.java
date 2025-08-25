package educaplus.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import educaplus.enums.FormaPagamento;


public class Transacao {
    private final UUID id = UUID.randomUUID();
    private final LocalDate data;
    private final BigDecimal valorPago;
    private final FormaPagamento formaPagamento;
    private final Curso curso;
    private final Aluno aluno;
    private final BigDecimal repasseInstrutor;
    private BigDecimal receitaPlataforma;

    public Transacao (LocalDate data, BigDecimal valorPago, FormaPagamento formaPagamento, Curso curso, Aluno aluno, BigDecimal repasseInstrutor, BigDecimal receitaPlataforma) {
        this.data = data;
        this.valorPago = valorPago;
        this.formaPagamento = formaPagamento;
        this.curso = curso;
        this.aluno = aluno;
        this.repasseInstrutor = repasseInstrutor;
        this.receitaPlataforma = receitaPlataforma; 
    }
    public LocalDate getData() {
        return data;
    }
    public BigDecimal getValorPago() {
        return valorPago;
    }
    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }
    public Curso getCurso() {
        return curso;
    }
    public Aluno getAluno() {
        return aluno;
    }
    public BigDecimal getRepasseInstrutor() {
        return repasseInstrutor;
    }
    public BigDecimal getReceitaPlataforma() {
        return receitaPlataforma;
    }
    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", data=" + data +
                ", valorPago=" + valorPago +
                ", formaPagamento=" + formaPagamento +
                ", curso=" + curso.getTitulo() +
                ", aluno=" + aluno.getNome() +
                ", repasseInstrutor=" + repasseInstrutor +
                ", receitaPlataforma=" + receitaPlataforma +
                '}';
    }
}
