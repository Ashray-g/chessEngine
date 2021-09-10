package engine;

import com.github.bhlangonijr.chesslib.File;
import com.github.bhlangonijr.chesslib.game.Game;
import com.github.bhlangonijr.chesslib.move.MoveList;
import com.github.bhlangonijr.chesslib.pgn.PgnHolder;

import java.util.ArrayList;
import java.util.Collections;

public class LoadGames {
    static ArrayList<Game> grandMasterGames = new ArrayList<>();
    public static ArrayList<MoveList> grandMasterGamesMoves = new ArrayList<>();
    public static void load() throws Exception {
        PgnHolder pgn = new PgnHolder("src/main/java/games/Nakamura.pgn");
        PgnHolder pgn2 = new PgnHolder("src/main/java/games/Kasparov.pgn");
        PgnHolder pgn3 = new PgnHolder("src/main/java/games/Modern.pgn");
        PgnHolder pgn4 = new PgnHolder("src/main/java/games/SicilianNajdorf6Be2.pgn");
        PgnHolder pgn5 = new PgnHolder("src/main/java/games/openings.pgn");
        pgn.loadPgn();
        pgn2.loadPgn();
        pgn3.loadPgn();
        pgn4.loadPgn();
        pgn4.loadPgn();
        grandMasterGames.addAll(pgn.getGames());
        grandMasterGames.addAll(pgn2.getGames());
        grandMasterGames.addAll(pgn3.getGames());
        grandMasterGames.addAll(pgn4.getGames());
        grandMasterGames.addAll(pgn5.getGames());
        for(Game g : grandMasterGames){
            grandMasterGamesMoves.add(g.getCurrentMoveList());
        }
        Collections.shuffle(grandMasterGamesMoves);
        System.out.println(grandMasterGamesMoves.size());
    }
}
