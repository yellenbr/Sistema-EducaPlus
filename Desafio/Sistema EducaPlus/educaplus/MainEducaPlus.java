package educaplus;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import educaplus.model.Aluno;
import educaplus.model.Certificado;
import educaplus.model.Curso;
import educaplus.model.Instrutor;
import educaplus.model.Matricula;
import educaplus.service.Plataforma;
import educaplus.enums.FormaPagamento;
import educaplus.enums.NivelDificuldade;
import educaplus.enums.StatusCurso;


public class MainEducaPlus {
     public static void main(String[] args) {
        Plataforma plataforma = new Plataforma("EducaPlus", new BigDecimal("0.70")); // 70% instrutor, 30% plataforma

        
        Instrutor i1 = new Instrutor("Carla Silva", "carla@ex.com", "Desenvolvedora e instrutora Java.");
        Instrutor i2 = new Instrutor("Rafael Souza", "rafael@ex.com", "Data Scientist e professor de ML.");
        plataforma.cadastrarInstrutor(i1);
        plataforma.cadastrarInstrutor(i2);

        Aluno a1 = new Aluno("Marina Lopes", "marina@ex.com");
        Aluno a2 = new Aluno("Pedro Henrique", "pedro@ex.com");
        Aluno a3 = new Aluno("Lia Costa", "lia@ex.com");
        plataforma.cadastrarAluno(a1);
        plataforma.cadastrarAluno(a2);
        plataforma.cadastrarAluno(a3);

    
        Curso c1 = new Curso("Java do Zero", "Fundamentos de Java e POO.", i1, NivelDificuldade.BASICO, new BigDecimal("199.90"));
        Curso c2 = new Curso("Java Avançado", "Streams, Concurrency, Spring.", i1, NivelDificuldade.AVANCADO, new BigDecimal("349.90"));
        Curso c3 = new Curso("Introdução a ML", "Conceitos e prática com Python.", i2, NivelDificuldade.INTERMEDIARIO, new BigDecimal("299.90"));
        plataforma.cadastrarCurso(c1);
        plataforma.cadastrarCurso(c2);
        plataforma.cadastrarCurso(c3);


        c2.setStatus(StatusCurso.INATIVO);

        
        LocalDate hoje = LocalDate.now();
        Matricula m1 = plataforma.matricular(a1, c1, FormaPagamento.CARTAO, hoje);
        Matricula m2 = plataforma.matricular(a2, c1, FormaPagamento.PIX, hoje);
        Matricula m3 = plataforma.matricular(a3, c3, FormaPagamento.BOLETO, hoje);

    
        Certificado cert1 = plataforma.concluirCurso(m1, 5, 5, hoje.plusDays(15));
        Certificado cert2 = plataforma.concluirCurso(m2, 4, 5, hoje.plusDays(20));
        Certificado cert3 = plataforma.concluirCurso(m3, 5, 4, hoje.plusDays(25));
        System.out.println("\n=== Certificados Emitidos ===");
        System.out.println(cert1);
        System.out.println();
        System.out.println(cert2);
        System.out.println();
        System.out.println(cert3);
        System.out.println();

        plataforma.imprimirTransacoes();
        plataforma.imprimirTopCursos(3);
        plataforma.imprimirTopInstrutores(3);

        BigDecimal receitaMes = plataforma.arrecadacaoPlataforma(hoje.minusDays(1), hoje.plusDays(30));
        System.out.println("\n=== Receita da Plataforma (período) ===");
        System.out.println("R$ " + receitaMes.setScale(2, RoundingMode.HALF_UP));

        plataforma.imprimirGastoMedioPorAluno();
        plataforma.imprimirFormasPagamento();
    }
}
