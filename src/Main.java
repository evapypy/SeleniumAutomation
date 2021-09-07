import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        //Original values
        double PurchaseValue= 8000;
        double InterestRate = 3;
        int PaymentTerm = 5;
        double FirstInstallment = 0;
        double RemainingValue=0;


        // Compares interest rate
        // As an example, we make 100 different comparisons starting from interest rate of 2%
        // to an interest rate of 3% increasing by 0.01%

        BigDecimal InterestRateFrom=new BigDecimal("2");
        BigDecimal InterestRateTo= new BigDecimal("3");
        BigDecimal InterestIncreasesBy = new BigDecimal("0.01");

        //Method
        Tasks.Comparison(PurchaseValue, InterestRateFrom, InterestRateTo, InterestIncreasesBy, PaymentTerm, FirstInstallment, RemainingValue);


        // PaymentCalculation method returns value closest to the monthly payment required
        // by changing first installment %

        double MonthlyPayment = 100;

        //Method
       // Tasks.PaymentCalculation(PurchaseValue,InterestRate,PaymentTerm,FirstInstallment,RemainingValue,MonthlyPayment);

    }
}
