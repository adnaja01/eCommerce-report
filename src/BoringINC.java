import java.util.HashMap;
import java.util.Map;

public record BoringINC(String name, String shirt_size, Boolean with_design, Boolean with_hoodie,
                        Payment payment) implements Runnable {
    private static final double shirt_base_price = 14.0;
    private static final double design_price = 2.0;
    private static final double hoodie_price = 3.0;
    private static double revenue = 0.0;
    private static double profit = 0.0;
    private static final Map<String, Double> profitPerShirtSize = new HashMap<>();

    public static double getProfit() {
        return profit;
    }

    public static double getTotalRevenue() {
        return revenue;
    }

    public static Map<String, Double> getProfitPerShirtSize() {
        return profitPerShirtSize;
    }

    private double shirtPrice() {
        double shirt = 40.0;
        if (with_design) {
            shirt += design_price;
        }
        if (with_hoodie) {
            shirt += hoodie_price;
        }
        return shirt;
    }

    private double Transaction(double total) {
        double fee = payment.Transaction(total);
        return total - fee;
    }

    private static void newRevenueAndTransaction(double total, double shirt) {
        revenue += shirt;
        profit = profit + (total - shirt_base_price);
    }

    private static void newProfitPerShirtSize(String shirtSize, double totalAmount) {
        profitPerShirtSize.merge(shirtSize, totalAmount - shirt_base_price, Double::sum);
    }

    @Override
    public void run() {
        double shirt = shirtPrice();
        double total = Transaction(shirt);
        newRevenueAndTransaction(total, shirt);
        newProfitPerShirtSize(shirt_size, total);
    }
}
