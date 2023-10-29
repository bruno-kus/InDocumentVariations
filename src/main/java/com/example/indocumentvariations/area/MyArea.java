package com.example.indocumentvariations.area;

import javafx.event.ActionEvent;
import org.fxmisc.richtext.*;
import com.example.indocumentvariations.area.MySegmentOps;
import org.fxmisc.richtext.model.*;
import org.reactfx.util.Either;

import java.util.List;
import java.util.Optional;

public class MyArea extends GenericStyledArea<Void, Either<String, MySegment>, Void> {

    // muszę mieć przynajmniej jeden typ tekstowy
    // ale mógłbym go teoretycznie zupełnie zminimalizować
    // lub w ogóle nigdy nim nie pisać


    // !
    // funkcja create jest używana do mapowania tekstu i musi! coś zwrócić
    // dlatego wystarczy, że jeden z fabryk będzie
    // pytanie kto ją wywołuje
    // bo fabryka nie musi

    /*
    pytanie brzmi czy na pewno chce mieć Either?
    wydawałoby się, że tak, bo wtedy by było mniej pamięci zużywane
     */
    private static final TextOps<String, Void> STYLED_TEXT_OPS = SegmentOps.styledTextOps();
    private static final MySegmentOps<Void> MY_OPS = new MySegmentOps<>();
    private static final TextOps<Either<String, MySegment>, Void> EITHER_OPS = STYLED_TEXT_OPS._or(MY_OPS, (e1, e2) -> Optional.empty());

    //    private static final TextOps<MySegment, Void> ops = OPS;
    /*
    czy wszystkie wyświetlane elementy włącznie z obrazkami muszę traktować jako text?
     */
    public MyArea() {
        super(
                null,
                (t, p) -> {
                },
                null,
                EITHER_OPS,
                e -> e.getSegment().unify(
                        TextExt::new,
                        mySegment -> {
                            return new TextExt(mySegment.getCurrentText());
                        })

        );
    }


    public void replaceWithMySegment(int start, int end, Either<String, MySegment> mySegment) {
        super.replace(start, end, mySegment, null);
    }

    public void replaceSelectionWithMySegment() {
        System.out.println("MyArea::replaceSelectionWithMySegment");
        MySegment fromSelection = new MySegment(getText(getSelection()));
        Either<String, MySegment> right = Either.right(fromSelection);
        replaceSelection(ReadOnlyStyledDocument.fromSegment(right, null, null, EITHER_OPS));
    }

    public List<MySegment> getMySegments() {
        return getAllSegments().stream().filter(Either::isRight).map(Either::getRight).toList();
    }

    public List<Either<String, MySegment>> getAllSegments() {
        return getDocument().subSequence(0, getLength()).getParagraphs().stream().
                flatMap(e -> e.getSegments().stream()).
                toList();
    }


    /*
    i teraz teoretycznie bym mógł nadpisać kontrolę tego, co robi back space...
    albo subsekwencję mieć, która podaję wskaźnik do listy następcy
     */
}
