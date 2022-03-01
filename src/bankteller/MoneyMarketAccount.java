package bankteller;


import java.text.DecimalFormat;

/**
 * The type Money market account.
 * @author Anshul Prasad, Alexander Reyes
 */
public class MoneyMarketAccount extends Savings{


    private final String TYPE = "Money Market";

    private boolean execessWithdrawl = false;
    private int numWithdrawls = 0;

    /**
     * Instantiates a new Money market account.
     *
     * @param holder  the holder
     * @param balance the balance
     */
    public MoneyMarketAccount(Profile holder, double balance) {
        super.holder = holder;
        super.balance = balance;

        if (super.balance < 2500) {
            super.isLoyal = false;
            super.rate = .008 / 12;
        } else {
            super.isLoyal = true;
        }
        super.rate = .0095 / 12;
        this.fee();
    }

    /**
     *
     * @param amount
     */
    @Override
    public void open(double amount) {
        super.open(amount);
        this.updateLoyalty();
    }

    /**
     * implements the Account close function
     */

    @Override
    public void close() {

        super.close();
        this.numWithdrawls = 0;
        this.execessWithdrawl = false;
    }

    /**
     * gets fee
     *
     * @return the fee
     */
    @Override
    public double fee() {

        if (this.execessWithdrawl) {
            super.fee = 10;
            return 10;
        }

        if (super.balance >= 2500) {
            super.fee = 0;
            return 0;
        }


        return 10;
    }

    /**
     * updates loyalty status and adjust fees and raters accordingly
     */
    private void updateLoyalty() {
        System.out.println("Balance: "+ super.balance);
        if (super.balance < 2500) {
            super.isLoyal = false;
            super.rate = .008 / 12;
            return;
        }
        super.rate = 0.0095/12;
        super.isLoyal = true;
    }

    /**
     *
     * @param amount the amount
     */
    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        this.updateLoyalty();
    }

    /**
     *
     * @return
     */
    @Override
    public String getType() {
        return TYPE;
    }

    /**
     *
     * @return
     */
    @Override
    public String printFormat() {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###.##");
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);



        super.balance = super.rounder(super.balance);
        String balenceRounded = decimalFormat.format(super.balance);

        this.updateLoyalty();
        if (!super.closed) {
            if (super.isLoyal)
                return this.TYPE+" Savings" + "::" + super.holder.toString() + "::Balance $" +
                        balenceRounded + "::Loyal::withdrawl: " + Integer.toString(this.numWithdrawls);
            return this.TYPE+" Savings" + "::" + super.holder.toString() + "::Balance $" +
                    balenceRounded + "::withdrawl: " + Integer.toString(numWithdrawls);
        }
        return this.TYPE+" Savings" + "::" + super.holder.toString() + "::Balance $" + balenceRounded + "::CLOSED"
                + "::withdrawl: " + Integer.toString(this.numWithdrawls);
    }

    /**
     *
     * @param amount
     * @return
     */
    @Override
    public boolean isSufficentFunds(double amount) {
        this.updateLoyalty();
        return super.isSufficentFunds(amount);
    }

    /**
     *AInherited from Savings and Account
     * @param obj
     * @return
     */

   /* @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    */

    /**
     * withdraw from MoneyMarket Account Inherited from Savings
     * @param amount
     */
    @Override
    public void withdraw(double amount) {
       if(!super.isSufficentFunds(amount)){
           return;
       }
       this.numWithdrawls++;
       if(this.numWithdrawls >3 ){
            this.execessWithdrawl = true;
            super.fee = this.fee();
       }
       super.balance -= amount;
       super.balance = super.rounder(super.balance);
       updateLoyalty();

    }

    /**
     *Gives interest preview for printFeeAndInterst method
     * @return
     */
    @Override
    public String interestPreview() {
        return super.interestPreview();
    }

    /**
     * sets balance with Interest, Inherited from Savings
     */
    @Override
    public void setMonthlyInterest() {
        this.updateLoyalty();
        super.setMonthlyInterest();

    }

    /**
     *Gets monthly interest Inherited from Savings
     * @return
     */
    @Override
    public double monthlyInterest() {
        this.updateLoyalty();
      return super.monthlyInterest();
    }
}
