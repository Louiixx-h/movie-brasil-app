// Generated by view binder compiler. Do not edit!
package com.luislabs.details.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.luislabs.details.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityDetailsBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final FragmentContainerView fragmentContainer;

  @NonNull
  public final Toolbar toolbarMainDetails;

  private ActivityDetailsBinding(@NonNull ConstraintLayout rootView,
      @NonNull FragmentContainerView fragmentContainer, @NonNull Toolbar toolbarMainDetails) {
    this.rootView = rootView;
    this.fragmentContainer = fragmentContainer;
    this.toolbarMainDetails = toolbarMainDetails;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDetailsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDetailsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_details, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDetailsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.fragmentContainer;
      FragmentContainerView fragmentContainer = ViewBindings.findChildViewById(rootView, id);
      if (fragmentContainer == null) {
        break missingId;
      }

      id = R.id.toolbar_main_details;
      Toolbar toolbarMainDetails = ViewBindings.findChildViewById(rootView, id);
      if (toolbarMainDetails == null) {
        break missingId;
      }

      return new ActivityDetailsBinding((ConstraintLayout) rootView, fragmentContainer,
          toolbarMainDetails);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}