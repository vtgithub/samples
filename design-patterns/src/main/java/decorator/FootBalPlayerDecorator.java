package decorator;

public abstract class FootBalPlayerDecorator implements Player{
    protected Player decoratedPlayer;
//    abstract void setProfesionality(int prof);

    public FootBalPlayerDecorator(Player decoratedPlayer) {
        super();
        this.decoratedPlayer = decoratedPlayer;
    }


}
