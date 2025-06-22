package blogibge.repository;

import blogibge.model.Usuario;
import blogibge.service.UsuarioService;

public class DadosRepository {
    private Usuario usuario;

    public DadosRepository(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void salvarDados() {
        UsuarioService.salvarUsuario(usuario);
    }

    public void adicionarFavorito(blogibge.model.Noticia noticia) {
        if (!usuario.getFavoritos().contains(noticia)) {
            usuario.getFavoritos().add(noticia);
        }
    }

    public void removerFavorito(blogibge.model.Noticia noticia) {
        usuario.getFavoritos().remove(noticia);
    }

    public void adicionarParaLerDepois(blogibge.model.Noticia noticia) {
        if (!usuario.getParaLerDepois().contains(noticia)) {
            usuario.getParaLerDepois().add(noticia);
        }
    }

    public void removerParaLerDepois(blogibge.model.Noticia noticia) {
        usuario.getParaLerDepois().remove(noticia);
    }

    public void marcarComoLida(blogibge.model.Noticia noticia) {
        if (!usuario.getLidas().contains(noticia)) {
            usuario.getLidas().add(noticia);
        }
    }
}
