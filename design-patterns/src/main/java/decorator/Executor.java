package decorator;

public class Executor {
    public static void main(String[] args) {
        Player player = new VoleyBallPlayer("ali");
        player = new FootBallPESPlayer(player, "laliga", "vali");
        player.play();
        // this add football functionality to voleibalist
    }
}
