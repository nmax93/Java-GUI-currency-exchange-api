package Exchange_App;

public interface Exchange_Interface {

    void parse();   // the parsing function retrieving data from the web and creating a local file with the content.
                    // updating the document the program working with, and updating the date displayed in the GUI.

    void convert();    // conversion function between the two chosen currencies and displays the result in the GUI

    String search_currency_value(String cur);    // searching currency value (ILS) by name pram

    void Add_Buttons_Action_Listeners();     // adding each button in the GUI an action listener
                                             // *currency buttons pass the name of the currency to a string var
                                             // *conversion button start a conversion routine
}