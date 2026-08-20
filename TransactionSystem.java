class TransactionSystem {
    public void transfer(BankAccount from, BankAccount to, double amount) {
        // Lock accounts in a consistent order to avoid deadlocks
        BankAccount first = from.getId() < to.getId() ? from : to;
        BankAccount second = from.getId() < to.getId() ? to : from;

        first.lock();
        try {
            second.lock();
            try {
                if (from.getBalance() >= amount) {
                    from.withdraw(amount);
                    to.deposit(amount);
                    from.addTransaction("Transferred $" + amount + " to Account " + to.getId());
                    to.addTransaction("Received $" + amount + " from Account " + from.getId());
                    System.out.println("Transfer successful: $" + amount + " from Account " + from.getId() + " to Account " + to.getId());
                } else {
                    System.out.println("Transfer failed: Insufficient funds in Account " + from.getId());
                }
            } finally {
                second.unlock();
            }
        } finally {
            first.unlock();
        }
    }

    public void reverse(BankAccount from, BankAccount to, double amount) {
        // Reversal logic is the same as transfer but in reverse direction
        //System.out.println("An error occurred!");
        //transfer(to, from, amount);

        BankAccount first = from.getId() < to.getId() ? from : to;
        BankAccount second = from.getId() < to.getId() ? to : from;

        first.lock();
        try {
            second.lock();
            try {
                if (from.getBalance() >= amount) {
                    from.withdraw(amount);
                    to.deposit(amount);
                    from.addTransaction("Reversed $" + amount + " to Account " + to.getId());
                    to.addTransaction("Reversed $" + amount + " from Account " + from.getId());
                    System.out.println("An error occurred! Transaction reversed: $" + amount + " from Account " + from.getId() + " to Account " + to.getId());
                } else {
                    System.out.println("Transfer failed: Insufficient funds in Account " + from.getId());
                }
            } finally {
                second.unlock();
            }
        } finally {
            first.unlock();
        }
    }

    public void printAccountDetails(BankAccount account) {
        System.out.println("Account " + account.getId() + ": Balance = $" + account.getBalance());
        System.out.println("Transaction History: " + account.getTransactionHistory());
    }
}
