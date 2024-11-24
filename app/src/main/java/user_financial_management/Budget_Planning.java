package user_financial_management;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.eldroid.pennywise.R;

public class Budget_Planning extends AppCompatActivity {

    private EditText budgetEditText, noteEditText, travelEditText, foodEditText, accommodationEditText;
    private RadioGroup categoryRadioGroup;
    private Button addPlanBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_planning);

        // Initialize back button
        LinearLayout backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(view -> finish());

        // Initialize views
        budgetEditText = findViewById(R.id.budgetEditText);
        noteEditText = findViewById(R.id.noteEditText);
        travelEditText = findViewById(R.id.travelEditText);
        foodEditText = findViewById(R.id.foodEditText);
        accommodationEditText = findViewById(R.id.accommodationEditText);
        categoryRadioGroup = findViewById(R.id.categoryRadioGroup);
        addPlanBtn = findViewById(R.id.addPlanBtn);

        // Add listener for the Add Plan button
        addPlanBtn.setOnClickListener(view -> {
            if (validateInputs()) {
                String selectedCategory = getSelectedCategory();
                String budget = budgetEditText.getText().toString().trim();
                String notes = noteEditText.getText().toString().trim();

                Toast.makeText(this, "Budget Added", Toast.LENGTH_SHORT).show();

                // Logic to save the data (e.g., to a database) can be added here
            }
        });
    }

    private boolean validateInputs() {
        // Check budget input
        if (budgetEditText.getText().toString().trim().isEmpty()) {
            budgetEditText.setError("Budget is required");
            budgetEditText.requestFocus();
            return false;
        }

        // Check if a category is selected in the RadioGroup
        int selectedRadioId = categoryRadioGroup.getCheckedRadioButtonId();
        if (selectedRadioId == -1) {
            // No category selected
            Log.d("BudgetPlanning", "No category selected. RadioGroup ID: " + categoryRadioGroup.getId());
            Toast.makeText(this, "Please select a category", Toast.LENGTH_SHORT).show();
            return false; // Stop further validation
        } else {
            Log.d("BudgetPlanning", "Selected category ID: " + selectedRadioId);
        }

        // Validate the EditText associated with the selected category
        EditText selectedCategoryEditText = null;
        if (selectedRadioId == R.id.travelRadioButton) {
            selectedCategoryEditText = travelEditText;
        } else if (selectedRadioId == R.id.foodRadioButton) {
            selectedCategoryEditText = foodEditText;
        } else if (selectedRadioId == R.id.accommodationRadioButton) {
            selectedCategoryEditText = accommodationEditText;
        }

        // Check if the category-specific EditText is filled
        if (selectedCategoryEditText != null && selectedCategoryEditText.getText().toString().trim().isEmpty()) {
            selectedCategoryEditText.setError("Please enter details for the selected category");
            selectedCategoryEditText.requestFocus();
            return false;
        }

        return true;
    }

    private String getSelectedCategory() {
        int selectedRadioId = categoryRadioGroup.getCheckedRadioButtonId();
        if (selectedRadioId == R.id.travelRadioButton) {
            return "Travel";
        } else if (selectedRadioId == R.id.foodRadioButton) {
            return "Food";
        } else if (selectedRadioId == R.id.accommodationRadioButton) {
            return "Accommodation";
        }
        return "Unknown";
    }
}
