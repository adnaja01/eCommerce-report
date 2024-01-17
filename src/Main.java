import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Map<String, Payment> way_of_payment = new HashMap<>();
    static {
        way_of_payment.put("wallet", new WalletPayment());
        way_of_payment.put("bankcard", new BankcardPayment());
        way_of_payment.put("visa", new VisaPayment());
        way_of_payment.put("mastercard", new MastercardPayment());
    }
    public static void main(String[] args) throws IOException {
        ArrayList<BoringINC> customers = new ArrayList<>();
        getCustomers(customers);

        for(BoringINC b: customers){
            Thread t = new Thread(b);
            t.start();
            try{
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        getRevenue();
        getProfit();
        getProfitPerShirtSize();
    }

    private static void getProfitPerShirtSize() throws IOException {
        FileWriter output = new FileWriter("Profit_Per_Shirt_Size.txt");
        try {
            output.write("Shirt size" + " - " + "Profit\n");
            Map<String, Double>profitPerSize = BoringINC.getProfitPerShirtSize();
            for(Map.Entry<String, Double>entry: profitPerSize.entrySet()){
                BigDecimal value = BigDecimal.valueOf(entry.getValue()).setScale(2, BigDecimal.ROUND_HALF_UP);
                output.write(entry.getKey() + " - " + value + "\n");
            }
            output.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getProfit() throws IOException {
        FileWriter output = new FileWriter("Profit_in_Total.txt");
        try {
            BigDecimal totalProfit = BigDecimal.valueOf(BoringINC.getProfit());
            totalProfit = new BigDecimal(String.valueOf(totalProfit)).setScale(2, BigDecimal.ROUND_HALF_UP);
            output.write("Total profit: " + totalProfit);
            output.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getRevenue() throws IOException {
        FileWriter output = new FileWriter("Revenue_in_Total.txt");
        try {
            BigDecimal totalRevenue = BigDecimal.valueOf(BoringINC.getTotalRevenue());
            totalRevenue = new BigDecimal(String.valueOf(totalRevenue)).setScale(2, BigDecimal.ROUND_HALF_UP);
            output.write("Total revenue: " + totalRevenue);
            output.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getCustomers(ArrayList<BoringINC> customers) throws FileNotFoundException {
        File file = new File("src/customer_orders.csv");
        Scanner s = new Scanner(file);
        if (s.hasNextLine()) {
            s.nextLine();
        }
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] linePart = line.split(",");
            String name = linePart[0].trim();
            String shirt_size = linePart[1].trim();
            Boolean with_design = Boolean.parseBoolean(linePart[2].trim());
            Boolean with_hoodie = Boolean.parseBoolean(linePart[3].trim());
            String payment = linePart[4].trim();

            Payment p;
            if(Main.way_of_payment.containsKey(payment))
                p = Main.way_of_payment.get(payment);
            else
                p = new OtherPayment();

            BoringINC b = new BoringINC(name, shirt_size, with_design, with_hoodie, p);
            customers.add(b);
        }
    }
}