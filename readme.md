# Last OOPE Task
Program for reading and filtering text documents. Made as coursework for the course OOPE2 at Tampereen yliopisto.
### Structure
The program is divided into six packages. Apulaiset is an interface by Jorma Laurikkala and contains declarations for methods that were implemented to spec in the other packages. Dokumentit contains the single text document object handler, kokoelma contains the methods for accessing those documents, omalista contains an extension to Java's own linked list class that allows for placing elements so that they have their correct place in the list, shell contains the methods for the commands the user can issue, and ui contains the text based interface for issuing those commands.
### Compiling
The program is written in Java, and will compile with ```javac```. Main class can be found in Oope2HT.java.
### Running
The program needs two arguments, a document source and a filterword source. The document source can consist of either documents or jokes. Documents are formated as so: 
```id number///date///text```
whereas jokes are formatted as such:
```id number///category///text```. The filter word source is a list of words separated by newlines.
If these arguments aren't met the program won't run, and will exit with an error.
### To do
Functionality yet to be implemented includes a pretty printer, different ways to sort the articles, a word frequency calculator, and ultimately, a graphical interface.
