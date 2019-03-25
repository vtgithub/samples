package decorator;

public class FootBallPESPlayer extends FootBalPlayerDecorator {
    private PlayerInfo playerInfo;

    private String leagueName;

    public FootBallPESPlayer(Player player, String leagueName, String playerName) {
        super(player);
        this.leagueName = leagueName;
        this.playerInfo = new PlayerInfo(playerName, 0, (byte) 1);
    }

    public void play() {
        decoratedPlayer.play();
        System.out.println("I am playing in league __"+leagueName+"__ my updateScore is __"+ getScore() );
    }

    public PlayerInfo getPlayerInfo() {
        return this.playerInfo ;
    }

    public void updateScore() {
        this.playerInfo.setScore(this.playerInfo.getScore() + 1);
    }

    public int getScore() {
        return this.playerInfo.getScore();
    }

    public void updateLevel() {
        this.playerInfo.setLevel((byte) (this.playerInfo.getLevel()+1));
    }
}
