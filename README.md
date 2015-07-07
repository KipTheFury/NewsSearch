# NewsSearch
HSCIC News Search
Created by Kyle Bennett

### Requirements
Java 1.7 or higher

### Building from source

Open cmd/terminal

Navigate to the root NewsSearch folder and run "mvn clean install"

The tests will run and the program will be packaged into a jar file in "NewsSearch/target"

### Running the Program

Open cmd/terminal

Navigate to the folder containing the NewsSearch-1.0.0.jar file

Run the program with "java -jar NewsSearch-1.0.0.jar"

Enter the full or relative path of the file you wish to search - You will not be able to continue without a valid file.
Enter the search terms separated by a space
Enter the Search Type, "AND" or "OR"

The lines in the file that meet the search criteria will be displayed.

Press enter to perform another search or x to exit

### External Libraries used in Development / Testing 

* JUnit
* Hamcrest
* Fest-Assert

### Future Improvements

* Boolean flag for "Ignore Case" when searching.
* Search by date - Before or after 
* Better logging, replace System.out.println
* Simple Swing GUI - File Dialog, SearchString and Operand input and then results screen.
* Web Application - search files from online sources
* Filename validation to only accept .txt files, check its not a directory etc
* Negative search operands e.g. Return Lines NOT containing X or Y 
