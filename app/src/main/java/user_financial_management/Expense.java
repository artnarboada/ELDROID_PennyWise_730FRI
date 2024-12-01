package user_financial_management;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import com.eldroid.pennywise.R;

import androidx.appcompat.app.AppCompatActivity;

import Models.ExpenseData;
import API.ApiService;
import API.RetrofitClient;
import Models.ExpenseResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import user_interface.DashboardFragment;

public class Expense extends AppCompatActivity {

    private EditText expenseNameEditText, amountEditText;
    private Spinner categorySpinner;
    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        // Initialize views
        expenseNameEditText = findViewById(R.id.expenseName);
        amountEditText = findViewById(R.id.amountEdit);
        categorySpinner = findViewById(R.id.categorySpinner);
        submitBtn = findViewById(R.id.submitBtn);
        LinearLayout backBtn = findViewById(R.id.backBtn);

        // Set up the category spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.dropdown_options, // Assuming the options are defined in strings.xml
                R.layout.spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        // Back button functionality
        backBtn.setOnClickListener(view -> finish());

        // Submit button click listener
        submitBtn.setOnClickListener(view -> {
            if (validateInputs()) {
                String expenseName = expenseNameEditText.getText().toString().trim();
                String category = categorySpinner.getSelectedItem().toString();
                String amount = amountEditText.getText().toString().trim();

                // Assuming userID and categoryID are already known
                int userID = 1; // Replace with actual user ID
                int categoryID = getCategoryID(category);
                double expenseAmount = Double.parseDouble(amount);

                ExpenseData expenseData = new ExpenseData(userID, categoryID, expenseName, expenseAmount, ""); // Optional description

                // Show progress dialog
                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Submitting expense...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                // API call to create the expense
                ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
                Call<ExpenseResponse> call = apiService.createExpense(expenseData);

                call.enqueue(new Callback<ExpenseResponse>() {
                    @Override
                    public void onResponse(Call<ExpenseResponse> call, Response<ExpenseResponse> response) {
                        progressDialog.dismiss();
                        if (response.isSuccessful()) {
                            Toast.makeText(Expense.this, "Expense Created Successfully", Toast.LENGTH_SHORT).show();
                            // Navigate to Dashboard
                            Intent intent = new Intent(Expense.this, DashboardFragment.class);
                            startActivity(intent);
                            finish(); // Optional: finish this activity
                        } else {
                            Toast.makeText(Expense.this, "Failed to create expense", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ExpenseResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(Expense.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private boolean validateInputs() {
        // Check if expense name is entered
        if (expenseNameEditText.getText().toString().trim().isEmpty()) {
            expenseNameEditText.setError("Expense name is required");
            expenseNameEditText.requestFocus();
            return false;
        }

        // Check if a category is selected
        if (categorySpinner.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Please select a category", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Check if amount is entered
        if (amountEditText.getText().toString().trim().isEmpty()) {
            amountEditText.setError("Amount is required");
            amountEditText.requestFocus();
            return false;
        }

        // check if the user entered a valid amount
        try {
            Double.parseDouble(amountEditText.getText().toString().trim());
        } catch (NumberFormatException e) {
            amountEditText.setError("Please enter a valid amount");
            amountEditText.requestFocus();
            return false;
        }

        return true;
    }
    private int getCategoryID(String category) {
        if (category.equals("Travel")) {
            return 1;
        } else if (category.equals("Food")) {
            return 2;
        } else {
            return 0; // Default category ID
        }
    }
}
