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
public class AcceptanceCriteriaTest {

    private static final File TEST_FILE = new File("src/test/resources/hscic-news.txt");

    private NewsSearch        newsLineSearch;
    private List<String>      allLines;

    @Before
    public void setUp() {

        newsLineSearch = new NewsSearch();
        allLines = newsLineSearch.readFile(TEST_FILE);
    }

    /**
     * "Care Quality Commission" - OR - 0,1,2,3,4,5,6
     *
     * @throws Exception
     */
    @Test
    public void ORSearch_CareQualityCommision() throws Exception {

        final Set<String> searchResults = newsLineSearch.search(TEST_FILE, "Care Quality Commission", SearchType.OR);

        assertThat(searchResults).hasSize(7).contains(allLines.get(0), allLines.get(1), allLines.get(2),
                allLines.get(3), allLines.get(4), allLines.get(5), allLines.get(6));
    }

    /**
     * "September 2004" - OR - 9
     *
     * @throws Exception
     */
    @Test
    public void ORSearch_September2004() throws Exception {

        final Set<String> searchResults = newsLineSearch.search(TEST_FILE, "September 2004", SearchType.OR);

        assertThat(searchResults).hasSize(1).contains(allLines.get(9));
    }

    /**
     * "general population generally" - OR - 6,8
     *
     * @throws Exception
     */
    @Test
    public void ORSearch_GeneralPopulationGenerally() throws Exception {

        final Set<String> searchResults = newsLineSearch.search(TEST_FILE, "general population generally",
                SearchType.OR);

        assertThat(searchResults).hasSize(2).contains(allLines.get(6), allLines.get(8));
    }

    /**
     * "Care Quality Commission admission" - AND - 1
     *
     * @throws Exception
     */
    @Test
    public void ANDSearch_CareQualityComissionAdmission() throws Exception {

        final Set<String> searchResults = newsLineSearch.search(TEST_FILE, "Care Quality Commission admission",
                SearchType.AND);

        assertThat(searchResults).hasSize(1).contains(allLines.get(1));
    }

    /**
     * "general population Alzheimer" - AND - 6
     *
     * @throws Exception
     */
    @Test
    public void ANDSearch_GeneralPopulationAlzheimer() throws Exception {

        final Set<String> searchResults = newsLineSearch.search(TEST_FILE, "general population Alzheimer",
                SearchType.AND);

        assertThat(searchResults).hasSize(1).contains(allLines.get(6));
    }
}
