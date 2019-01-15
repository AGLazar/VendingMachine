package Model;

public class Product {
    private String name;
    private Integer pret;
    private Integer cod;
    private String size;

    public Product(String name, Integer pret, Integer cod, String size) {
        this.name = name;
        this.pret = pret;
        this.cod = cod;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return pret;
    }

    public void setPret(Integer pret) {
        this.pret = pret;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
