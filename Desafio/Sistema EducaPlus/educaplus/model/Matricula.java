package educaplus.model;

import java.time.LocalDate;
import educaplus.enums.StatusMatricula;

public class Matricula {
    private final Aluno aluno;
    private final Curso curso;  
    private LocalDate data;
    private StatusMatricula status = StatusMatricula.EM_ANDAMENTO;
    private Certificado certificado;

    public Matricula(Aluno aluno, Curso curso, LocalDate data) {
        this.aluno = aluno;
        this.curso = curso;
        this.data = data;
    }
    public void concluir(int notaCurso, int notaInstrutor, LocalDate dataConclusao) {
        if (status != StatusMatricula.EM_ANDAMENTO)
            throw new IllegalStateException("A matrícula não está em andamento.");
        status = StatusMatricula.CONCLUIDA;
        curso.receberAvaliacao(notaCurso);             
        curso.getInstrutor().receberAvaliacao(notaInstrutor); 
        certificado = new Certificado ("Certificado de conclusão do curso: " + curso.getTitulo() + " para o aluno: " + aluno.getNome() + " na data: " + dataConclusao);
    }
    public void cancelar() {
        if (status != StatusMatricula.EM_ANDAMENTO)
            throw new IllegalStateException("A matrícula não está em andamento.");
        status = StatusMatricula.CANCELADA;
    }

    public Aluno getAluno() {
        return aluno;
    }
    public Curso getCurso() {
        return curso;
    }
    public LocalDate getData() {
        return data;
    }
    public StatusMatricula getStatus() {
        return status;
    }
    public Certificado getCertificado() {
        return certificado;
    }
    @Override
    public String toString() {
        return "Matricula{" +
                "aluno=" + aluno.getNome() +
                ", curso=" + curso.getTitulo() +
                ", data=" + data +
                ", status=" + status +
                ", certificado=" + (certificado != null ? certificado.getConteudo() : "null") +
                '}';
    }
}
