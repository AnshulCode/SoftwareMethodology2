package bankteller;


import java.text.DecimalFormat;

/**
 * The type Money market account.
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

        if (super.balance >= 2500) {
            super.fee = 0;
            return 0;
        }

        if (this.execessWithdrawl) {
            super.fee = 10;
            return 10;
        }
        super.fee = 10;
        return super.fee;
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
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     *
     * @param amount
     */
    @Override
    public void withdraw(double amount) {
        double prev_balence = super.getBalance();
        this.updateLoyalty();
        super.withdraw(amount);
        if (prev_balence != super.getBalance()) {
            this.numWithdrawls++;
            if (numWithdrawls > 3) {
                execessWithdrawl = true;
            }

        }
        this.updateLoyalty();
        super.fee = this.fee();

    }

    /**
     *
     * @return
     */
    @Override
    public String interestPreview() {
        this.updateLoyalty();
        return super.interestPreview();
    }

    /**
     *
     */
    @Override
    public void setMonthlyInterest() {
        this.updateLoyalty();
        super.setMonthlyInterest();

    }

    /**
     *
     * @return
     */
    @Override
    public double monthlyInterest() {
      return super.monthlyInterest();
    }
}
