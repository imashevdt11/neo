import java.util.List;

public class Orders {
    private int id;
    private int customerId;
    private List<OrderProduct> orderProducts;

    public Orders(int id, int customerId, List<OrderProduct> orderProducts) {
        this.id = id;
        this.customerId = customerId;
        this.orderProducts = orderProducts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
}