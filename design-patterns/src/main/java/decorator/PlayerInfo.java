package decorator;

public class PlayerInfo {
    private String name;
    private int score;
    private byte level;

    public PlayerInfo(String name) {
        this.name = name;
    }

    public PlayerInfo(String name, int score, byte level) {
        this.name = name;
        this.score = score;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public byte getLevel() {
        return level;
    }

    public void setLevel(byte level) {
        this.level = level;
    }
}
