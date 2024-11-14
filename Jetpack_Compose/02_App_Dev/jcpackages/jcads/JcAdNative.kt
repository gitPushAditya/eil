package com.visionforgestudio.taskflow.jcpackages.jcads

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun JcAdNative(
	modifier: Modifier = Modifier,
	adUnitId: String,
){
	val context = LocalContext.current
	
	AndroidView(
		modifier = modifier.fillMaxWidth(),
		factory = {
			ctx: Context ->
			AdView(ctx).apply {
				setAdSize(AdSize.BANNER)
				this.adUnitId = adUnitId
				loadAd(AdRequest.Builder().build())
			}
		},
		update = { adView ->
			adView.loadAd(AdRequest.Builder().build())
		}
	)
}