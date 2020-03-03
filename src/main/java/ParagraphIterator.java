import com.sun.istack.internal.NotNull;

import java.util.*;

public class ParagraphIterator implements Iterator<List<String>> {

    private final String paragraphStart;
    private final String paragraphEnd;
    private final Iterator<String> cursor;
    private boolean hasNext = true;
    private boolean cursorSet = false;


    public ParagraphIterator(@NotNull Iterable<String> collection, @NotNull String paragraphStart, @NotNull String paragraphEnd) {
        if (collection == null || paragraphStart == null || paragraphEnd == null) {
            throw new IllegalArgumentException();
        }
        this.paragraphStart = paragraphStart;
        this.paragraphEnd = paragraphEnd;
        this.cursor = collection.iterator();
    }

    public ParagraphIterator(@NotNull Iterator<String> iterator, @NotNull String paragraphStart, @NotNull String paragraphEnd) {
        if (iterator == null || paragraphStart == null || paragraphEnd == null) {
            throw new IllegalArgumentException();
        }
        this.paragraphStart = paragraphStart;
        this.paragraphEnd = paragraphEnd;
        this.cursor = iterator;
    }


    public boolean hasNext() {
        if (cursorSet) {
            return hasNext;
        }

        cursorSet = true;

        while (cursor.hasNext()) {
            if (isParagraphStart(cursor.next())) {
                return hasNext = true;
            }
        }

        return hasNext = false;
    }

    public List<String> next() {
        if (hasNext()) {
            cursorSet = false;

            final List<String> paragraph = new ArrayList<>();
            while (cursor.hasNext()) {
                final String line = cursor.next();
                if (isParagraphEnd(line)) {
                    break;
                }
                paragraph.add(line);
            }
            return paragraph;
        }

        throw new NoSuchElementException();
    }

    private boolean isParagraphStart (String line) {
        return paragraphStart.equals(line);
    }
    private boolean isParagraphEnd (String line) {
        return paragraphEnd.equals(line);
    }
}
