import java.util.*;

import static org.junit.Assert.*;

public class ParagraphIteratorTest {

    @org.junit.Test
    public void paragraphIterator() {
        List<String> testList = Arrays.asList(
                "this is string which is not relevant to us",
                "MARKER_START",
                "This text should",
                "appear",
                "in the",
                "result",
                "MARKER_END",
                "MARKER_START",
                "Another part",
                "of valuable",
                "data",
                "MARKER_END",
                "to be ignored",
                "MARKER_START",
                "Something that we",
                "need",
                "to keep",
                "MARKER_END");
        List<String> firstParagraph = Arrays.asList(
                "This text should",
                "appear",
                "in the",
                "result");
        List<String> secondParagraph = Arrays.asList(
                "Another part",
                "of valuable",
                "data");
        List<String> thirdParagraph = Arrays.asList(
                "Something that we",
                "need",
                "to keep");

        ParagraphIterator paragraphIterator = new ParagraphIterator(testList, "MARKER_START", "MARKER_END");
        assertTrue(paragraphIterator.hasNext());
        assertEquals(firstParagraph, paragraphIterator.next());
        assertTrue(paragraphIterator.hasNext());
        assertEquals(secondParagraph, paragraphIterator.next());
        assertTrue(paragraphIterator.hasNext());
        assertEquals(thirdParagraph, paragraphIterator.next());
        assertFalse(paragraphIterator.hasNext());
    }

    @org.junit.Test
    public void hasNextEmpty() {
        ParagraphIterator paragraphIterator = new ParagraphIterator(Collections.emptyList(), "MARKER", "MARKER");
        assertFalse(paragraphIterator.hasNext());
    }

    @org.junit.Test (expected = NoSuchElementException.class)
    public void nextEmpty() {
        ParagraphIterator paragraphIterator = new ParagraphIterator(Collections.emptyList(), "MARKER", "MARKER");
        paragraphIterator.next();
    }

}