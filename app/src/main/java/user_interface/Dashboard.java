package user_interface;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import user_financial_management.Budget_Planning;
import user_financial_management.Category;
import user_financial_management.Expense;
import com.eldroid.pennywise.R;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        //To budget planning
        ImageView toBudget = findViewById(R.id.budget);
        toBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Dashboard.this, Budget_Planning.class);
                startActivity(intent);
            }
        });

        //To expense
        ImageView toExpense = findViewById(R.id.expense);
        toExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, Expense.class);
                startActivity(intent);
            }
        });

        //To Category
        ImageView toCategory = findViewById(R.id.category);
        toCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, Category.class);
                startActivity(intent);
            }
        });

        // Find the ImageView and set the click listener
        ImageView toSettings = findViewById(R.id.settings);
        if (toSettings != null) {
            toSettings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Dashboard.this, Settings.class);
                    startActivity(intent);
                }
            });
        } else {
            Log.e("Dashboard", "ImageView with ID 'settings' not found in layout");
        }

        //End
    }
}