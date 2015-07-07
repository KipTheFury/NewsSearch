package com.kb.newssearch;

import static org.fest.assertions.Assertions.assertThat;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.kb.newssearch.NewsSearch.SearchType;

/**
 * @author Kyle Bennett
 *
 */
public class NewsSearchTest {

    private static final File TEST_FILE = new File("src/test/resources/TestFile.txt");

    private NewsSearch        newsLineSearch;

    @Before
    public void setUp() {

        newsLineSearch = new NewsSearch();
    }

    @Test
    public void shouldReadLinesFromATextFileIntoAList() throws Exception {

        final List<String> lines = newsLineSearch.readFile(TEST_FILE);

        assertThat(lines).hasSize(4);
        assertThat(lines).containsOnly("The First Headline", "The Second Headline", "The Final Headline",
                "Other Nonsense");
    }

    @Test
    public void shouldReturnAnEmptyListWhenTheFilenameIsNull() throws Exception {

        final List<String> lines = newsLineSearch.readFile(null);

        assertThat(lines).isEmpty();
    }

    @Test
    public void shouldReturnAnEmptyListWhenAnIOExceptionIsThrown() throws Exception {

        final List<String> lines = newsLineSearch.readFile(new File("OtherTestFile.txt"));

        assertThat(lines).isEmpty();
    }

    @Test
    public void shouldReturnLinesContainingSearchPhrase_SinglePhrase() throws Exception {

        final Set<String> filteredLines = newsLineSearch.search(TEST_FILE, "First", SearchType.AND);

        assertThat(filteredLines).hasSize(1);
        assertThat(filteredLines).containsOnly("The First Headline");
    }

    @Test
    public void shouldReturnLinesContainingSearchPhrase_AND() throws Exception {

        final Set<String> filteredLines = newsLineSearch.search(TEST_FILE, "Second Headline", SearchType.AND);

        assertThat(filteredLines).hasSize(1);
        assertThat(filteredLines).containsOnly("The Second Headline");
    }

    @Test
    public void shouldReturnLinesContainingSearchPhrase_OR() throws Exception {

        final Set<String> filteredLines = newsLineSearch.search(TEST_FILE, "Final Second", SearchType.OR);

        assertThat(filteredLines).hasSize(2);
        assertThat(filteredLines).containsOnly("The Second Headline", "The Final Headline");
    }

}
