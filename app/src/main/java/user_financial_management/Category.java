package user_financial_management;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.eldroid.pennywise.R;

public class Category extends AppCompatActivity {

    // Variables to track icon selection
    private boolean isAnyIconSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // Back Button
        LinearLayout backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // EditText fields and Submit Button
        EditText categoryEditText = findViewById(R.id.categoryEditText);
        EditText descriptionEdit = findViewById(R.id.descriptionEdit);
        Button submitBtn = findViewById(R.id.submitBtn);

        // Icon buttons
        ImageButton travelIcon = findViewById(R.id.travel_Icon);
        ImageButton foodIcon = findViewById(R.id.food_Icon);
        ImageButton accommodationIcon = findViewById(R.id.accommodation_Icon);
        ImageButton bookIcon = findViewById(R.id.book_Icon);
        ImageButton docsIcon = findViewById(R.id.docs_Icon);

        // Icon selection logic
        View.OnClickListener iconClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAnyIconSelected = true;
            }
        };

        travelIcon.setOnClickListener(iconClickListener);
        foodIcon.setOnClickListener(iconClickListener);
        accommodationIcon.setOnClickListener(iconClickListener);
        bookIcon.setOnClickListener(iconClickListener);
        docsIcon.setOnClickListener(iconClickListener);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String categoryText = categoryEditText.getText().toString().trim();
                String descriptionText = descriptionEdit.getText().toString().trim();

                if (categoryText.isEmpty()) {
                    // Show error for empty category
                    categoryEditText.setError("Category is required");
                    categoryEditText.requestFocus();
                } else if (descriptionText.isEmpty()) {
                    // Show error for empty description
                    descriptionEdit.setError("Description is required");
                    descriptionEdit.requestFocus();
                } else if (!isAnyIconSelected) {
                    Toast.makeText(Category.this, "Choose icon", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
