import java.util.ArrayList;
import java.util.HashMap;

public class ChessMain {


    public static void main(String[] args) {

        ChessMain main = new ChessMain();
        ArrayList<Player> players = main.createPlayers();

        for (Player player : players) {
            System.out.println(player);
        }
        HashMap<String, Piece> pieceHashMap = main.createPieces();
        System.out.println(pieceHashMap);

        main.play(players,pieceHashMap);



        // getter
        //System.out.println("The player 1 name is: " + whitePlayer.getName() + ", email: "+ whitePlayer.getEmail() + ", rank is: " + whitePlayer.getRank() + ", age: " + whitePlayer.getAge());
        //System.out.println("The player 2 name is: " + blackPlayer.getName() + ", email: "+ blackPlayer.getEmail() + ", rank is: " + blackPlayer.getRank() + ", age: " + blackPlayer.getAge());




//        try {
//            whitePlayer.setRank(2100);
//
//        }
//        catch (IllegalArgumentException err) {
//            System.out.println("please update rank to valid");
//        }
        //System.out.println(whitePlayer.getRank());

        // blackPlayer.setRank(-100);
    }

    public ArrayList<Player> createPlayers()
    {
        Player whitePlayer = new Player("Beth Harmon", "beth.harmon@gmail.com", true, 2000, 20);
        Player blackPlayer = new Player("Vasily Borgov", "vasily.borgov@yandex.ru", false, 2500, 45);

        ArrayList <Player> players = new ArrayList<>();
        players.add(whitePlayer);
        players.add(blackPlayer);

        return players;
    }

    public HashMap<String, Piece> createPieces()
    {
        King whiteKing = new King(new Spot("H",7),"white_king", true);
        King blackKing = new King(new Spot("D",8),"black_king",false);
        Rook whiteRookR = new Rook(new Spot("G", 7),"white_rook_right",true);
        Rook whiteRookL = new Rook(new Spot("A", 7),"white_rook_left",true);
        Knight blackKnightR = new Knight(new Spot("D", 6),"black_knight_right", false);

        HashMap<String, Piece> pieceHashMap = new HashMap<>();
        pieceHashMap.put(whiteKing.getId(), whiteKing);
        pieceHashMap.put(blackKing.getId(), blackKing);
        pieceHashMap.put(whiteRookR.getId(),whiteRookR);
        pieceHashMap.put(whiteRookL.getId(),whiteRookL);
        pieceHashMap.put(blackKnightR.getId(),blackKnightR);

        return pieceHashMap;


    }

    public void play(ArrayList<Player> players, HashMap<String, Piece> hashMap)
    {
        //move 1
        try {
            players.get(0).movePiece(hashMap.get("white_rook_left"), new Spot("A", 8));
            //players.get(0).movePiece(hashMap.get("black_king"), new Spot("A", 8));
        }
        catch(IllegalArgumentException error)
        {
            System.out.println("The move is incorrect, please try again!");
            //retry functionality
        }
        //System.out.println(hashMap.get("white_rook_left"));
        players.get(1).movePiece(hashMap.get("black_knight_right"), new Spot("C",8));
    }
}
