package Models;

public class BudgetData {
    private int userID;
    private int categoryID;
    private double budgetLimit;
    private String budgetNotes;

    // Constructor
    public BudgetData(int userID, int categoryID, double budgetLimit, String budgetNotes) {
        this.userID = userID;
        this.categoryID = categoryID;
        this.budgetLimit = budgetLimit;
        this.budgetNotes = budgetNotes;
    }

    // Getters and setters
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

    public double getBudgetLimit() {
        return budgetLimit;
    }

    public void setBudgetLimit(double budgetLimit) {
        this.budgetLimit = budgetLimit;
    }

    public String getBudgetNotes() {
        return budgetNotes;
    }

    public void setBudgetNotes(String budgetNotes) {
        this.budgetNotes = budgetNotes;
    }
}
