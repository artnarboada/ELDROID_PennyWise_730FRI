package API;

import android.util.Log; // Import logging utility
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String TAG = "RetrofitClient";
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            Log.e(TAG, "Creating new Retrofit instance"); // Log when instance is created
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8000/api/")  // Correct base URL
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        } else {
            Log.e(TAG, "Using existing Retrofit instance"); // Log when reusing instance
        }
        return retrofit;
    }
}
