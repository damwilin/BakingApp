package com.wili.android.bakingapp.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.wili.android.bakingapp.R;
import com.wili.android.bakingapp.activity.MViewModel;
import com.wili.android.bakingapp.activity.detail.DetailViewModel;
import com.wili.android.bakingapp.activity.step.RecipeStepViewModel;
import com.wili.android.bakingapp.utils.Utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class RecipeStepDetailFragment extends Fragment {
    private final static String TAG = RecipeStepDetailFragment.class.getSimpleName();
    private View rootView;
    private int currentWindow;
    private SimpleExoPlayer exoPlayer;
    private SimpleExoPlayerView exoPlayerView;
    private MediaSessionCompat mediaSession;
    private TextView stepInstruction;
    private Long videoPosition;
    private static final String VIDEO_POSITION = "videoPosition";
    private static final String VIDEO_WINDOW = "videoWindow";

    private MViewModel viewModel;

    public static RecipeStepDetailFragment getNewInstance() {
        RecipeStepDetailFragment fragment = new RecipeStepDetailFragment();
        return fragment;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            videoPosition = savedInstanceState.getLong(VIDEO_POSITION);
            currentWindow = savedInstanceState.getInt(VIDEO_WINDOW);
            Log.d(RecipeStepDetailFragment.class.getSimpleName(), "Video Position after: " + videoPosition);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_recipe_step_detail, container, false);
        exoPlayerView = rootView.findViewById(R.id.exoplayer_step_detail);
        stepInstruction = rootView.findViewById(R.id.step_instruction);
        if (Utils.isTablet(getActivity())) {
            viewModel = ViewModelProviders.of(getActivity()).get(DetailViewModel.class);
        } else {
            viewModel = ViewModelProviders.of(getActivity()).get(RecipeStepViewModel.class);
        }
        setLandscapeMode();
        if (viewModel.getStep() != null) {
            initializeMediaSession();
            initializePlayer(viewModel.getStep().getVideoURL());
            setInstruction();
        }
        return rootView;
    }

    private void initializeMediaSession() {
        mediaSession = new MediaSessionCompat(getContext(), TAG);
        mediaSession.setActive(true);
    }

    public void initializePlayer(String mediaUrl) {
        if (mediaUrl != null && !mediaUrl.isEmpty() && !mediaUrl.equals("")) {
            Uri uri = Uri.parse(mediaUrl);
            if (exoPlayer == null) {
                TrackSelector trackSelector = new DefaultTrackSelector();
                LoadControl loadControl = new DefaultLoadControl();
                exoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
                exoPlayerView.setPlayer(exoPlayer);
                String userAgent = Util.getUserAgent(getContext(), "RecipeVideo");
                MediaSource mediaSource = new ExtractorMediaSource(uri, new DefaultDataSourceFactory(getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
                exoPlayer.prepare(mediaSource);
                if (videoPosition != null)
                    exoPlayer.seekTo(currentWindow, videoPosition);
            }
        }
    }

    private void releasePlayer() {
        if (exoPlayer != null) {
            exoPlayer.release();
            currentWindow = exoPlayer.getCurrentWindowIndex();
            videoPosition = exoPlayer.getCurrentPosition();
            exoPlayer = null;
        }
    }

    private void setInstruction() {
        stepInstruction.setText(viewModel.getStep().getDescription());
        Log.d(TAG, "Step description: " + viewModel.getStep().getShortDescription());
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void setLandscapeMode() {
        if (Utils.isLandscape(getActivity())) {
            exoPlayerView.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
            exoPlayerView.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (exoPlayer != null) {
            videoPosition = exoPlayer.getCurrentPosition();
            outState.putLong(VIDEO_POSITION, videoPosition);
            currentWindow = exoPlayer.getCurrentWindowIndex();
            outState.putInt(VIDEO_WINDOW, currentWindow);
            Log.d(RecipeStepDetailFragment.class.getSimpleName(), "Video Position before: " + videoPosition);
        }
    }
}
