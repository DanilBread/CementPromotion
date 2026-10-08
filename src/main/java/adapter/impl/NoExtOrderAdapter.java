package adapter.impl;

public final class NoExtOrderAdapter extends OrderReadAndSplit {

    @Override
    public String getDelimiter() {
        return "#";
    }
}

