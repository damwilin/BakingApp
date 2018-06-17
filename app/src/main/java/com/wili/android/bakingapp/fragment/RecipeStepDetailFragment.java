package com.wili.android.bakingapp.fragment;

import android.content.res.Configuration;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayer;
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
import com.wili.android.bakingapp.data.models.Step;

public class RecipeStepDetailFragment extends Fragment {
    private final static String TAG = RecipeStepDetailFragment.class.getSimpleName();
    private View rootView;
    private int currentWindow;
    private Step step;
    private SimpleExoPlayer exoPlayer;
    private SimpleExoPlayerView exoPlayerView;
    private MediaSessionCompat mediaSession;
    private TextView stepInstruction;
    private Long videoPosition;
    private static final String VIDEO_POSITION = "videoPosition";
    private static final String VIDEO_WINDOW = "videoWindow";

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null){
            step = savedInstanceState.getParcelable(Step.STEP_KEY);
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
        setLandscapeMode();
        if(step != null){
            initializeMediaSession();
            initializePlayer(step.getVideoURL());
            setInstruction();
        }
        return rootView;
    }

    public void setStep(Step step) {
        this.step = step;
    }

    private void initializeMediaSession(){
        mediaSession = new MediaSessionCompat(getContext(),TAG);
        mediaSession.setActive(true);
    }

    private void initializePlayer(String mediaUrl){
        Uri uri = Uri.parse(mediaUrl);
        if (exoPlayer== null){
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            exoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            exoPlayerView.setPlayer(exoPlayer);
            String userAgent = Util.getUserAgent(getContext(), "RecipeVideo");
            MediaSource mediaSource = new ExtractorMediaSource(uri, new DefaultDataSourceFactory(getContext(), userAgent), new DefaultExtractorsFactory(), null,null);
            exoPlayer.prepare(mediaSource);
            if (videoPosition!= null)
                exoPlayer.seekTo(currentWindow, videoPosition);
        }
    }

    private void releasePlayer(){
        if (exoPlayer != null){
            exoPlayer.release();
            currentWindow = exoPlayer.getCurrentWindowIndex();
            videoPosition = exoPlayer.getCurrentPosition();
            exoPlayer = null;
        }
    }
    private void setInstruction(){
        stepInstruction.setText(step.getShortDescription());
        Log.d(TAG, "Step description: " + step.getShortDescription());
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

    private void setLandscapeMode(){
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            exoPlayerView.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
            exoPlayerView.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Step.STEP_KEY, step);
        if (exoPlayer != null){
            videoPosition = exoPlayer.getCurrentPosition();
            outState.putLong(VIDEO_POSITION, videoPosition);
            currentWindow = exoPlayer.getCurrentWindowIndex();
            outState.putInt(VIDEO_WINDOW, currentWindow);
            Log.d(RecipeStepDetailFragment.class.getSimpleName(), "Video Position before: " + videoPosition);
        }
    }
}
