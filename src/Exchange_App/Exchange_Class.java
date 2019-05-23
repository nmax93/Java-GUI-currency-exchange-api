package Exchange_App;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.log4j.Logger;
import org.w3c.dom.*;
import java.net.URL;
import java.lang.Runnable;
import java.awt.event.*;
import java.io.File;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.apache.log4j.BasicConfigurator;

public class Exchange_Class implements Exchange_Interface, ActionListener, Runnable {

    private static Exchange_Class Singleton = null;
    private Document localCurrencies = null;
    private File currenciesFile = null;
    private GUI GUI = null;
    private String currency_name1, currency_name2, currency_value1, currency_value2;
    static Logger logger = Logger.getLogger("Exchange Class");


    public static Exchange_Class instance(){    // instance

        if(Singleton == null) Singleton = new Exchange_Class();
        return Singleton;
    }

    private Exchange_Class(){    // constructor

        BasicConfigurator.configure();
        currenciesFile = new File("currencies.xml");
        parse();
        GUI = GUI.instance();
        Add_Buttons_Action_Listeners();
    }

    public void run () {    // run method responsible to check if the local data file is up to date with online data

        while (true) {   // while loop in case the "try" fails
            try {
                // document builders and connection url
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                URL url = new URL("https://www.boi.org.il/currency.xml");
                Document onlineCurrencies = builder.parse(url.openStream());
                String file_Date, online_Date;
                NodeList nList;
                Element element;

                // display date on GUI
                localCurrencies = builder.parse(currenciesFile);
                nList = localCurrencies.getElementsByTagName("LAST_UPDATE");
                Node nNode = nList.item(0);
                element = (Element) nNode;
                file_Date = element.getTextContent();
                GUI.Display_date(file_Date);

                logger.info("thread running");

                while (true) { //continuous check for compatibility between local and online data

                    // retrieve online data
                    nList = onlineCurrencies.getElementsByTagName("LAST_UPDATE");
                    nNode = nList.item(0);
                    element = (Element) nNode;
                    online_Date = element.getTextContent();

                    // retrieve local data
                    localCurrencies = builder.parse(currenciesFile);
                    nList = localCurrencies.getElementsByTagName("LAST_UPDATE");
                    nNode = nList.item(0);
                    element = (Element) nNode;
                    file_Date = element.getTextContent();

                    // compare and update the local file in case they do not match
                    if(!online_Date.equals(file_Date)){
                        parse();
                        GUI.Display_date(online_Date);
                    }
                    Thread.sleep(5000);
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.info("XML parsing error");
            }
        }
    }

    public void parse(){    // the parsing function retrieving data from the web and creating a local file with the content.
                            // updating the document the program working with, and updating the date displayed in the GUI.
        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        URL url;
        while(true) { // while loop in case try block fails
            try {
                factory = DocumentBuilderFactory.newInstance();
                builder = factory.newDocumentBuilder();
                url = new URL("https://www.boi.org.il/currency.xml");
                localCurrencies = builder.parse(url.openStream());
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                Result output = new StreamResult(currenciesFile);
                Source input = new DOMSource(localCurrencies);
                transformer.transform(input, output);
                localCurrencies = builder.parse(currenciesFile);
                break;
            }
            catch (Exception e) {
                logger.info("XML parsing error");
            }
        }
        logger.info("parsed");
    }

    public void convert(){    // conversion function between the two chosen currencies and displays the result in the GUI

        if(localCurrencies != null)
        {
            if(currency_value1 != null && currency_value2 != null) {
                Double d1 = Double.parseDouble(currency_value1);
                Double d2 = Double.parseDouble(currency_value2);
                Double res = d1 / d2;
                logger.info("conversion result: " + res);
                GUI.Display_Result(res, currency_name1, currency_name2);
            }
        }
    }

    public String search_currency_value(String cur){   // searching currency value (ILS) by name pram

        String val = null;
        NodeList nList = localCurrencies.getElementsByTagName("CURRENCY");

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            Element eElement = (Element) nNode;
            Element cElement = (Element) eElement.getElementsByTagName("CURRENCYCODE").item(0);
            if(cElement.getTextContent().equals(cur)){
                val = eElement.getElementsByTagName("RATE").item(0).getTextContent();
                break;
            }
        }
        return val;
    }

