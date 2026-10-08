package adapter.impl;

public final class TxtOrderAdapter extends OrderReadAndSplit {

    @Override
    public String getDelimiter() {
        return "\\|";
    }
}

