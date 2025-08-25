package educaplus.model;

public class Certificado {
    private final String conteudo;

    public Certificado(String conteudo) {
        this.conteudo = conteudo;
    }
    public String getConteudo() {
        return conteudo;
    }
    @Override
    public String toString() {
        return "Certificado{" +
                "conteudo='" + conteudo + '\'' +
                '}';
    }
}
