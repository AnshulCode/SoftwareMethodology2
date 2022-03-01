package bankteller;

/**
 * The type Main.
 */
public class main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
    /*

        test.print();
 */
   Account a = new Checking(new Profile("Anshul","Prasad","01/31/2001"),2200.0);
   System.out.println(a.rounder(1.47+2200.0));

    }
}
