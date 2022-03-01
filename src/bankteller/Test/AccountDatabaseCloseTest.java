package bankteller.Test;

import bankteller.*;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountDatabaseCloseTest {

    /**
     *Close should work for MoneyMarket
     */
    @Test
    void close() {
        Profile holder = new Profile("Anshul", "prasad", "06/18/2001");

        Profile holder2 = new Profile("April", "March", "1/15/1987");

        Profile holder3 = new Profile("Aayush", "prasad", "06/18/2001");
        Profile holder4 = new Profile("John", "Doe", "05/18/2009");

        Account account1 = new MoneyMarketAccount(holder, 2600);
        Account account2 = new Savings(holder2, 100.0,1);
        Account account3 = new CollegeCheckingAccount(holder3, 100.0,1);

        AccountDatabase test = new AccountDatabase();

        test.open(account1);
        test.open(account2);
        test.open(account3);

        Account close = new MoneyMarketAccount(holder,2);

        Assertions.assertEquals(true,test.close(close));

    }


    /**
     *  Checking account  where holder has College Checking Fail
     */
    @Test
    void close2() {
        Profile holder = new Profile("Anshul", "prasad", "06/18/2001");

        Profile holder2 = new Profile("April", "March", "1/15/1987");

        Profile holder3 = new Profile("Aayush", "prasad", "06/18/2001");
        Profile holder4 = new Profile("John", "Doe", "05/18/2009");

        Account account1 = new MoneyMarketAccount(holder, 2600);
        Account account2 = new Savings(holder2, 100.0,1);
        Account account3 = new CollegeCheckingAccount(holder3, 100.0,1);

        AccountDatabase test = new AccountDatabase();

        test.open(account1);
        test.open(account2);
        test.open(account3);

        Account close = new Checking(holder3,2);

        Assertions.assertEquals(false,test.close(close));

    }

    /**
     * Other account type but account not present, Fail
     */
    @Test
    void close3() {
        Profile holder = new Profile("Anshul", "prasad", "06/18/2001");

        Profile holder2 = new Profile("April", "March", "1/15/1987");

        Profile holder3 = new Profile("Aayush", "prasad", "06/18/2001");
        Profile holder4 = new Profile("John", "Doe", "05/18/2009");

        Account account1 = new MoneyMarketAccount(holder, 2600);
        Account account2 = new Savings(holder2, 100.0,1);
        Account account3 = new CollegeCheckingAccount(holder3, 100.0,1);

        AccountDatabase test = new AccountDatabase();

        test.open(account1);
        test.open(account2);
        test.open(account3);

        Account close = new Savings(holder3,2,1);

        Assertions.assertEquals(false,test.close(close));

    }
    /**
     * Close Already Closed, Fail
     */
    @Test
    void close4() {
        Profile holder = new Profile("Anshul", "prasad", "06/18/2001");

        Profile holder2 = new Profile("April", "March", "1/15/1987");

        Profile holder3 = new Profile("Aayush", "prasad", "06/18/2001");
        Profile holder4 = new Profile("John", "Doe", "05/18/2009");

        Account account1 = new MoneyMarketAccount(holder, 2600);
        Account account2 = new Savings(holder2, 100.0,1);
        Account account3 = new CollegeCheckingAccount(holder3, 100.0,1);

        AccountDatabase test = new AccountDatabase();

        test.open(account1);
        test.open(account2);
        test.open(account3);
        test.close(account3);

        Assertions.assertEquals(false,test.close(account3));

    }
}
