package Models;

public class CategoryData {
    private String categoryName;
    private String categoryDescription;
    private String categoryIcon;

    public CategoryData(String categoryName, String categoryDescription, String categoryIcon) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.categoryIcon = categoryIcon;
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

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }
}

