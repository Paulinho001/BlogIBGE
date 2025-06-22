package blogibge.service;

import blogibge.model.Noticia;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class IbgeNoticiasService {
    public List<Noticia> buscarNoticias() {
        List<Noticia> lista = new ArrayList<>();

        try {
            URL url = new URL("https://servicodados.ibge.gov.br/api/v3/noticias/");
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");
            conexao.connect();

            if (conexao.getResponseCode() != 200) {
                System.out.println("Erro na conexão com a API. Código: " + conexao.getResponseCode());
                return lista;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            StringBuilder resposta = new StringBuilder();
            String linha;

            while ((linha = reader.readLine()) != null) {
                resposta.append(linha);
            }

            reader.close();

            JsonObject json = JsonParser.parseString(resposta.toString()).getAsJsonObject();
            JsonArray itens = json.getAsJsonArray("items");

            itens.forEach(item -> {
                JsonObject obj = item.getAsJsonObject();
                String titulo = obj.get("titulo").getAsString();
                String introducao = obj.get("introducao").getAsString();
                String link = obj.get("link").getAsString();
                String dataPublicacao = obj.get("data_publicacao").getAsString();
                String tipo = obj.has("tipo") ? obj.get("tipo").getAsString() : "Não informado";

                lista.add(new Noticia(titulo, introducao, link, dataPublicacao, tipo));
            });

        } catch (Exception e) {
            System.out.println("Erro ao buscar notícias: " + e.getMessage());
        }

        return lista;
    }
}
