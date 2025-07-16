package model;


public class BeanVariety {
    private String id;
    private String name;

    public BeanVariety() {
        super();
    }

    public BeanVariety(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BeanVariety{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}


