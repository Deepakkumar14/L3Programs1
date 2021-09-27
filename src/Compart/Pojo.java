package Compart;

public class Pojo {
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   private int number;
    private String name;

    @Override
    public String toString() {
        return "Pojo{" +
                "number=" + number +
                ", name='" + name + '\'' +
                '}';
    }
}
