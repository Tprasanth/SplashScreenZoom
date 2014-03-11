package prasanth.developer.splashscreenzoom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;

public class SplashScreenActivity extends Activity implements AnimationListener {

	static int IMAGE_NUMBER = 1;

	RelativeLayout SpalshScreen_rLayout;
	Button button_Next;

	Animation animZoomIn, fadeIn, fadeOut;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		// setting screen on always while application running time
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		// Initializing all utilities
		SpalshScreen_rLayout = (RelativeLayout) findViewById(R.id.rLayout_Spalsh);
		button_Next = (Button) findViewById(R.id.button_nextActivity);

		animZoomIn = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
		fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
		fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);

		// listeners for animation effects..
		animZoomIn.setAnimationListener(this);
		fadeOut.setAnimationListener(this);
		fadeIn.setAnimationListener(this);

		// Setting RelativeLayout to zoom animation at starting
		SpalshScreen_rLayout.startAnimation(animZoomIn);

		button_Next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Starting Main_activity on click of the button NEXT
				Intent intent = new Intent(SplashScreenActivity.this,
						MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

	/*
	 * Check type of Animation effect is ended through AnimationListener Class
	 * 
	 * if it is zoom animation ******************** Type 1 *********************
	 * applying RelativeLayout to fade-out animation
	 * 
	 * if it is fade-out animation **************** Type 2 *********************
	 * then change background image and applying fade-in animation to
	 * RelativeLayout
	 * 
	 * if it is fade-in animation ***************** Type 3 *********************
	 * applying RelativeLayout to zoom animation
	 */

	@Override
	public void onAnimationEnd(Animation animation) {

		if (animation == animZoomIn) {
			SpalshScreen_rLayout.startAnimation(fadeOut);
		} else if (animation == fadeOut) {
			if (IMAGE_NUMBER == 1) {
				IMAGE_NUMBER = 2;
				SpalshScreen_rLayout.setBackgroundResource(R.drawable.background_two);
				SpalshScreen_rLayout.startAnimation(fadeIn);
			} else if (IMAGE_NUMBER == 2) {
				IMAGE_NUMBER = 3;
				SpalshScreen_rLayout.setBackgroundResource(R.drawable.background_three);
				SpalshScreen_rLayout.startAnimation(fadeIn);
			} else {
				IMAGE_NUMBER = 1;
				SpalshScreen_rLayout.setBackgroundResource(R.drawable.background_one);
				SpalshScreen_rLayout.startAnimation(fadeIn);
			}

		} else if (animation == fadeIn) {
			SpalshScreen_rLayout.startAnimation(animZoomIn);
		}
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
	}

	@Override
	public void onAnimationStart(Animation animation) {
	}

}
