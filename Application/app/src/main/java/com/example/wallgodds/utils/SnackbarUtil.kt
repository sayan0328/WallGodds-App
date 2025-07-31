package com.example.wallgodds.utils

import android.content.Context
import android.util.Log


// helper function to make sure snackbar appears only after the first install
fun hasShownSnackbar(context: Context): Boolean {
	val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
	val result = prefs.getBoolean("snackbar_shown", false)
	return result
}

fun markSnackbarShown(context: Context) {
	val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
	prefs.edit().putBoolean("snackbar_shown", true).apply()
}