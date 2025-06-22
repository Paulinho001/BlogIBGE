package blogibge.model;

public class Noticia {
    private String titulo;
    private String introducao;
    private String link;
    private String dataPublicacao;
    private String tipo;
    private String fonte;
    private boolean lida;

    public Noticia(String titulo, String introducao, String link, String dataPublicacao, String tipo) {
        this.titulo = titulo;
        this.introducao = introducao;
        this.link = link;
        this.dataPublicacao = dataPublicacao;
        this.tipo = tipo;
        this.fonte = "IBGE";
        this.lida = false;
    }

    public String getIntroducao() {
        return this.introducao;
    }

    public void marcarComoLida() {
        this.lida = true;
    }

    public boolean isLida() {
        return lida;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDataPublicacao() {
        return dataPublicacao;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "\nTítulo: " + titulo +
                "\nIntrodução: " + introducao +
                "\nData: " + dataPublicacao +
                "\nLink: " + link +
                "\nTipo: " + tipo +
                "\nFonte: " + fonte +
                (lida ? "\nStatus: Lida" : "\nStatus: Não Lida") + "\n";
    }
}
