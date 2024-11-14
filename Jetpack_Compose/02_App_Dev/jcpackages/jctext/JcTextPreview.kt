package com.visionforgestudio.jcpackages.jctext

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JcTextPreview() {
	Scaffold(topBar = {
		TopAppBar(title = {
			Text("Jc Text Preview")
		})
	}) { contentPadding ->
		LazyColumn(
			modifier = Modifier
				.padding(contentPadding)
				.padding(16.dp)
				.fillMaxSize()
		) {
			item {
				JcText(text = "Jc Text")
				Spacer(modifier = Modifier.height(16.dp))
				JcTextDL(text = "Jc Text DL")
				Spacer(modifier = Modifier.height(16.dp))
				JcTextDM(text = "Jc Text DM")
				Spacer(modifier = Modifier.height(16.dp))
				JcTextDS(text = "Jc Text DS")
				Spacer(modifier = Modifier.height(16.dp))
				JcTextHL(text = "Jc Text HL")
				Spacer(modifier = Modifier.height(16.dp))
				JcTextHM(text = "Jc Text HM")
				Spacer(modifier = Modifier.height(16.dp))
				JcTextHS(text = "Jc Text HS")
				Spacer(modifier = Modifier.height(16.dp))
				JcTextTL(text = "Jc Text TL")
				Spacer(modifier = Modifier.height(16.dp))
				JcTextTM(text = "Jc Text TM")
				Spacer(modifier = Modifier.height(16.dp))
				JcTextTS(text = "Jc Text TS")
				Spacer(modifier = Modifier.height(16.dp))
				JcTextBL(text = "Jc Text BL")
				Spacer(modifier = Modifier.height(16.dp))
				JcTextBM(text = "Jc Text BM")
				Spacer(modifier = Modifier.height(16.dp))
				JcTextBS(text = "Jc Text BS")
				Spacer(modifier = Modifier.height(16.dp))
				JcTextLL(text = "Jc Text LL")
				Spacer(modifier = Modifier.height(16.dp))
				JcTextLM(text = "Jc Text LM")
				Spacer(modifier = Modifier.height(16.dp))
				JcTextLS(text = "Jc Text LS")
				Spacer(modifier = Modifier.height(16.dp))
			}
		}
	}
}