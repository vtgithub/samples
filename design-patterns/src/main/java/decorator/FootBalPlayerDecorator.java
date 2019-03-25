package decorator;

public abstract class FootBalPlayerDecorator implements Player{
    private Player player;
//    abstract void setProfesionality(int prof);

    public FootBalPlayerDecorator(Player player) {
        super();
        this.player = player;
    }


}
