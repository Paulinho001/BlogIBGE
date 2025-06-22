package blogibge.service;

import blogibge.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class PersistenciaService {
    private static final String ARQUIVO = "usuario.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void salvarUsuario(Usuario usuario) {
        try (FileWriter writer = new FileWriter(ARQUIVO)) {
            gson.toJson(usuario, writer);
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    public static Usuario carregarUsuario() {
        try (FileReader reader = new FileReader(ARQUIVO)) {
            return gson.fromJson(reader, Usuario.class);
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
            return null;
        }
    }
}
