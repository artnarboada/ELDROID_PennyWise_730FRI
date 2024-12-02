package Models;

public class CategoryData {
    private String categoryName;
    private String categoryDescription;

    // Constructor
    public CategoryData(String categoryName, String categoryDescription) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    // Getters and setters
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    // Override toString to display category name in Spinner
    @Override
    public String toString() {
        return categoryName;  // Return category name for Spinner display
    }
}
