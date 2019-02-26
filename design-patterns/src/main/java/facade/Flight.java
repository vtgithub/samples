package facade;

public class Flight {
    private String name;

    public Flight() {
    }

    public Flight(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "name='" + name + '\'' +
                '}';
    }
}
