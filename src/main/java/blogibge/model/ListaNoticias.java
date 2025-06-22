package blogibge.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListaNoticias {
    private List<Noticia> noticias = new ArrayList<>();

    public ListaNoticias() {
    }

    public ListaNoticias(List<Noticia> noticias) {
        this.noticias = noticias;
    }

    public List<Noticia> getNoticias() {
        return noticias;
    }

    public void adicionar(Noticia noticia) {
        noticias.add(noticia);
    }

    public void remover(Noticia noticia) {
        noticias.remove(noticia);
    }

    public void ordenarPorTitulo() {
        noticias.sort(Comparator.comparing(Noticia::getTitulo, String.CASE_INSENSITIVE_ORDER));
    }

    public void ordenarPorData() {
        noticias.sort(Comparator.comparing(Noticia::getDataPublicacao).reversed());
    }

    public void ordenarPorTipo() {
        noticias.sort(Comparator.comparing(Noticia::getTipo, String.CASE_INSENSITIVE_ORDER));
    }

    public List<Noticia> buscarPorTituloOuPalavra(String palavra) {
        List<Noticia> resultado = new ArrayList<>();
        for (Noticia n : noticias) {
            if (n.getTitulo().toLowerCase().contains(palavra.toLowerCase()) ||
                    n.toString().toLowerCase().contains(palavra.toLowerCase())) {
                resultado.add(n);
            }
        }
        return resultado;
    }

    public List<Noticia> buscarPorData(String data) {
        List<Noticia> resultado = new ArrayList<>();
        for (Noticia n : noticias) {
            if (n.getDataPublicacao().equalsIgnoreCase(data)) {
                resultado.add(n);
            }
        }
        return resultado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Noticia n : noticias) {
            sb.append(n).append("\n");
        }
        return sb.toString();
    }
}
