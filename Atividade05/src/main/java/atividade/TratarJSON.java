package atividade;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class TratarJSON {

    public static void main(String[] args) throws UnirestException {
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o nome do jogo: ");
        String jogo = scan.nextLine();
        String parametro = jogo.replace(" ", "+");

        HttpResponse<JsonNode> response = Unirest.get("https://videogamesrating.p.mashape.com/get.php?game=" + parametro)
                .header("X-Mashape-Key", "FC2RAZxpaDmshpSVSiZe0Yt6Rcugp1ehKItjsnhRhGAtBYzsMQ")
                .header("Accept", "application/json")
                .asJson();

        JSONArray games = response.getBody().getArray();

        String s = "";
        for (int i = 0; i < games.length(); i++) {
            JSONObject game = games.getJSONObject(i);

            String title = game.getString("title");
            String publisher = game.getString("publisher");
            String score = game.getString("score");
            String description = game.getString("short_description");

            s = s + (String.format("Nome: %s \n "
                    + "Distribuídora: %s \n "
                    + "Nota: %s \n "
                    + "Descrição curta: %s \n \n",
                    title, publisher, score, description));

        }

        System.out.println(response.getBody());
        System.out.println(s);

    }

}
