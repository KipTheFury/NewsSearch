package com.kb.newssearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Kyle Bennett
 *
 */
public class NewsSearch {

    private List<String> allNewsLines;
    private List<String> searchTerms;
    private Set<String>  filteredNewslines;

    /**
     * The different types of search available. Could be extended in the future.
     *
     * AND - Line matches if it contains all of the search terms
     *
     * OR - Headline Line if it contains any of the search terms
     */
    public enum SearchType {
        AND, OR;
    }

    /**
     * Performs a search on the provided file and returns a set of news lines
     * that match the search criteria.
     *
     * @param file
     *            - The file to search.
     * @param searchString
     *            - The search terms, seperated by a space.
     * @param searchType
     *            - The type of search to perform.
     *
     * @return - A Set containing the lines that match the search criteria.
     */
    public Set<String> search(final File file, final String searchString, final SearchType searchType) {

        // Seperate the search terms

        final String[] searchTermsArray = searchString.split(" ");
        searchTerms = Arrays.asList(searchTermsArray);

        System.out.println(String.format("Searching for News Lines containing %s in [%s] file", searchTerms, file));

        // Read the news line file into a list of the lines in the file

        filteredNewslines = new HashSet<String>();
        allNewsLines = readFile(file);

        // perform the search

        switch (searchType) {

            case AND:
                performAndSearch();
                break;

            case OR:
                performOrSearch();
                break;

            default:
                System.out.println("Unknown Search Type");
                break;
        }

        System.out.println(String.format("Search found [%d] matching lines", filteredNewslines.size()));

        return filteredNewslines;
    }

    /**
     * Perform an AND search.
     *
     * The News Line matches if it contains ALL the search terms.
     */
    private void performAndSearch() {

        for (final String newsLine : allNewsLines) {

            boolean containsAllSearchTerms = true;

            for (final String searchTerm : searchTerms) {
                if (!newsLine.contains(searchTerm)) {
                    containsAllSearchTerms = false;
                }
            }
            if (containsAllSearchTerms) {
                filteredNewslines.add(newsLine);
            }
        }
    }

    /**
     * Perform an OR search.
     *
     * The News Line matches if it contains ANY of the search terms.
     */
    private void performOrSearch() {

        for (final String newsLine : allNewsLines) {
            for (final String searchTerm : searchTerms) {
                if (newsLine.contains(searchTerm)) {
                    filteredNewslines.add(newsLine);
                    break;
                }
            }
        }
    }

    /**
     * Read the lines in a text file into memory, returns them as a List<String>
     *
     * @param file
     *            - The path of the file to read.
     *
     * @return - A List containing the lines in the file.
     */
    protected List<String> readFile(final File file) {

        final List<String> lines = new ArrayList<String>();

        if (file != null) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {

                String line;
                while ((line = br.readLine()) != null) {

                    lines.add(line);
                }

                System.out.println(String.format("Finished loading [%d] News Lines from [%s]", lines.size(), file));
            }
            catch (final IOException e) {

                System.out.println(String.format("Error when Reading file [%s] - %s", file, e.getMessage()));
            }
        }
        else {
            System.out.println("No file selected");
        }

        return lines;
    }
}
