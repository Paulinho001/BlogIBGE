package blogibge.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private List<Noticia> favoritos = new ArrayList<>();
    private List<Noticia> paraLerDepois = new ArrayList<>();
    private List<Noticia> lidas = new ArrayList<>();

    public Usuario(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public List<Noticia> getFavoritos() {
        return favoritos;
    }

    public List<Noticia> getParaLerDepois() {
        return paraLerDepois;
    }

    public List<Noticia> getLidas() {
        return lidas;
    }
}
