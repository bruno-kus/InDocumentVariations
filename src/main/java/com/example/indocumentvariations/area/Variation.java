package com.example.indocumentvariations.area;

import java.util.List;
import java.util.SortedSet;



        import java.util.List;
        import java.util.SortedSet;

public interface Variation<P extends Passage> {
    SortedSet<P> sortedSet(); // to może być też z z zasady sorted set!
    // zbiór czy lista?
    default List<Integer> positions() {
        return sortedSet().stream()
                .map(Passage::getPosition)
                .toList();
    }
    default List<MyRange> ranges() {
        return sortedSet().stream()
                .map(Passage::toRange)
                .toList();
    }
    default List<String> strings() {
        return sortedSet()
                .stream()
                .map(Passage::getText)
                .toList();
    }
    default void addPassage(P passage) {
        sortedSet().add(passage);
        // to powinno wykorzystywać polimorfizm,
        // czyli wywołuję na collection ale de facto będzie wywołane add przesłaniające
        // czy może nie i jestem w błędzie?
    }
    // zobaczymy czy za argument nie wolałbym podawać też pasażu, chyba nie!
    default void update(int position, String text) {
        var optional = sortedSet().stream().filter(passage -> passage.getPosition() == position).findFirst();
        optional.ifPresent(passage -> {
            int change = passage.getText().length() - text.length();
            adjustPositions(position, change);
        });
    }
    default void adjustPositions(int from, int change) {
        sortedSet().stream()
                .filter(passage -> passage.getPosition() > from)
                .forEach(passage -> passage.setPosition(passage.getPosition() + change));
    }


    /*
    aby sprawdzać czy mogę dodać i pasaże się nie pokryją
    mógłbym wywoływać ranges oraz sprawdzać czy position do
    żadnego nie należy!
     */
}
