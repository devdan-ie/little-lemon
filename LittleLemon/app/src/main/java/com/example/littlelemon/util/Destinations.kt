package com.example.littlelemon.util

interface Destinations {
    val onboarding: String
    val home: String
    val profile: String
}

object AppDestinations : Destinations {
    override val onboarding = "onboarding"
    override val home = "home"
    override val profile = "profile"
}