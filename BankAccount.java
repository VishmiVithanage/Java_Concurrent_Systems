import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.*;

class BankAccount {
    private final int id;
    private double balance;
    private final Lock lock = new ReentrantLock(true); // Fair lock for fair access
    private final List<String> transactionHistory = new CopyOnWriteArrayList<>();

    public BankAccount(int id, double initialBalance) {
        this.id = id;
        this.balance = initialBalance;
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void lock() {
        lock.lock();
    }

    public void unlock() {
        lock.unlock();
    }

    public void addTransaction(String transaction) {
        transactionHistory.add(transaction);
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }
}
