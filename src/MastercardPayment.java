public class MastercardPayment implements Payment {
    @Override
    public double Transaction(double total) {
        return total * 0.03;
    }
}
