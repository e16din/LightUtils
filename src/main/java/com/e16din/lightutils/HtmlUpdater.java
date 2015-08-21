package com.e16din.lightutils;

import android.app.Activity;
import android.text.Html;
import android.widget.TextView;

public class HtmlUpdater {

	public HtmlUpdater(TextView tv) {
		if (tv != null)
			tv.setText(Html.fromHtml(tv.getText().toString()));
	}

	public HtmlUpdater(Activity activity, int tvId) {
		TextView tv = (TextView) activity.findViewById(tvId);
		if (tv != null)
			tv.setText(Html.fromHtml(tv.getText().toString()));
	}
}
