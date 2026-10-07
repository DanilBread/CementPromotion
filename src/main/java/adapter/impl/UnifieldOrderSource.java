package adapter.impl;

import adapter.OrderSource;
import model.Order;

import java.util.List;

public final class UnifieldOrderSource implements OrderSource {
    private final TxtOrderAdapter txtAdapter = new TxtOrderAdapter();
    private final NoExtOrderAdapter noExtOrderAdapter = new NoExtOrderAdapter();

    @Override
    public List<Order> read(String fileName) {
        if (fileName.endsWith(".txt")) {
            return txtAdapter.read(fileName);
        }
        return noExtOrderAdapter.read(fileName);
    }
}
