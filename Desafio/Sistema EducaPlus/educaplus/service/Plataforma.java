package educaplus.service;

import educaplus.model.Aluno;
import educaplus.model.Pessoa;
import educaplus.model.Certificado;
import educaplus.model.Curso;
import educaplus.model.Instrutor;
import educaplus.model.Matricula;
import educaplus.model.Transacao;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import educaplus.enums.FormaPagamento;
import educaplus.enums.StatusCurso;






public class Plataforma {
    private final String nome;
    private final List<Aluno> alunos = new ArrayList<>();
    private final List<Instrutor> instrutores = new ArrayList<>();
    private final List<Curso> cursos = new ArrayList<>();
    private final List<Matricula> matriculas = new ArrayList<>();
    private final List<Transacao> transacoes = new ArrayList<>();
    private BigDecimal percentualInstrutor = BigDecimal.ZERO;

    public Plataforma(String nome, BigDecimal percentualInstrutor) {
        if (percentualInstrutor.compareTo(BigDecimal.ZERO) < 0 || percentualInstrutor.compareTo(BigDecimal.ONE) > 0) {
            throw new IllegalArgumentException("Percentual do instrutor deve estar entre 0 e 1");
        }
        this.nome = nome;
        this.percentualInstrutor = percentualInstrutor;
    }
    public void cadastrarAluno(Aluno aluno) {
        alunos.add(aluno);
    }
    public void cadastrarInstrutor(Instrutor instrutor) {
        instrutores.add(instrutor);
    }
    public void cadastrarCurso(Curso curso) {
        cursos.add(curso);
    }
    public Matricula matricular(Aluno aluno, Curso curso, FormaPagamento formaPagamento, LocalDate data) {
       if (curso.getStatus() != StatusCurso.ATIVO) 
           throw new IllegalArgumentException("Curso inativo");
       Matricula m = new Matricula(aluno, curso, data);
       aluno.adicionarMatricula(m);
       curso.incrementarMatriculados();
       matriculas.add(m);


       BigDecimal valor = curso.getValorMatricula();
       BigDecimal repasseInstrutor = valor.multiply(percentualInstrutor);
       BigDecimal receitaPlataforma = valor.subtract(repasseInstrutor);

       curso.getInstrutor().creditarRepasse(repasseInstrutor);
       aluno.adicionarGasto(valor);

        Transacao t = new Transacao(data, valor, formaPagamento, curso, aluno, repasseInstrutor, receitaPlataforma);
        transacoes.add(t);
        return m;
    }
   public Certificado concluirCurso(Matricula m, int notaCurso, int notaInstrutor, LocalDate dataConclusao) {
        m.concluir(notaCurso, notaInstrutor, dataConclusao);
        return m.getCertificado();
    }

    public List<Curso> cursosMaisProcurados(int n) {
        return cursos.stream()
                .sorted(Comparator.comparingInt(Curso::getTotalMatriculados).reversed())
                .limit(n)
                .toList();
    }
    public List<Instrutor> instrutoresMelhoresAvaliados(int n) {
        return instrutores.stream()
                .sorted(Comparator.comparingDouble(Pessoa::getAvaliacaoMedia).reversed())
                .limit(n)
                .toList();
    }
    public BigDecimal arrecadacaoPlataforma(LocalDate inicio, LocalDate fim) {
        return transacoes.stream()
                .filter(t -> !t.getData().isBefore(inicio) && !t.getData().isAfter(fim))
                .map(Transacao::getReceitaPlataforma)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    public Map<Aluno, BigDecimal> gastoMedioPorAluno() {
        Map<Aluno, List<Transacao>> porAluno = transacoes.stream()
                .collect(Collectors.groupingBy(Transacao::getAluno));

        Map<Aluno, BigDecimal> media = new LinkedHashMap<>();
        for (var e : porAluno.entrySet()) {
            BigDecimal total = e.getValue().stream()
                    .map(Transacao::getValorPago)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal qtd = BigDecimal.valueOf(e.getValue().size());
            media.put(e.getKey(), qtd.compareTo(BigDecimal.ZERO) == 0 ?
                    BigDecimal.ZERO : total.divide(qtd, 2, RoundingMode.HALF_UP));
        }
        return media;
    }
     public Map<FormaPagamento, Long> relatorioFormasDePagamento() {
        return transacoes.stream()
                .collect(Collectors.groupingBy(Transacao::getFormaPagamento, LinkedHashMap::new, Collectors.counting()));
    }
     public void imprimirTransacoes() {
        System.out.println("\n=== Transações ===");
        transacoes.forEach(t -> System.out.println(" - " + t));
    }
    public void imprimirTopCursos(int n) {
        System.out.println("\n=== Top " + n + " Cursos por Popularidade ===");
        cursosMaisProcurados(n).forEach(c -> System.out.println(" - " + c));
    }
    public void imprimirTopInstrutores(int n) {
        System.out.println("\n=== Top " + n + " Instrutores por Avaliação ===");
        instrutoresMelhoresAvaliados(n).forEach(i ->
                System.out.printf(" - %s |  %.2f | Recebido: R$ %s%n",
                        i.getNome(), i.getAvaliacaoMedia(), i.getSaldoRecebido().setScale(2, RoundingMode.HALF_UP)));
    }
     public void imprimirGastoMedioPorAluno() {
        System.out.println("\n=== Gasto Médio por Aluno ===");
        gastoMedioPorAluno().forEach((aluno, media) ->
                System.out.printf(" - %s: R$ %s%n", aluno.getNome(), media.setScale(2, RoundingMode.HALF_UP)));
    }
    public void imprimirFormasPagamento() {
        System.out.println("\n Formas de Pagamento");
        relatorioFormasDePagamento().forEach((fp, qtd) ->
                System.out.printf(" - %s: %d%n", fp, qtd));
    }
    @Override
    public String toString() {
        return "Plataforma{" +
                "nome='" + nome + '\'' +
                ", percentualInstrutor=" + percentualInstrutor +
                ", totalAlunos=" + alunos.size() +
                ", totalInstrutores=" + instrutores.size() +
                ", totalCursos=" + cursos.size() +
                ", totalMatriculas=" + matriculas.size() +
                ", totalTransacoes=" + transacoes.size() +
                '}';
    }

}
