package Models;

public class ExpenseResponse {
    private String message;
    private ExpenseData expense;

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ExpenseData getExpense() {
        return expense;
    }

    public void setExpense(ExpenseData expense) {
        this.expense = expense;
    }
}
