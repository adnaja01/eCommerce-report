public class VisaPayment implements Payment {
    @Override
    public double Transaction(double total) {
        return total * 0.02;
    }
}
