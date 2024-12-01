package user_interface;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import API.ApiService;
import API.RetrofitClient;
import Models.BudgetData;
import Models.ExpenseData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import user_financial_management.Budget_Planning;
import user_financial_management.Category;
import user_financial_management.Expense;
import user_financial_management.ExpenseAdapter;

import com.eldroid.pennywise.R;

public class DashboardFragment extends Fragment {

    private RecyclerView recyclerView;
    private ExpenseAdapter expenseAdapter;
    private TextView budgetSummary; // Declare the TextView for budget summary
    private double totalBudgetLimit = 0.0; // Variable to hold the total budget limit
    private double totalExpense = 0.0; // Variable to hold the total expenses

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_dashboard, container, false);

        recyclerView = view.findViewById(R.id.expense_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        budgetSummary = view.findViewById(R.id.budgetSummary); // Initialize the budget summary TextView

        // Fetch expenses and budgets
        fetchBudgets();
        fetchExpenses();

        // Navigation buttons
        setupNavigationButtons(view);

        return view;
    }

    private void setupNavigationButtons(View view) {
        ImageView toBudget = view.findViewById(R.id.budget);
        toBudget.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), Budget_Planning.class);
            startActivity(intent);
        });

        ImageView toExpense = view.findViewById(R.id.expense);
        toExpense.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), Expense.class);
            startActivity(intent);
        });

        ImageView toCategory = view.findViewById(R.id.category);
        toCategory.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), Category.class);
            startActivity(intent);
        });

        ImageView toSettings = view.findViewById(R.id.settings);
        if (toSettings != null) {
            toSettings.setOnClickListener(v -> {
                Intent intent = new Intent(requireContext(), SettingsFragment.class);
                startActivity(intent);
            });
        } else {
            Log.e("Dashboard", "ImageView with ID 'settings' not found in layout");
        }
    }

    private void fetchExpenses() {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<List<ExpenseData>> call = apiService.getExpenses();

        call.enqueue(new Callback<List<ExpenseData>>() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onResponse(Call<List<ExpenseData>> call, Response<List<ExpenseData>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ExpenseData> expenses = response.body();
                    expenseAdapter = new ExpenseAdapter(expenses);
                    recyclerView.setAdapter(expenseAdapter);

                    // Calculate the total expense amount
                    totalExpense = 0.0;
                    for (ExpenseData expense : expenses) {
                        totalExpense += expense.getExpenseAmount(); // Sum up the expense amounts
                    }

                    // Update the budget summary after fetching expenses
                    updateBudgetSummary();
                } else {
                    Toast.makeText(requireContext(), "Failed to fetch expenses", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ExpenseData>> call, Throwable t) {
                Toast.makeText(requireContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Dashboard", "Error fetching expenses", t);
            }
        });
    }

    private void fetchBudgets() {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<List<BudgetData>> call = apiService.getBudgets(); // Ensure this endpoint exists

        call.enqueue(new Callback<List<BudgetData>>() {
            @Override
            public void onResponse(Call<List<BudgetData>> call, Response<List<BudgetData>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<BudgetData> budgets = response.body();

                    // Calculate the total budget limit
                    totalBudgetLimit = 0.0;
                    for (BudgetData budget : budgets) {
                        totalBudgetLimit += budget.getBudgetLimit();
                    }

                    // Update the budget summary TextView
                    updateBudgetSummary();
                } else {
                    Toast.makeText(requireContext(), "Failed to fetch budgets", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<BudgetData>> call, Throwable t) {
                Toast.makeText(requireContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Dashboard", "Error fetching budgets", t);
            }
        });
    }

    private void updateBudgetSummary() {
        // Calculate the remaining budget after expenses
        double remainingBudget = totalBudgetLimit - totalExpense;

        // Update the budget summary TextView
        budgetSummary.setText(String.format("P%.2f", remainingBudget));
    }

    private void addExpense(double expenseAmount) {
        // Your API call to add the expense...

        // After successful addition of expense
        fetchExpenses(); // Refresh expenses to reflect the new expense
        fetchBudgets(); // Refresh budget to reflect the new expense
        updateBudgetSummary(); // Update the budget summary
    }
}
