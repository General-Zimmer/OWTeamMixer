package me.gzimmer.owteammixer.util;

import me.gzimmer.owteammixer.model.Player;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

public class UtilCommon {

    public static Player getOnlinePlayer(String urlPath, String bnet) {
        Player player = new Player(bnet);
        try {
            URL url = new URL(urlPath);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            JSONObject data_obj = getJsonObject(conn, url);

            if (!data_obj.getString("privacy").equalsIgnoreCase("public")) {
                return player;
            }

            JSONObject comp = data_obj.getJSONObject("competitive");
            JSONObject pc = comp.getJSONObject("pc");

            setPlayerRank(pc, "tank", player);
            setPlayerRank(pc, "damage", player);
            setPlayerRank(pc, "support", player);
        } catch (IOException e) {
            System.out.println("Error: \n" + Arrays.toString(e.getStackTrace()));
        }
        return player;
    }

    private static void setPlayerRank(JSONObject platform, String role, Player player) {
        JSONObject rank;
        try {
            rank = platform.getJSONObject(role);
        } catch (Exception e) {
            return;
        }
        player.setRank(role, rank.getString("division"), rank.getInt("tier"));
    }

    @NotNull
    private static JSONObject getJsonObject(HttpURLConnection conn, URL url) throws IOException {
        int responsecode = conn.getResponseCode();
        if (responsecode != 200)
            throw new RuntimeException("HttpResponseCode: " + responsecode);

        StringBuilder inline = new StringBuilder();
        Scanner scanner = new Scanner(url.openStream());

        //Write all the JSON data into a string using a scanner
        while (scanner.hasNext()) {
            inline.append(scanner.nextLine());
        }

        //Close the scanner
        scanner.close();

        //Using the JSON simple library parse the string into a json object
        return new JSONObject(inline.toString());
    }
}
