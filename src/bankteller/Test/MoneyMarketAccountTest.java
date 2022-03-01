package bankteller.Test;

import bankteller.MoneyMarketAccount;
import bankteller.Profile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyMarketAccountTest {
    /**
     *  Amount = 2500
     *  Interest = 1.98
     */
    @Test
    void monthlyInterest1() {
        MoneyMarketAccount test = new MoneyMarketAccount(new Profile
                ("Anshul","Prasad","01/12/2001"),2500);
        Assertions.assertEquals(1.98,test.monthlyInterest());
    }

    /**
     * Amount = 2400
     * Interest = 1.60
     */
    @Test
    void monthlyInterest2() {
        MoneyMarketAccount test = new MoneyMarketAccount(new Profile
                ("Anshul","Prasad","01/12/2001"),2400);
        Assertions.assertEquals(1.60,test.monthlyInterest());
    }
}