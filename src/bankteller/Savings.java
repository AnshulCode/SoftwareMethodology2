package bankteller;


import java.text.DecimalFormat;

/**
 * The type Savings.
 * @author Anshul Prasad, Alexander Reyes
 */
public class Savings extends Account {
    private final String TYPE = "Savings";
    protected double fee = 6;
    protected double rate = .003 / 12;


    protected boolean isLoyal;

    /**
     *Rounds decimals to Correct place, Inherited from Account
     * @param amount
     * @return
     */
    @Override
    public double rounder(double amount){
        return super.rounder(amount);
    }
    /**
     * Instantiates a new Savings.
     *
     * @param holder  the holder
     * @param balance the balance
     * @param loyalty the loyalty
     */
    public Savings(Profile holder, double balance, int loyalty) {
        super.holder = holder;
        super.balance = balance;
        if (loyalty == 1) {
            this.isLoyal = true;
        }
        if (this.isLoyal) {
            this.rate = 0.0045 / 12;
        }


    }

    /**
     * Base constructor, dont use this
     */
    public Savings(){

    }

    /**
     * opens account inherited from Account
     * @param amount the initial deposit
     */
    @Override
    public void open(double amount) {
        super.open(amount);
    }

    /**
     * Gets fee, inherited from Account,implemented here
     * @return fee
     */

    @Override
    public double fee() {
        if (super.balance >= 300) {
            this.fee = 0;
            return 0;
        }
        this.fee = 6;
        return 6;
    }

    /**
     *Closes account, inherited from Account
     */
    @Override
    public void close() {
        super.close();
    }

    /**
     *Gets Type of Account, inherited from account, implemented here
     * @return
     */
    @Override
    public String getType() {
        return this.TYPE;
    }

    /**
     *Coverts Account to readable formant, Inherited from Account
     * @return
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     *Checks if Accounts are equal, Inherited from Account
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * withdraws from balance, inherited from Account
     * @param amount the amount to withdraw
     */

    @Override
    public void withdraw(double amount) {
        super.withdraw(amount);
    }

    /**
     *Gives the interest preview for printFeeAndInterest method in AccountDatabase
     * @return
     */
    @Override
    public String interestPreview() {
        DecimalFormat decimalFormat = new DecimalFormat("##,###,###,###.##");

        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);
        String acct = this.toString();
        return acct + "::fee $" + decimalFormat.format(this.fee()) + "::monthly interest $" +
                decimalFormat.format(this.monthlyInterest());
    }

    /**
     * checks is there is enough to withdraw, inherited from Account
     * @param amount the amount to subtract
     * @return
     */
    @Override
    public boolean isSufficentFunds(double amount) {
        return super.isSufficentFunds(amount);
    }

    /**
     * Updates balance with interest
     */

    @Override
    public void setMonthlyInterest() {
        if (super.isClosed()) {
            return;
        }
        super.balance += this.monthlyInterest();
        super.balance -= this.fee();
        super.balance = super.rounder(super.balance);
    }

    /**
     * Gives the format for toString method
     * @return String for toString
     */
    @Override
    public String printFormat() {
        DecimalFormat deciFormat = new DecimalFormat("###,###,###.##");

        deciFormat.setMaximumFractionDigits(2);
        deciFormat.setMinimumFractionDigits(2);

        String rateRounded = deciFormat.format(super.rounder(super.balance));
        if(!this.isLoyal) {
            if (super.closed) {
                return this.TYPE + "::" + super.holder.toString() + "::Balance $" + rateRounded + "::CLOSED";
            }
            return this.TYPE + "::" + super.holder.toString() + "::Balance $" + rateRounded;
        }
        if (super.closed) {
            return this.TYPE + "::" + super.holder.toString() + "::Balance $" + rateRounded+ "::CLOSED";
        }
        return this.TYPE + "::" + super.holder.toString() + "::Balance $" + rateRounded+"::Loyal";
    }

    /**
     * gets monthly interest, Inherited from Account
     * @return
     */
    @Override
    public double monthlyInterest() {

        double monthlyInterest = this.balance * this.rate;
        return super.rounder(monthlyInterest);
    }
}
