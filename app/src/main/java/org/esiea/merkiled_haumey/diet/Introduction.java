package org.esiea.merkiled_haumey.diet;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class Introduction extends AppIntro2 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();
        // permet de cacher la barre la status bar
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest.
        addSlide(AppIntroFragment.newInstance(Introduction.this.getString(R.string.onglet1), Introduction.this.getString(R.string.intro_1_description), R.drawable.ecran_1, Color.parseColor("#03A9F4")));
        addSlide(AppIntroFragment.newInstance(Introduction.this.getString(R.string.onglet2), Introduction.this.getString(R.string.intro_2_description), R.drawable.ecran_2, Color.parseColor("#00BFA5")));
        addSlide(AppIntroFragment.newInstance(Introduction.this.getString(R.string.onglet3), Introduction.this.getString(R.string.intro_3_description), R.drawable.ecran_3, Color.parseColor("#FF4081")));

        setZoomAnimation();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
    }
}