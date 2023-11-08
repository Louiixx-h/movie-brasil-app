// Generated by view binder compiler. Do not edit!
package com.luislabs.search.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.luislabs.search.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentSearchBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout contentHome;

  @NonNull
  public final RecyclerView rvMovies;

  @NonNull
  public final Group searchContentGroup;

  @NonNull
  public final EditText searchMovie;

  @NonNull
  public final ShimmerFrameLayout searchShimmerContainer;

  @NonNull
  public final View shimmerMovieGenres1;

  @NonNull
  public final View shimmerMovieGenres2;

  @NonNull
  public final View shimmerMovieGenres3;

  @NonNull
  public final View shimmerMovieGenres4;

  @NonNull
  public final View shimmerMovieGenres5;

  @NonNull
  public final View shimmerMovieGenres6;

  @NonNull
  public final View shimmerMovieGenres7;

  @NonNull
  public final View shimmerMovieGenres8;

  @NonNull
  public final View shimmerMovieSearch;

  private FragmentSearchBinding(@NonNull ConstraintLayout rootView,
      @NonNull ConstraintLayout contentHome, @NonNull RecyclerView rvMovies,
      @NonNull Group searchContentGroup, @NonNull EditText searchMovie,
      @NonNull ShimmerFrameLayout searchShimmerContainer, @NonNull View shimmerMovieGenres1,
      @NonNull View shimmerMovieGenres2, @NonNull View shimmerMovieGenres3,
      @NonNull View shimmerMovieGenres4, @NonNull View shimmerMovieGenres5,
      @NonNull View shimmerMovieGenres6, @NonNull View shimmerMovieGenres7,
      @NonNull View shimmerMovieGenres8, @NonNull View shimmerMovieSearch) {
    this.rootView = rootView;
    this.contentHome = contentHome;
    this.rvMovies = rvMovies;
    this.searchContentGroup = searchContentGroup;
    this.searchMovie = searchMovie;
    this.searchShimmerContainer = searchShimmerContainer;
    this.shimmerMovieGenres1 = shimmerMovieGenres1;
    this.shimmerMovieGenres2 = shimmerMovieGenres2;
    this.shimmerMovieGenres3 = shimmerMovieGenres3;
    this.shimmerMovieGenres4 = shimmerMovieGenres4;
    this.shimmerMovieGenres5 = shimmerMovieGenres5;
    this.shimmerMovieGenres6 = shimmerMovieGenres6;
    this.shimmerMovieGenres7 = shimmerMovieGenres7;
    this.shimmerMovieGenres8 = shimmerMovieGenres8;
    this.shimmerMovieSearch = shimmerMovieSearch;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentSearchBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentSearchBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_search, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentSearchBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      ConstraintLayout contentHome = (ConstraintLayout) rootView;

      id = R.id.rv_movies;
      RecyclerView rvMovies = ViewBindings.findChildViewById(rootView, id);
      if (rvMovies == null) {
        break missingId;
      }

      id = R.id.searchContentGroup;
      Group searchContentGroup = ViewBindings.findChildViewById(rootView, id);
      if (searchContentGroup == null) {
        break missingId;
      }

      id = R.id.search_movie;
      EditText searchMovie = ViewBindings.findChildViewById(rootView, id);
      if (searchMovie == null) {
        break missingId;
      }

      id = R.id.searchShimmerContainer;
      ShimmerFrameLayout searchShimmerContainer = ViewBindings.findChildViewById(rootView, id);
      if (searchShimmerContainer == null) {
        break missingId;
      }

      id = R.id.shimmerMovieGenres1;
      View shimmerMovieGenres1 = ViewBindings.findChildViewById(rootView, id);
      if (shimmerMovieGenres1 == null) {
        break missingId;
      }

      id = R.id.shimmerMovieGenres2;
      View shimmerMovieGenres2 = ViewBindings.findChildViewById(rootView, id);
      if (shimmerMovieGenres2 == null) {
        break missingId;
      }

      id = R.id.shimmerMovieGenres3;
      View shimmerMovieGenres3 = ViewBindings.findChildViewById(rootView, id);
      if (shimmerMovieGenres3 == null) {
        break missingId;
      }

      id = R.id.shimmerMovieGenres4;
      View shimmerMovieGenres4 = ViewBindings.findChildViewById(rootView, id);
      if (shimmerMovieGenres4 == null) {
        break missingId;
      }

      id = R.id.shimmerMovieGenres5;
      View shimmerMovieGenres5 = ViewBindings.findChildViewById(rootView, id);
      if (shimmerMovieGenres5 == null) {
        break missingId;
      }

      id = R.id.shimmerMovieGenres6;
      View shimmerMovieGenres6 = ViewBindings.findChildViewById(rootView, id);
      if (shimmerMovieGenres6 == null) {
        break missingId;
      }

      id = R.id.shimmerMovieGenres7;
      View shimmerMovieGenres7 = ViewBindings.findChildViewById(rootView, id);
      if (shimmerMovieGenres7 == null) {
        break missingId;
      }

      id = R.id.shimmerMovieGenres8;
      View shimmerMovieGenres8 = ViewBindings.findChildViewById(rootView, id);
      if (shimmerMovieGenres8 == null) {
        break missingId;
      }

      id = R.id.shimmerMovieSearch;
      View shimmerMovieSearch = ViewBindings.findChildViewById(rootView, id);
      if (shimmerMovieSearch == null) {
        break missingId;
      }

      return new FragmentSearchBinding((ConstraintLayout) rootView, contentHome, rvMovies,
          searchContentGroup, searchMovie, searchShimmerContainer, shimmerMovieGenres1,
          shimmerMovieGenres2, shimmerMovieGenres3, shimmerMovieGenres4, shimmerMovieGenres5,
          shimmerMovieGenres6, shimmerMovieGenres7, shimmerMovieGenres8, shimmerMovieSearch);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
