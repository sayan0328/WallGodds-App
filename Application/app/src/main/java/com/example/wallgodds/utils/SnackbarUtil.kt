package com.example.wallgodds.utils

import android.content.Context

enum class SnackBarType {
	Home,
	Upload
}

// helper function to make sure snackbar appears only after the first install
fun hasShownSnackbar(context: Context, type: SnackBarType = SnackBarType.Home): Boolean {
	val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
	val typeName = type.name.lowercase()
	val result = prefs.getBoolean("${typeName}_snackbar_shown", false)
	return result
}

fun markSnackbarShown(context: Context, type: SnackBarType = SnackBarType.Home) {
	val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
	val typeName = type.name.lowercase()
	prefs.edit().putBoolean("${typeName}_snackbar_shown", true).apply()
}