public class BankcardPayment implements Payment {
    @Override
    public double Transaction(double total) {
        return total * 0.05;
    }
}