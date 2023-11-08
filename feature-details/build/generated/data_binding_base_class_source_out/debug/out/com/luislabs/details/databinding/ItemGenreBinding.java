// Generated by view binder compiler. Do not edit!
package com.luislabs.details.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.luislabs.details.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemGenreBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView nameGenre;

  private ItemGenreBinding(@NonNull ConstraintLayout rootView, @NonNull TextView nameGenre) {
    this.rootView = rootView;
    this.nameGenre = nameGenre;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemGenreBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemGenreBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_genre, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemGenreBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.name_genre;
      TextView nameGenre = ViewBindings.findChildViewById(rootView, id);
      if (nameGenre == null) {
        break missingId;
      }

      return new ItemGenreBinding((ConstraintLayout) rootView, nameGenre);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}