package com.e16din.lightutils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.e16din.lightutils.utils.U;

@Deprecated
public class BadgeSetter {

	private static final String ZERO = "0";

	private static final int BADGE_MARGIN = 8;
	private static final int BADGE_BACKGROUND_HEIGHT = 32;
	private static final int BADGE_COUNTER_FONT_SIZE = 12;

	private TextView tvBadge;
	private ImageView ivBadge;

	public BadgeSetter(Context context, View v, int bgResId) {
		ViewGroup vgParent = (ViewGroup) v.getParent();

		int position = 0;

		for (int i = 0; i < vgParent.getChildCount(); i++) {
			if (vgParent.getChildAt(i).equals(v)) {
				position = i;
				break;
			}
		}

		vgParent.removeView(v);

		RelativeLayout rlResult = new RelativeLayout(context);

		int height = /*
					 * (int) (context.getResources().getDimension(R.dimen.button_height) + Utils.dpToPx(context,
					 * BADGE_MARGIN * 2))
					 */ViewGroup.LayoutParams.WRAP_CONTENT;
		ViewGroup.LayoutParams paramsResult = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, height);
		rlResult.setLayoutParams(paramsResult);
		// rlResult.setBackgroundColor(context.getResources().getColor(android.R.color.black));

		// image view
		ivBadge = new ImageView(context);

		LayoutParams paramsBadge = new LayoutParams(U.dpToPx(context, BADGE_BACKGROUND_HEIGHT), U.dpToPx(
				context, BADGE_BACKGROUND_HEIGHT));
		// paramsBadge.addRule(RelativeLayout.CENTER_VERTICAL);
		paramsBadge.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

		paramsBadge.rightMargin = U.dpToPx(context, 20);
		paramsBadge.topMargin = U.dpToPx(context, 10);
		paramsBadge.bottomMargin = U.dpToPx(context, 10);

		ivBadge.setLayoutParams(paramsBadge);
		ivBadge.setImageResource(bgResId);

		// text view
		tvBadge = new TextView(context);
		LayoutParams paramsCounter = new LayoutParams(U.dpToPx(context, BADGE_BACKGROUND_HEIGHT), U.dpToPx(
				context, BADGE_BACKGROUND_HEIGHT));
		paramsCounter.addRule(RelativeLayout.CENTER_VERTICAL);
		paramsCounter.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

		tvBadge.setText(ZERO);
		tvBadge.setTextColor(context.getResources().getColor(android.R.color.white));
		tvBadge.setTextSize(U.dpToPx(context, BADGE_COUNTER_FONT_SIZE));
		tvBadge.setLayoutParams(paramsBadge);
		tvBadge.setGravity(Gravity.CENTER);

		rlResult.addView(v);

		LayoutParams paramsV = (LayoutParams) v.getLayoutParams();
		paramsV.addRule(RelativeLayout.CENTER_VERTICAL);
		v.setLayoutParams(paramsV);

		rlResult.addView(ivBadge);
		rlResult.addView(tvBadge);

		vgParent.addView(rlResult, position);
		updateVisibility();
	}

	public void setNumber(int number) {
		if (tvBadge != null)
			tvBadge.setText(number + "");

		updateVisibility();
	}

	public void setNumber(String number) {
		tvBadge.setText(number);
		updateVisibility();
	}

	private void updateVisibility() {
		if (tvBadge.getText().toString().equals(ZERO)) {
			tvBadge.setVisibility(View.GONE);
			ivBadge.setVisibility(View.GONE);
		} else {
			tvBadge.setVisibility(View.VISIBLE);
			ivBadge.setVisibility(View.VISIBLE);
		}
	}
}
