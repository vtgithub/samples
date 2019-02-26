package facade;

public class Travel {
    private String name;

    public Travel(String name) {
        this.name = name;
    }

    public Travel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Travel{" +
                "name='" + name + '\'' +
                '}';
    }
}
