import java.util.*;

class BankAccount {
    private String accountHolder;
    private String accountNumber;
    private double balance;
    private ArrayList<String> transactionHistory;

    public BankAccount(String accountHolder, String accountNumber, double initialBalance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        addTransaction("Account created with balance: $" + initialBalance);
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            addTransaction("Deposited: $" + amount + ", New Balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            addTransaction("Withdrew: $" + amount + ", New Balance: $" + balance);
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    public void transfer(BankAccount recipient, double amount) {
        if (amount > 0 && amount <= balance) {
            this.withdraw(amount);
            recipient.deposit(amount);
            addTransaction("Transferred $" + amount + " to " + recipient.getAccountNumber());
        } else {
            System.out.println("Invalid transfer amount.");
        }
    }

    public void printTransactionHistory() {
        System.out.println("\nTransaction History for Account: " + accountNumber);
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    private void addTransaction(String transaction) {
        transactionHistory.add(transaction);
    }
}

public class OnlineBankingApp {
    private static Scanner scanner = new Scanner(System.in);
    private static HashMap<String, BankAccount> accounts = new HashMap<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Online Banking Application ---");
            System.out.println("1. Create Account");
            System.out.println("2. Login to Account");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> login();
                case 3 -> {
                    System.out.println("Thank you for using the Online Banking Application!");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Set your account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter initial deposit amount: ");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine();

        if (accounts.containsKey(accountNumber)) {
            System.out.println("Account number already exists. Try a different one.");
        } else {
            BankAccount newAccount = new BankAccount(name, accountNumber, initialBalance);
            accounts.put(accountNumber, newAccount);
            System.out.println("Account created successfully!");
        }
    }

    private static void login() {
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();

        BankAccount account = accounts.get(accountNumber);
        if (account != null) {
            while (true) {
                System.out.println("\n--- Welcome, " + account.getAccountHolder() + " ---");
                System.out.println("1. View Balance");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. Transfer Money");
                System.out.println("5. Transaction History");
                System.out.println("6. Logout");
                System.out.print("Select an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> System.out.println("Current Balance: $" + account.getBalance());
                    case 2 -> {
                        System.out.print("Enter deposit amount: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();
                        account.deposit(amount);
                    }
                    case 3 -> {
                        System.out.print("Enter withdrawal amount: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();
                        account.withdraw(amount);
                    }
                    case 4 -> {
                        System.out.print("Enter recipient account number: ");
                        String recipientAccountNumber = scanner.nextLine();
                        System.out.print("Enter transfer amount: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();
                        BankAccount recipient = accounts.get(recipientAccountNumber);
                        if (recipient != null) {
                            account.transfer(recipient, amount);
                        } else {
                            System.out.println("Recipient account not found.");
                        }
                    }
                    case 5 -> account.printTransactionHistory();
                    case 6 -> {
                        System.out.println("Logged out successfully.");
                        return;
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            }
        } else {
            System.out.println("Account not found. Please check the account number.");
        }
    }
}
