package reactive.java.app.model;

import lombok.Data;

@Data
public class Item {
    private final Integer id;

    public Item(Integer id) {
        this.id = id;
        System.out.println("Item is created:" + this.id + ":" + Thread.currentThread().getName() + "\n");
    }
}
