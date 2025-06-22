package blogibge.ui;

import blogibge.model.Noticia;
import blogibge.model.Usuario;
import blogibge.repository.DadosRepository;
import blogibge.service.IBGENoticiasService;
import blogibge.service.PersistenciaService;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private static IBGENoticiasService noticiasService = new IBGENoticiasService();
    private static Usuario usuario;
    private static DadosRepository dados;
    private static List<Noticia> noticias;

    public static void exibirMenu() {
        Scanner scanner = new Scanner(System.in);

        usuario = PersistenciaService.carregarUsuario();
        if (usuario == null) {
            System.out.print("Bem-vindo! Digite seu nome ou apelido: ");
            String nome = scanner.nextLine();
            usuario = new Usuario(nome);
            PersistenciaService.salvarUsuario(usuario);
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
                    gerenciarFavoritos(scanner);
                    break;
                case 3:
                    marcarComoLida(scanner);
                    break;
                case 4:
                    gerenciarParaLerDepois(scanner);
                    break;
                case 5:
                    exibirListas(scanner);
                    break;
                case 6:
                    ordenarListas(scanner);
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
        System.out.println("\n=== Buscar Notícias ===");
        System.out.println("1 - Por título");
        System.out.println("2 - Por palavra-chave");
        System.out.println("3 - Por data (AAAA-MM-DD)");
        System.out.print("Escolha: ");
        int escolha = lerNumero(scanner);

        System.out.print("Digite o termo de busca: ");
        String termo = scanner.nextLine().toLowerCase();

        List<Noticia> resultado = noticias.stream().filter(n -> {
            switch (escolha) {
                case 1:
                    return n.getTitulo().toLowerCase().contains(termo);
                case 2:
                    return n.getIntroducao().toLowerCase().contains(termo) ||
                            n.getTitulo().toLowerCase().contains(termo);
                case 3:
                    return n.getDataPublicacao() != null && n.getDataPublicacao().startsWith(termo);
                default:
                    return false;
            }
        }).toList();

        if (resultado.isEmpty()) {
            System.out.println("Nenhuma notícia encontrada.");
        } else {
            resultado.forEach(System.out::println);
        }
    }

    private static void gerenciarFavoritos(Scanner scanner) {
        System.out.println("\n=== Gerenciar Favoritos ===");
        listarNoticias();

        System.out.print("Digite o número da notícia para [1] Adicionar ou [2] Remover dos favoritos: ");
        int escolha = lerNumero(scanner);

        if (escolha <= 0 || escolha > noticias.size()) {
            System.out.println("Escolha inválida.");
            return;
        }

        Noticia noticia = noticias.get(escolha - 1);

        System.out.println("[1] Adicionar ou [2] Remover?");
        int acao = lerNumero(scanner);

        if (acao == 1) {
            dados.adicionarFavorito(noticia);
            System.out.println("Adicionado aos favoritos.");
        } else if (acao == 2) {
            dados.removerFavorito(noticia);
            System.out.println("Removido dos favoritos.");
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private static void marcarComoLida(Scanner scanner) {
        System.out.println("\n=== Marcar como Lida ===");
        listarNoticias();

        System.out.print("Digite o número da notícia que deseja marcar como lida: ");
        int escolha = lerNumero(scanner);

        if (escolha <= 0 || escolha > noticias.size()) {
            System.out.println("Escolha inválida.");
            return;
        }

        Noticia noticia = noticias.get(escolha - 1);
        dados.marcarComoLida(noticia);
        System.out.println("Notícia marcada como lida.");
    }

    private static void gerenciarParaLerDepois(Scanner scanner) {
        System.out.println("\n=== Gerenciar 'Para Ler Depois' ===");
        listarNoticias();

        System.out.print("Digite o número da notícia para [1] Adicionar ou [2] Remover da lista: ");
        int escolha = lerNumero(scanner);

        if (escolha <= 0 || escolha > noticias.size()) {
            System.out.println("Escolha inválida.");
            return;
        }

        Noticia noticia = noticias.get(escolha - 1);

        System.out.println("[1] Adicionar ou [2] Remover?");
        int acao = lerNumero(scanner);

        if (acao == 1) {
            dados.adicionarParaLerDepois(noticia);
            System.out.println("Adicionado na lista 'Para ler depois'.");
        } else if (acao == 2) {
            dados.removerParaLerDepois(noticia);
            System.out.println("Removido da lista.");
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private static void exibirListas(Scanner scanner) {
        System.out.println("\n=== Suas Listas ===");
        System.out.println("1 - Favoritos");
        System.out.println("2 - Lidas");
        System.out.println("3 - Para ler depois");
        System.out.print("Escolha: ");
        int escolha = lerNumero(scanner);

        List<Noticia> listaSelecionada = switch (escolha) {
            case 1 -> usuario.getFavoritos();
            case 2 -> usuario.getLidas();
            case 3 -> usuario.getParaLerDepois();
            default -> null;
        };

        if (listaSelecionada == null) {
            System.out.println("Opção inválida.");
            return;
        }

        if (listaSelecionada.isEmpty()) {
            System.out.println("Lista vazia.");
        } else {
            listaSelecionada.forEach(System.out::println);
        }
    }

    private static void ordenarListas(Scanner scanner) {
        System.out.println("\n=== Ordenar Listas ===");
        System.out.println("1 - Favoritos");
        System.out.println("2 - Lidas");
        System.out.println("3 - Para ler depois");
        System.out.print("Escolha a lista: ");
        int listaEscolhida = lerNumero(scanner);

        List<Noticia> lista = switch (listaEscolhida) {
            case 1 -> usuario.getFavoritos();
            case 2 -> usuario.getLidas();
            case 3 -> usuario.getParaLerDepois();
            default -> null;
        };

        if (lista == null) {
            System.out.println("Opção inválida.");
            return;
        }

        if (lista.isEmpty()) {
            System.out.println("Lista vazia.");
            return;
        }

        System.out.println("Ordenar por:");
        System.out.println("1 - Título");
        System.out.println("2 - Data");
        System.out.println("3 - Tipo");
        System.out.print("Escolha: ");
        int ordem = lerNumero(scanner);

        switch (ordem) {
            case 1 -> lista.sort((a, b) -> a.getTitulo().compareToIgnoreCase(b.getTitulo()));
            case 2 -> lista.sort((a, b) -> b.getDataPublicacao().compareToIgnoreCase(a.getDataPublicacao()));
            case 3 -> lista.sort((a, b) -> a.getTipo().compareToIgnoreCase(b.getTipo()));
            default -> {
                System.out.println("Opção inválida.");
                return;
            }
        }

        System.out.println("Lista ordenada:");
        lista.forEach(System.out::println);
    }

}
