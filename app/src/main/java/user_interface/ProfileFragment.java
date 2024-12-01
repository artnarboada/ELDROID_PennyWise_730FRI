package user_interface;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.eldroid.pennywise.R;

public class ProfileFragment extends Fragment {

    private ImageView profilePicture;
    private TextView profileName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_profile, container, false);

        profilePicture = view.findViewById(R.id.profile_picture);
        profileName = view.findViewById(R.id.profile_name);

        view.findViewById(R.id.edit_icon).setOnClickListener(v -> showAvatarDialog());

        view.findViewById(R.id.edit_profile_button).setOnClickListener(v -> showEditNameDialog());

        return view;
    }

    private void showAvatarDialog() {
        int[] avatars = {
                R.drawable.avatar_a,
                R.drawable.avatar_b,
                R.drawable.avatar_c
        };

        String[] avatarNames = {"Avatar A", "Avatar B", "Avatar C"};

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Choose Avatar");

        builder.setItems(avatarNames, (dialog, which) -> {
            profilePicture.setImageResource(avatars[which]); // Change avatar
            Toast.makeText(requireContext(), avatarNames[which] + " selected!", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Cancel", null);
        builder.create().show();
    }

    private void showEditNameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Edit Name");

        final EditText input = new EditText(requireContext());
        input.setText(profileName.getText().toString()); // Pre-fill current name
        builder.setView(input);

        builder.setPositiveButton("Save", (dialog, which) -> {
            String newName = input.getText().toString().trim();
            if (!newName.isEmpty()) {
                profileName.setText(newName); // Update name
                Toast.makeText(requireContext(), "Name updated!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Name cannot be empty.", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", null);
        builder.create().show();
    }
}
