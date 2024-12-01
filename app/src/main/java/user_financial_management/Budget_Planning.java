package user_financial_management;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
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

import API.ApiService;
import API.RetrofitClient;
import Models.BudgetData;
import Models.BudgetResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import user_interface.Dashboard;

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

                // Get the category ID based on the selected category
                int categoryID = getCategoryID(selectedCategory);

                // Convert the budget to a double
                double budgetLimit;
                try {
                    budgetLimit = Double.parseDouble(budget);
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Invalid budget value", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create a BudgetData object
                BudgetData budgetData = new BudgetData(1, categoryID, budgetLimit, notes); // Assuming userID = 1 for testing

                // Show progress dialog while making API call
                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Adding budget...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                // Call the API to create the budget
                ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
                Call<BudgetResponse> call = apiService.createBudget(budgetData);

                call.enqueue(new Callback<BudgetResponse>() {
                    @Override
                    public void onResponse(Call<BudgetResponse> call, Response<BudgetResponse> response) {
                        progressDialog.dismiss();
                        if (response.isSuccessful()) {
                            BudgetResponse budgetResponse = response.body();
                            if (budgetResponse != null && budgetResponse.getMessage().equals("Budget created successfully")) {
                                Toast.makeText(Budget_Planning.this, "Budget Added Successfully", Toast.LENGTH_SHORT).show();

                                // Navigate to Dashboard after successful budget addition
                                Intent intent = new Intent(Budget_Planning.this, Dashboard.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Budget_Planning.this, "Failed to add budget", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Budget_Planning.this, "Failed to add budget", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BudgetResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(Budget_Planning.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
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
            Toast.makeText(this, "Please select a category", Toast.LENGTH_SHORT).show();
            return false; // Stop further validation
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

    private int getCategoryID(String category) {
        // Use if-else to map category name to categoryID
        if (category.equals("Travel")) {
            return 1;
        } else if (category.equals("Food")) {
            return 2;
        } else if (category.equals("Accommodation")) {
            return 3;
        } else {
            return 0; // Default ID (should be handled)
        }
    }
}
