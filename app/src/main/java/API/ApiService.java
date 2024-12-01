package API;

import java.util.List;

import Models.BudgetData;
import Models.BudgetResponse;
import Models.CategoryData;
import Models.ExpenseData;
import Models.ExpenseResponse;
import Models.LoginRequest;
import Models.User;
import Models.UserResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import user_financial_management.Category;

public interface ApiService {
    @POST("register")
    Call<UserResponse> registerUser(@Body User user);

    @POST("login")
    Call<UserResponse> login(@Body LoginRequest loginRequest);

    @POST("categories")
    Call<Void> createCategory(@Body CategoryData categoryData);

    @POST("budgets")
    Call<BudgetResponse> createBudget(@Body BudgetData budgetData);

    @POST("expenses")
    Call<ExpenseResponse> createExpense(@Body ExpenseData expenseData);

    @GET("budgets") // Ensure this matches your API route
    Call<List<BudgetData>> getBudgets();

    @GET("expenses") // Ensure this matches your API route
    Call<List<ExpenseData>> getExpenses();

//    @GET("verify-email")
//    Call<GenericResponse> verifyEmail(@Body VerifyEmailRequest verifyEmailRequest);
//
//    @GET("users/{id}")
//    Call<UserResponse> getUser(@Path("id") int id);
}
