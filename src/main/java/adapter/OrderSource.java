package adapter;

import model.Order;
import java.util.List;

public interface OrderSource {
    List<Order> read(String fileName);
}
