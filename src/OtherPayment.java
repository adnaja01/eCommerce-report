public class OtherPayment implements Payment {
    @Override
    public double Transaction(double total) {
        return total * 0.1;
    }
}
