import java.util.*;

// Class to store user details
class User {
    private String name;
    private String mobileNumber;
    private double balance;

    // Constructor
    public User(String name, String mobileNumber, double balance) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void updateBalance(double amount) {
        balance += amount;
    }
}

// Class to store recharge details
class Recharge {
    private double amount;
    private Date date;

    // Constructor
    public Recharge(double amount) {
        this.amount = amount;
        this.date = new Date();
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }
}

// Class that handles recharge operations
class RechargeService {
    private List<Recharge> rechargeHistory = new ArrayList<>();

    public void recharge(User user, double amount) {
        user.updateBalance(amount);
        Recharge recharge = new Recharge(amount);
        rechargeHistory.add(recharge);

        // Recharge confirmation
        System.out.println("\n✅ Recharge Successful!");
        System.out.println("📱 Mobile Number : " + user.getMobileNumber());
        System.out.println("💰 Recharge Amount: ₹" + amount);
        System.out.println("💼 Updated Balance: ₹" + user.getBalance());
        System.out.println("📅 Date & Time   : " + recharge.getDate());
    }

    public void showRechargeHistory() {
        if (rechargeHistory.isEmpty()) {
            System.out.println("\nNo recharge history available.");
            return;
        }

        System.out.println("\n📜 Recharge History:");
        for (int i = 0; i < rechargeHistory.size(); i++) {
            Recharge r = rechargeHistory.get(i);
            System.out.println(
                (i + 1) + ". Amount: ₹" + r.getAmount() + 
                " | Date: " + r.getDate()
            );
        }
    }
}

// Main class
public class MobileRechargeSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("===== Mobile Recharge System =====");

        System.out.print("Enter User Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Mobile Number: ");
        String mobile = sc.nextLine();

        System.out.print("Enter Initial Balance: ");
        double balance = sc.nextDouble();

        User user = new User(name, mobile, balance);
        RechargeService service = new RechargeService();

        int choice;
        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Recharge Mobile");
            System.out.println("2. View Balance");
            System.out.println("3. View Recharge History");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter recharge amount: ");
                    double amount = sc.nextDouble();
                    service.recharge(user, amount);
                    break;

                case 2:
                    System.out.println("💼 Current Balance: ₹" + user.getBalance());
                    break;

                case 3:
                    service.showRechargeHistory();
                    break;

                case 4:
                    System.out.println("Thank you for using Mobile Recharge System!");
                    break;

                default:
                    System.out.println("❌ Invalid choice!");
            }
        } while (choice != 4);

        sc.close();
    }
}
