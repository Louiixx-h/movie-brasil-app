// Generated by view binder compiler. Do not edit!
package com.luislabs.arch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.luislabs.arch.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentErrorScreenBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button errorButton;

  @NonNull
  public final LinearLayout errorContainer;

  @NonNull
  public final TextView errorDescription;

  private FragmentErrorScreenBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button errorButton, @NonNull LinearLayout errorContainer,
      @NonNull TextView errorDescription) {
    this.rootView = rootView;
    this.errorButton = errorButton;
    this.errorContainer = errorContainer;
    this.errorDescription = errorDescription;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentErrorScreenBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentErrorScreenBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_error_screen, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentErrorScreenBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.error_button;
      Button errorButton = ViewBindings.findChildViewById(rootView, id);
      if (errorButton == null) {
        break missingId;
      }

      id = R.id.error_container;
      LinearLayout errorContainer = ViewBindings.findChildViewById(rootView, id);
      if (errorContainer == null) {
        break missingId;
      }

      id = R.id.error_description;
      TextView errorDescription = ViewBindings.findChildViewById(rootView, id);
      if (errorDescription == null) {
        break missingId;
      }

      return new FragmentErrorScreenBinding((ConstraintLayout) rootView, errorButton,
          errorContainer, errorDescription);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}