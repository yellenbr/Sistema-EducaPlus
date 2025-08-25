# Sistema-EducaPlus

## 1. Visão Geral

O **EducaPlus** é um sistema desenvolvido em Java que simula uma plataforma de cursos online, projetado para gerenciar a interação entre alunos, instrutores e cursos. A plataforma permite a criação de conteúdo educacional, matrículas, processamento de transações financeiras e a geração de relatórios para análise de desempenho.

## 2. Funcionalidades Principais

O sistema oferece um conjunto robusto de funcionalidades para criar um ecossistema completo de ensino a distância:

* **Gestão de Usuários:**
    * Cadastro e gerenciamento de **Alunos** e **Instrutores**.
    * Sistema de avaliação mútua.

* **Gestão de Cursos:**
    * Criação de cursos com título, descrição, nível de dificuldade e valor.
    * Associação de um instrutor responsável por cada curso.
    * Controle de status do curso (`Ativo` ou `Inativo`).

* **Jornada do Aluno:**
    * Matrícula em cursos disponíveis e ativos.
    * Acompanhamento do progresso e conclusão de cursos.
    * Emissão de certificados de conclusão personalizados.
    * Possibilidade de avaliar os cursos e instrutores.

* **Transações Financeiras:**
    * Registro de todas as matrículas como transações financeiras.
    * Detalhes da transação, incluindo valor, forma de pagamento e curso adquirido.
    * Sistema de repasse de comissão para o instrutor a cada matrícula.

* **Relatórios e Análises:**
    * Identificação dos cursos mais populares (por número de matrículas).
    * Ranking de instrutores com as melhores avaliações.
    * Relatório de arrecadação da plataforma por período.
    * Cálculo do gasto médio por aluno.
    * Análise das formas de pagamento mais utilizadas.

## 3. Modelo de Domínio

O sistema é estruturado em torno de três pilares principais: **Pessoas**, **Cursos** e **Transações**.

### Pessoas

Existem dois perfis de usuários na plataforma:

* **Instrutor:** Responsável pela criação e gestão do conteúdo dos cursos. Recebe avaliações de seus alunos e uma parte do valor das matrículas.
* **Aluno:** Consome o conteúdo da plataforma. Pode se matricular, concluir cursos, receber certificados e avaliar sua experiência.

### Cursos

Cada curso é uma entidade única com os seguintes atributos:

* Título e descrição.
* Instrutor responsável.
* Nível de dificuldade.
* Valor de matrícula.
* Situação (`Ativo`/`Inativo`).
* Avaliações dos alunos.

### Transações

Toda matrícula gera um registro financeiro que armazena:

* O valor pago pelo aluno.
* A forma de pagamento.
* O curso adquirido.

## 4. Regras de Negócio

Para garantir a consistência e o bom funcionamento da plataforma, as seguintes regras foram implementadas:

1.  **Exclusividade de Instrutor:** Cada curso deve estar associado a um, e somente um, instrutor.
2.  **Matrículas em Cursos Ativos:** Um aluno só pode se inscrever em cursos que estejam com o status "Ativo".
3.  **Conteúdo do Certificado:** O certificado emitido após a conclusão deve conter, obrigatoriamente, o nome do aluno e o título do curso.
4.  **Divisão de Receita:** O valor pago em uma matrícula é dividido entre o instrutor do curso e a plataforma, conforme regras pré-definidas.
