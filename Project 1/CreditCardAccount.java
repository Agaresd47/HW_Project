/* Bruce Dong: This is a class that set up personal credit card accounts and allows user to
 * make any change to the account and also do common bank operator */

public class CreditCardAccount{
  // This is a private field to store the credit limit.
  private int creditLimit;
  
  // This is a private field to store the interest rate. 
  private double interestRate;
  
  // This is a private field to store the minimum payment for each month.
  private int minMonthPay;
  
  // This is a private field to store the late payment penalty.
  private int latePayedPenatly;
  
  // This is an instance field that stores the balance of the account owner
  private double balance=0;
  
  // This is a private field to store the amount that the user need to pay off the credit to full
  private double totalCharge =0;
  
  // This is an instance field that stores the total payment this month
  private double totalPayed  =0;
  
  // This is an instance field that stores the interest charged  so far
  private double interestCharged  =0;  
  
  // This ia an instance field that stores paid-in-full flag that keeps track of whether the user has paid the balance in full.
  private boolean paidInFullFlag = false;
  
  // This is the generic constructor for default values
  public CreditCardAccount(){
    this(0, 0.0, 0, 0);
  }
  
   // This is the actuall constructor
  public CreditCardAccount (int creditLimit, double interestRate, int minMonthPay, int latePayedPenatly){
    this.creditLimit=creditLimit;
    this.interestRate=interestRate;
    this.minMonthPay=minMonthPay;
    this.latePayedPenatly=latePayedPenatly;
  }
  
  // This method allows users to check his balance
  public double getBalance(){
    return balance;
  }
  
  // This method allows user to set the account's maximum amount that may be borrowed
  public void setCreditLimit( int creditLimit){
    this.creditLimit = creditLimit;
  }

   // This method allows user to check the account's maximum amount that may be borrowed
  public int  getCreditLimit(){
    return creditLimit;
  }
  
  // This method allows user to set the account's interest rate
  public void setInterestRate( double interestRate){
    this.interestRate = interestRate;
  }

   // This method allows user to check the account's interest rate
  public double getInterestRate(){
    return interestRate;
  }
  
  // This method allows user to set the account's minimum amount that must be paid to avoid a late payment penalty.
  public void setMinMonthlyPayment( int minMonthPay){
    this.minMonthPay = minMonthPay;
  }

   // This method allows user to check the account's minimum amount that must be paid to avoid a late payment penalty.
  public int  getMinMonthlyPayment(){
    return minMonthPay;
  }
  
  // This method allows user to set the account's penalty charged on a late payment month
  public void setLatePaymentPenalty( int latePayedPenatly){
    this.latePayedPenatly = latePayedPenatly;
  }

   // This method allows user to check the account's penalty charged on a late payment month
  public int getLatePaymentPenalty(){
    return latePayedPenatly;
  }
  
  // This method allows user to check the account's amount that the user needs to pay this month in order to pay off the credit card in full
  public double getMonthlyPayment(){
    return totalCharge;
  }
  
  // This method allows user to try to spend money with the credit card
  // non-field variable amount means the amount of money that user wants to spend
  public boolean charge(double amount){
    if ( (amount + balance)> creditLimit )
      return false;
    else{
      this.balance= balance + amount;
      this.totalCharge = totalCharge + amount;
      return true;
    }
  }
  
  // This is the method that stores how much money the owner payed into the acocunt
  // non-field variable payed means the payed amount 
  public void payment(double payed){
    this.balance = balance - payed;
    this.totalPayed = totalPayed+payed;
  }
  
  // This method should run each day and calculate the current interest charged
  public void incrementDay(){
    if ( paidInFullFlag == false)
      interestCharged =interestCharged+ (balance + interestCharged) *interestRate/365.0;
  }
  
  // This method should run each month and to judge whether the user paid all his debt and reach the montly min payment
  public void incrementMonth(){
    //1
    this.balance= balance + interestCharged;
    //2
    interestCharged =0;
    //3
    if(totalPayed >= totalCharge)
      paidInFullFlag = true;
    else
      paidInFullFlag = false;
    //4
    if( minMonthPay<totalCharge && totalPayed <minMonthPay)
      this.balance = balance + latePayedPenatly;
    //5
    totalPayed =0;
    //6
    totalCharge = balance;
  }
}