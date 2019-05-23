package Exchange_App;
import javax.swing.*;
import java.awt.*;

public class GUI {

    private static GUI GUI = null;
    private JFrame frame;
    private JTextField result, fromVal, toVal, fromName, toName, date;
    private JLabel fromLabel, toLabel;
    public JButton USD1, GBP1, JPY1, EUR1, AUD1, CAD1, DKK1, NOK1, ZAR1, SEK1, CHF1, JOD1, LBP1, EGP1;
    public JButton USD2, GBP2, JPY2, EUR2, AUD2, CAD2, DKK2, NOK2, ZAR2, SEK2, CHF2, JOD2, LBP2, EGP2;
    public JButton Convert;



    public static GUI instance(){   // GUI instance

        if(GUI == null) GUI = new GUI();
        return GUI;
    }

    private GUI(){    // constructor

        Create_Frame();
        Create_Displays();
        Create_Buttons();
        frame.pack();
        frame.setSize(500,600);
    }

    private void Create_Frame(){    // create the frame containing everything

        frame = new JFrame("Currency Exchange Rates Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private void Create_Displays(){     // create all the displays (currency name, value, conversion result, date)

        // labels "From" and "To" to tell the displays purpose
        Font font = new Font("SansSerif", Font.BOLD, 16);
        fromLabel = new JLabel("From:");
        fromLabel.setFont(font);
        fromLabel.setBounds(120,120,80,30);
        toLabel = new JLabel("To:");
        toLabel.setFont(font);
        toLabel.setBounds(280,120,80,30);

        // create display for the selected currency NAME to convert FROM
        fromName = new JTextField(3);
        fromName.setFont(font);
        fromName.setBounds(120, 160, 90, 40);
        fromName.setEditable(false);

        // create display for the selected currency VALUE to convert FROM
        fromVal = new JTextField(6);
        fromVal.setFont(font);
        fromVal.setBounds(120, 210, 90, 40);
        fromVal.setEditable(false);

        // create display for the selected currency NAME to convert TO
        toName = new JTextField(3);
        toName.setFont(font);
        toName.setBounds(280, 160, 90, 40);
        toName.setEditable(false);

        // create display for the selected currency VALUE to convert TO
        toVal = new JTextField(6);
        toVal.setFont(font);
        toVal.setBounds(280, 210, 90, 40);
        toVal.setEditable(false);

        // create display showing the DATE the data updated to
        date = new JTextField(25);
        date.setFont(font);
        date.setBounds(135, 500, 220, 40);
        date.setEditable(false);

        // create display showing the RESULT of the conversion between two currencies
        result = new JTextField(10);
        Font res_font = new Font("SansSerif", Font.BOLD, 36);
        result.setBounds(20,20,450,80);
        result.setFont(res_font);
        result.setEditable(false);

        // adding all the above components to the frame
        frame.add(result);
        frame.add(fromLabel);
        frame.add(toLabel);
        frame.add(fromName);
        frame.add(toName);
        frame.add(fromVal);
        frame.add(toVal);
        frame.add(date);
    }

    private void Create_Buttons(){    // creating two sets of currency buttons. one for conversion FROM, and one for conversion TO.
                                      // creating conversion button to submit a conversion.
        // FROM buttons
        USD1 = new JButton("USD");
        USD1.setBounds(20, 120, 80, 30);
        GBP1 = new JButton("GBP");
        GBP1.setBounds(20, 150, 80, 30);
        JPY1 = new JButton("JPY");
        JPY1.setBounds(20, 180, 80, 30);
        EUR1 = new JButton("EUR");
        EUR1.setBounds(20, 210, 80, 30);
        AUD1 = new JButton("AUD");
        AUD1.setBounds(20, 240, 80, 30);
        CAD1 = new JButton("CAD");
        CAD1.setBounds(20, 270, 80, 30);
        DKK1 = new JButton("DKK");
        DKK1.setBounds(20, 300, 80, 30);
        NOK1 = new JButton("NOK");
        NOK1.setBounds(20, 330, 80, 30);
        ZAR1 = new JButton("ZAR");
        ZAR1.setBounds(20, 360, 80, 30);
        SEK1 = new JButton("SEK");
        SEK1.setBounds(20, 390, 80, 30);
        CHF1 = new JButton("CHF");
        CHF1.setBounds(20, 420, 80, 30);
        JOD1 = new JButton("JOD");
        JOD1.setBounds(20, 450, 80, 30);
        LBP1 = new JButton("LBP");
        LBP1.setBounds(20, 480, 80, 30);
        EGP1 = new JButton("EGP");
        EGP1.setBounds(20, 510, 80, 30);

        // TO buttons
        USD2 = new JButton("USD");
        USD2.setBounds(390, 120, 80, 30);
        GBP2 = new JButton("GBP");
        GBP2.setBounds(390, 150, 80, 30);
        JPY2 = new JButton("JPY");
        JPY2.setBounds(390, 180, 80, 30);
        EUR2 = new JButton("EUR");
        EUR2.setBounds(390, 210, 80, 30);
        AUD2 = new JButton("AUD");
        AUD2.setBounds(390, 240, 80, 30);
        CAD2 = new JButton("CAD");
        CAD2.setBounds(390, 270, 80, 30);
        DKK2 = new JButton("DKK");
        DKK2.setBounds(390, 300, 80, 30);
        NOK2 = new JButton("NOK");
        NOK2.setBounds(390, 330, 80, 30);
        ZAR2 = new JButton("ZAR");
        ZAR2.setBounds(390, 360, 80, 30);
        SEK2 = new JButton("SEK");
        SEK2.setBounds(390, 390, 80, 30);
        CHF2 = new JButton("CHF");
        CHF2.setBounds(390, 420, 80, 30);
        JOD2 = new JButton("JOD");
        JOD2.setBounds(390, 450, 80, 30);
        LBP2 = new JButton("LBP");
        LBP2.setBounds(390, 480, 80, 30);
        EGP2 = new JButton("EGP");
        EGP2.setBounds(390, 510, 80, 30);

        // conversion button
        Convert = new JButton("CONVERT");
        Convert.setBounds(170, 280, 150, 150);
        Convert.setBackground(Color.decode("#54B948"));
        frame.add(Convert);

        // adding all the above components to the frame
        frame.add(USD1);
        frame.add(GBP1);
        frame.add(JPY1);
        frame.add(EUR1);
        frame.add(AUD1);
        frame.add(CAD1);
        frame.add(DKK1);
        frame.add(NOK1);
        frame.add(ZAR1);
        frame.add(SEK1);
        frame.add(CHF1);
        frame.add(JOD1);
        frame.add(LBP1);
        frame.add(EGP1);
        frame.add(USD2);
        frame.add(GBP2);
        frame.add(JPY2);
        frame.add(EUR2);
        frame.add(AUD2);
        frame.add(CAD2);
        frame.add(DKK2);
        frame.add(NOK2);
        frame.add(ZAR2);
        frame.add(SEK2);
        frame.add(CHF2);
        frame.add(JOD2);
        frame.add(LBP2);
        frame.add(EGP2);
    }

    // SETTERS
    public void Display_date(String d){ date.setText("Updated to date: " + d); }  // display date

    public void Display_from_values(String name, String value){     // display selected currency name and value
        fromName.setText(name);                                     // (FROM conversion values)
        fromVal.setText(value + " ILS");
    }

    public void Display_to_values(String name, String value){       // display selected currency name and value
        toName.setText(name);                                       // (TO conversion values)
        toVal.setText(value + " ILS");
    }

    public void Display_Result(double res, String cur1, String cur2){    // display the conversion result

        String str = String.format("%.4f", res);
        result.setText(" 1 " + cur1 + " = " + str + " " + cur2);
    }
}