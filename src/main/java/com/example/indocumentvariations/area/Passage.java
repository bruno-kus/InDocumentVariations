package com.example.indocumentvariations.area;





public interface Passage extends Comparable<Passage> {
    int getPosition();
    String getText();

    void setPosition(int position);
    void setText(String text);
    default MyRange toRange() {
        int start = getPosition();
        int end = start + getText().length();
        return new MyRange(start, end);
    }
    default void update(Passage passage) {
        setPosition(passage.getPosition());
        setText(passage.getText());
    }
    @Override
    default int compareTo(Passage o) {
        return Integer.compare(getPosition(), o.getPosition());
    }
}
