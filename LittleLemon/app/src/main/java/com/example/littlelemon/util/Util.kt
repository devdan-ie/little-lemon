package com.example.littlelemon.util


fun String.isValidEmail(): Boolean {
    val emailRegex = Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
    return matches(emailRegex)
}

