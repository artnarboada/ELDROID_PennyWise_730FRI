package user_financial_management;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.eldroid.pennywise.R;

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

                // Logic to save the expense details (e.g., save to a database)
                Toast.makeText(this, "Expense Submitted", Toast.LENGTH_SHORT).show();
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
        if (categorySpinner.getSelectedItemPosition() == 0) { // Assuming the first item is a "Select" option
            Toast.makeText(this, "Please select a category", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Check if amount is entered
        if (amountEditText.getText().toString().trim().isEmpty()) {
            amountEditText.setError("Amount is required");
            amountEditText.requestFocus();
            return false;
        }

        // Optionally, you could also check if the entered amount is a valid number.
        try {
            Double.parseDouble(amountEditText.getText().toString().trim());
        } catch (NumberFormatException e) {
            amountEditText.setError("Please enter a valid amount");
            amountEditText.requestFocus();
            return false;
        }

        return true;
    }
}