    public void Add_Buttons_Action_Listeners(){    // adding each button in the GUI an action listener
                                                   // *currency buttons pass the name of the currency to a string var
                                                   // *conversion button start a conversion routine

        GUI.USD1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currency_name1 = e.getActionCommand();
                currency_value1 = search_currency_value(currency_name1);
                GUI.Display_from_values(currency_name1, currency_value1);
            }
        });
        GUI.GBP1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currency_name1 = e.getActionCommand();
                currency_value1 = search_currency_value(currency_name1);
                GUI.Display_from_values(currency_name1, currency_value1);
            }
        });
        GUI.JPY1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currency_name1 = e.getActionCommand();
                currency_value1 = search_currency_value(currency_name1);
                GUI.Display_from_values(currency_name1, currency_value1);
            }
        });
        GUI.EUR1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currency_name1 = e.getActionCommand();
                currency_value1 = search_currency_value(currency_name1);
                GUI.Display_from_values(currency_name1, currency_value1);
            }
        });
        GUI.AUD1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currency_name1 = e.getActionCommand();
                currency_value1 = search_currency_value(currency_name1);
                GUI.Display_from_values(currency_name1, currency_value1);
            }
        });
        GUI.CAD1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currency_name1 = e.getActionCommand();
                currency_value1 = search_currency_value(currency_name1);
                GUI.Display_from_values(currency_name1, currency_value1);
            }
        });
        GUI.DKK1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currency_name1 = e.getActionCommand();
                currency_value1 = search_currency_value(currency_name1);
                GUI.Display_from_values(currency_name1, currency_value1);
            }
        });
        GUI.NOK1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currency_name1 = e.getActionCommand();
                currency_value1 = search_currency_value(currency_name1);
                GUI.Display_from_values(currency_name1, currency_value1);
            }
        });
        GUI.ZAR1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currency_name1 = e.getActionCommand();
                currency_value1 = search_currency_value(currency_name1);
                GUI.Display_from_values(currency_name1, currency_value1);
            }
        });
        GUI.SEK1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currency_name1 = e.getActionCommand();
                currency_value1 = search_currency_value(currency_name1);
                GUI.Display_from_values(currency_name1, currency_value1);
            }
        });
        GUI.CHF1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currency_name1 = e.getActionCommand();
                currency_value1 = search_currency_value(currency_name1);
                GUI.Display_from_values(currency_name1, currency_value1);
            }
        });
        GUI.JOD1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currency_name1 = e.getActionCommand();
                currency_value1 = search_currency_value(currency_name1);
                GUI.Display_from_values(currency_name1, currency_value1);
            }
        });
        GUI.LBP1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currency_name1 = e.getActionCommand();
                currency_value1 = search_currency_value(currency_name1);
                GUI.Display_from_values(currency_name1, currency_value1);
            }
        });
        GUI.EGP1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currency_name1 = e.getActionCommand();
                currency_value1 = search_currency_value(currency_name1);
                GUI.Display_from_values(currency_name1, currency_value1);
            }
        });
        GUI.USD2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currency_name2 = e.getActionCommand();
                currency_value2 = search_currency_value(currency_name2);
                GUI.Display_to_values(currency_name2, currency_value2);
            }
        });
        GUI.GBP2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currency_name2 = e.getActionCommand();
                currency_value2 = search_currency_value(currency_name2);
                GUI.Display_to_values(currency_name2, currency_value2);
            }
        });
        GUI.JPY2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currency_name2 = e.getActionCommand();
                currency_value2 = search_currency_value(currency_name2);
                GUI.Display_to_values(currency_name2, currency_value2);
            }
        });
        GUI.EUR2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currency_name2 = e.getActionCommand();
                currency_value2 = search_currency_value(currency_name2);
                GUI.Display_to_values(currency_name2, currency_value2);
            }
        });
        GUI.AUD2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currency_name2 = e.getActionCommand();
                currency_value2 = search_currency_value(currency_name2);
                GUI.Display_to_values(currency_name2, currency_value2);
            }
        });
        GUI.CAD2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currency_name2 = e.getActionCommand();
                currency_value2 = search_currency_value(currency_name2);
                GUI.Display_to_values(currency_name2, currency_value2);
            }
        });
        GUI.DKK2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currency_name2 = e.getActionCommand();
                currency_value2 = search_currency_value(currency_name2);
                GUI.Display_to_values(currency_name2, currency_value2);
            }
        });
        GUI.NOK2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currency_name2 = e.getActionCommand();
                currency_value2 = search_currency_value(currency_name2);
                GUI.Display_to_values(currency_name2, currency_value2);
            }
        });
        GUI.ZAR2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currency_name2 = e.getActionCommand();
                currency_value2 = search_currency_value(currency_name2);
                GUI.Display_to_values(currency_name2, currency_value2);
            }
        });
        GUI.SEK2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currency_name2 = e.getActionCommand();
                currency_value2 = search_currency_value(currency_name2);
                GUI.Display_to_values(currency_name2, currency_value2);
            }
        });
        GUI.CHF2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currency_name2 = e.getActionCommand();
                currency_value2 = search_currency_value(currency_name2);
                GUI.Display_to_values(currency_name2, currency_value2);
            }
        });
        GUI.JOD2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currency_name2 = e.getActionCommand();
                currency_value2 = search_currency_value(currency_name2);
                GUI.Display_to_values(currency_name2, currency_value2);
            }
        });
        GUI.LBP2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currency_name2 = e.getActionCommand();
                currency_value2 = search_currency_value(currency_name2);
                GUI.Display_to_values(currency_name2, currency_value2);
            }
        });
        GUI.EGP2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currency_name2 = e.getActionCommand();
                currency_value2 = search_currency_value(currency_name2);
                GUI.Display_to_values(currency_name2, currency_value2);
            }
        });
        GUI.Convert.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){    // the action listener of the conversion button.
        convert();                                 // currency buttons get their action listeners by inner classes.
    }
}