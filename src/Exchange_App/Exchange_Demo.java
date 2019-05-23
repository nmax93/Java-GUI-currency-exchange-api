package Exchange_App;

public class Exchange_Demo {

    public static void main(String args[]){

        Exchange_Class app = Exchange_Class.instance();
        Thread t = new Thread(app);
        t.start();
    }
}