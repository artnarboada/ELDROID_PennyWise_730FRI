package user_financial_management;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eldroid.pennywise.R;

import java.util.List;

import Models.ExpenseData;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {

    private List<ExpenseData> expenseList;

    public ExpenseAdapter(List<ExpenseData> expenseList) {
        this.expenseList = expenseList;
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_list_card, parent, false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        ExpenseData expense = expenseList.get(position);
        holder.nameTextView.setText(expense.getExpenseName());

        // Format the amount to remove the decimal part
        holder.amountTextView.setText(String.format("%.0f", expense.getExpenseAmount()));

        // Fetch and set the category name based on category ID
        String categoryName = getCategoryName(expense.getCategoryID());
        holder.categoryTextView.setText(categoryName);
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    private String getCategoryName(int categoryID) {
        switch (categoryID) {
            case 1:
                return "Travel";
            case 2:
                return "Food";
            case 3:
                return "Accommodation";
            default:
                return "Other";
        }
    }

    static class ExpenseViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView amountTextView;
        TextView categoryTextView;

        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            amountTextView = itemView.findViewById(R.id.amountTextView);
            categoryTextView = itemView.findViewById(R.id.categoryTextView);
        }
    }
}
