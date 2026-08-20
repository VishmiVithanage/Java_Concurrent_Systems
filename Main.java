public class Main {
    public static void main(String[] args) {
        TransactionSystem bank = new TransactionSystem();
        BankAccount account1 = new BankAccount(1, 500);
        BankAccount account2 = new BankAccount(2, 1000);
        BankAccount account3 = new BankAccount(3, 750);

        // Create threads for the transfer tasks
        Thread transferThread1 = new Thread(() -> {
            bank.transfer(account1, account2, 100);
        });

        Thread transferThread2 = new Thread(() -> {
            bank.transfer(account2, account3, 200);
        });

        Thread transferThread3 = new Thread(() -> {
            bank.transfer(account3, account1, 50);
        });

        Thread reverseThread3 = new Thread(() -> {
            bank.reverse(account2, account1, 50);
        });

        // Create a thread to print the account balances
        Thread printBalancesThread = new Thread(() -> {
            System.out.println("Balance of Account 1: " + account1.getBalance());
            System.out.println("Balance of Account 2: " + account2.getBalance());
            System.out.println("Balance of Account 3: " + account3.getBalance());
        });

        // Start all the threads
        transferThread1.start();
        transferThread2.start();
        transferThread3.start();
        reverseThread3.start();
        printBalancesThread.start();

        try {
            // Wait for the transfer threads to finish
            transferThread1.join();
            transferThread2.join();
            transferThread3.join();
            reverseThread3.join();
            // Wait for the print balances thread to finish (optional, can be omitted if not needed)
            printBalancesThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Print final account details
        bank.printAccountDetails(account1);
        bank.printAccountDetails(account2);
        bank.printAccountDetails(account3);
    }
}
