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
import API.RetrofitClient;
import API.ApiService;
import Models.CategoryData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Category extends AppCompatActivity {

    private boolean isAnyIconSelected = false;
    private String selectedIcon = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // Back Button
        LinearLayout backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(view -> finish());

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

        // Icon selection logic using if-else
        View.OnClickListener iconClickListener = view -> {
            isAnyIconSelected = true;
            if (view.getId() == R.id.travel_Icon) {
                selectedIcon = "travel";
            } else if (view.getId() == R.id.food_Icon) {
                selectedIcon = "food";
            } else if (view.getId() == R.id.accommodation_Icon) {
                selectedIcon = "accommodation";
            } else if (view.getId() == R.id.book_Icon) {
                selectedIcon = "book";
            } else if (view.getId() == R.id.docs_Icon) {
                selectedIcon = "docs";
            }
        };

        travelIcon.setOnClickListener(iconClickListener);
        foodIcon.setOnClickListener(iconClickListener);
        accommodationIcon.setOnClickListener(iconClickListener);
        bookIcon.setOnClickListener(iconClickListener);
        docsIcon.setOnClickListener(iconClickListener);

        // Submit Button Click Listener
        submitBtn.setOnClickListener(view -> {
            String categoryText = categoryEditText.getText().toString().trim();
            String descriptionText = descriptionEdit.getText().toString().trim();

            if (categoryText.isEmpty()) {
                categoryEditText.setError("Category is required");
                categoryEditText.requestFocus();
            } else if (descriptionText.isEmpty()) {
                descriptionEdit.setError("Description is required");
                descriptionEdit.requestFocus();
            } else if (!isAnyIconSelected) {
                Toast.makeText(Category.this, "Choose icon", Toast.LENGTH_SHORT).show();
            } else {
                // Create category data object
                CategoryData categoryData = new CategoryData(categoryText, descriptionText, selectedIcon);

                // Call the API
                ApiService categoryApi = RetrofitClient.getRetrofitInstance().create(ApiService.class);
                Call<Void> call = categoryApi.createCategory(categoryData);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(Category.this, "Category created successfully!", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            String errorMessage = "Error: " + response.code();
                            Toast.makeText(Category.this, errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(Category.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
