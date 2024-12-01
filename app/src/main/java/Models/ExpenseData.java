package Models;

public class ExpenseData {
    private int userID;
    private int categoryID;
    private String expenseName;
    private double expenseAmount;
    private String expenseDescription;

    // Constructor
    public ExpenseData(int userID, int categoryID, String expenseName, double expenseAmount, String expenseDescription) {
        this.userID = userID;
        this.categoryID = categoryID;
        this.expenseName = expenseName;
        this.expenseAmount = expenseAmount;
        this.expenseDescription = expenseDescription;
    }

    // Getters and Setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public String getExpenseDescription() {
        return expenseDescription;
    }

    public void setExpenseDescription(String expenseDescription) {
        this.expenseDescription = expenseDescription;
    }
}
