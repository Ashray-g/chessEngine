package lichess;

import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException, InterruptedException {
        String game = APIControl.createChallenge("AshrayTest", false);

        System.out.println("Id: " + game);

        APIControl.gameStream(game);
    }
}
