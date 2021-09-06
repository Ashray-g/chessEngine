package lichess;


import com.github.bhlangonijr.chesslib.move.Move;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


public class APIControl {

    static String code = Key.key;

    public static String createChallenge(int level) throws IOException {
        URL url = new URL("https://lichess.org/api/challenge/ai");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Authorization", "Bearer " + code);
        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        String data = "color=white&level=" + level;

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
        String outJson = in.readLine();
        in.close();

        System.out.println("i:"+http.getResponseCode());
        http.disconnect();

        Gson gson = new Gson();
        JsonObject js = gson.fromJson(outJson, JsonObject.class);
        System.out.println(js);
        return js.getAsJsonPrimitive("id").getAsString();
    }

    public static String createChallenge(String user, boolean rated) throws IOException {
        URL url = new URL("https://lichess.org/api/challenge/" + user);
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Authorization", "Bearer " + code);
        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        String data = "rated=false&color=white";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
        String outJson = in.readLine();
        in.close();

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();

        Gson gson = new Gson();
        JsonObject js = gson.fromJson(outJson, JsonObject.class);
        System.out.println(js);
        return js.getAsJsonObject("challenge").getAsJsonPrimitive("id").getAsString();
    }

    public static String playMove(Move move, String gameId) throws IOException {
        URL url = new URL("https://lichess.org/api/bot/game/"+gameId+"/move/"  + move.toString());
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Authorization", "Bearer " + code);
        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        http.setRequestProperty("Content-Length", "0");

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());

        BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
        String outJson = in.readLine();
        in.close();

        http.disconnect();

        return outJson;

    }

    public static String gameStream(String gameId) throws IOException {
        String urls = "https://lichess.org/api/bot/game/stream/" + gameId;

        URL url = new URL(urls);

        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestProperty("Authorization", "Bearer " + code);

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());

        BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
        String outJson = in.readLine();
        in.close();

        http.disconnect();
        return outJson;
    }

    public static String update(String gameId) throws IOException {
        String game = gameStream(gameId);
        Gson gson = new Gson();
        JsonObject js = gson.fromJson(game, JsonObject.class);
        return js.getAsJsonObject("state").getAsJsonPrimitive("moves").getAsString();

    }
}
