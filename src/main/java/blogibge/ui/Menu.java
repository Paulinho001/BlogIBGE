package blogibge.ui;

import blogibge.model.Noticia;
import blogibge.model.Usuario;
import blogibge.repository.DadosRepository;
import blogibge.service.IbgeNoticiasService;
import blogibge.service.UsuarioService;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private static IbgeNoticiasService noticiasService = new IbgeNoticiasService();
    private static Usuario usuario;
    private static DadosRepository dados;
    private static List<Noticia> noticias;

    public static void exibirMenu() {
        Scanner scanner = new Scanner(System.in);

        usuario = UsuarioService.carregarUsuario();
        if (usuario == null) {
            System.out.print("Bem-vindo! Digite seu nome ou apelido: ");
            String nome = scanner.nextLine();
            usuario = new Usuario(nome);
            UsuarioService.salvarUsuario(usuario);
        }
        dados = new DadosRepository(usuario);

        System.out.println("\n🔄 Carregando notícias do IBGE...");
        noticias = noticiasService.buscarNoticias();

        int opcao;
        do {
            System.out.println("\n=== Blog IBGE - Usuário: " + usuario.getNome() + " ===");
            System.out.println("1 - Buscar notícias");
            System.out.println("2 - Gerenciar favoritos");
            System.out.println("3 - Marcar notícia como lida");
            System.out.println("4 - Gerenciar 'Para ler depois'");
            System.out.println("5 - Ver listas");
            System.out.println("6 - Ordenar listas");
            System.out.println("7 - Salvar e sair");
            System.out.print("Escolha uma opção: ");
            opcao = lerNumero(scanner);

            switch (opcao) {
                case 1:
                    buscarNoticias(scanner);
                    break;
                case 2:
//                    gerenciarFavoritos(scanner);
                    break;
                case 3:
//                    marcarComoLida(scanner);
                    break;
                case 4:
//                    gerenciarParaLerDepois(scanner);
                    break;
                case 5:
//                    exibirListas(scanner);
                    break;
                case 6:
//                    ordenarListas(scanner);
                    break;
                case 7:
                    dados.salvarDados();
                    System.out.println("Dados salvos. Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 7);

        scanner.close();
    }

    private static void listarNoticias() {
        System.out.println("\n=== Lista de Notícias ===");
        for (int i = 0; i < noticias.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + noticias.get(i).getTitulo());
        }
    }

    private static int lerNumero(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void buscarNoticias(Scanner scanner) {

    }
//
//    private static void gerenciarFavoritos(Scanner scanner) {
//
//    }
//
//    private static void marcarComoLida(Scanner scanner) {
//
//    }
//
//    private static void gerenciarParaLerDepois(Scanner scanner) {
//
//        }
//    }
//
//    private static void exibirListas(Scanner scanner) {
//
//    }
//
//    private static void ordenarListas(Scanner scanner) {
//
//    }

}
