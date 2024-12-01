package user_interface;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.eldroid.pennywise.R;

public class SettingsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstaceState) {
        View view = inflater.inflate(R.layout.activity_settings, container, false);


        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView toHome = view.findViewById(R.id.settings);
        toHome.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), DashboardFragment.class);
            startActivity(intent);
        });

        return view;
    }
}