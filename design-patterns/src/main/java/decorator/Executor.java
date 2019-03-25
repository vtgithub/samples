package decorator;

public class Executor {
    public static void main(String[] args) {
        Player player = new VoleyBallPlayer("ali");
        player.play();
        player = new FootBallPESPlayer(player, "laliga", "vali");
        player.play();
    }
}
