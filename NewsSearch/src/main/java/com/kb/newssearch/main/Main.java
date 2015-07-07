package com.kb.newssearch.main;

import java.io.File;
import java.util.Scanner;
import java.util.Set;

import com.kb.newssearch.NewsSearch;
import com.kb.newssearch.NewsSearch.SearchType;

public class Main {

    private static NewsSearch newsSearch;

    /**
     * Main method
     *
     * Gets the Headline file, the Search Terms and the Search Type from the
     * User.
     *
     * Performs the search with the provided information and displays the
     * results.
     *
     * @param args
     */
    public static void main(final String[] args) {

        newsSearch = new NewsSearch();
        final Scanner scan = new Scanner(System.in);

        String input = null;
        try {
            do {

                // Make sure the news file exists

                File file = null;
                do {
                    System.out.println("Enter the file name / path to the file:");
                    final String filename = scan.nextLine();

                    file = new File(filename);

                    if (!file.exists()) {
                        System.out.println("That file cannot be loaded");
                        file = null;
                    }
                }
                while (file == null);

                // Read the search terms

                System.out.println("Enter the list of words you want to search for, seperated by spaces:");
                final String searchString = scan.nextLine();

                SearchType searchType = null;

                // select the Search Type

                do {
                    System.out.println("Enter the Search Type (AND or OR) :");

                    try {
                        searchType = SearchType.valueOf(scan.nextLine().toUpperCase());
                    }
                    catch (final IllegalArgumentException e) {
                        System.out.println("That Search Type is Invalid");
                    }
                }
                while (searchType == null);

                // Perform the search

                final Set<String> searchResults = newsSearch.search(file, searchString, searchType);

                // Display the results

                if (searchResults.size() > 0) {

                    System.out.println("\n----- Results -----\n");

                    for (final String line : searchResults) {
                        System.out.println("\t" + line + "\n");

                    }
                }
                else {
                    System.out.println("No Matching Lines");
                }

                // Perform another search or exit

                System.out.println("Press Enter to continue or type \"x\" to exit");
                input = scan.nextLine();
            }
            while (!input.equals("x"));
        }
        finally {
            scan.close();
        }

    }
}
