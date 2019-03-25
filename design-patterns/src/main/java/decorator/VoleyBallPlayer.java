package decorator;

public class VoleyBallPlayer  implements Player{
    private PlayerInfo playerInfo;
    private Integer scoreStep;

    public VoleyBallPlayer(String name) {
        this.scoreStep = 1;
        this.playerInfo = new PlayerInfo(name, 0, (byte) 1);
    }

    public void play() {
        System.out.println("_________playing voleyball ...");
    }

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    public void updateScore() {
        this.playerInfo.setScore(this.playerInfo.getScore() + scoreStep);
    }

    public int getScore() {
        return this.getPlayerInfo().getScore();
    }

    public void updateLevel() {
        this.playerInfo.setLevel((byte) (this.getPlayerInfo().getLevel() + 1));
    }
}
