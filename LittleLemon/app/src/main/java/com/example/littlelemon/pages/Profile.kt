package com.example.littlelemon.pages


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.util.AppDestinations
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.Black
import com.example.littlelemon.ui.theme.LightGray
import com.example.littlelemon.ui.theme.Olive
import com.example.littlelemon.ui.theme.Yellow
import com.example.littlelemon.util.SharedPreferencesHelper
import com.example.littlelemon.util.User
import com.example.littlelemon.util.isValidEmail


@Composable
fun ProfileScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White),
    ) {
        Header(showHello = false)
        PersonalInformationForm2(navController)
    }
}


@Composable
fun PersonalInformationForm2(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(20.dp)) {

        var user =
            SharedPreferencesHelper.getUserFromSharedPreferences(navController.context)

        if (user == null) {
            user = User("", "", "")
        }


        var firstName = remember { mutableStateOf(user.firstName) }
        var lastName = remember { mutableStateOf(user.lastName) }
        var email = remember { mutableStateOf(user.email) }

        var isFirstNameError = remember { mutableStateOf(false) }
        var isLastNameError = remember { mutableStateOf(false) }
        var isEmailError = remember { mutableStateOf(false) }


        fun updateUser() {
            isFirstNameError.value = firstName.value.isBlank()
            isLastNameError.value = lastName.value.isBlank()
            isEmailError.value = !email.value.isValidEmail()

            val isValid = !isFirstNameError.value && !isLastNameError.value && !isEmailError.value
            if (isValid) {
                val user = User(firstName.value, lastName.value, email.value)
                SharedPreferencesHelper.saveUserAsJson(navController.context, user)
            }
        }

        Text(
            text = "Personal Information",
            color = Color.Black,
            textAlign = TextAlign.Left,
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, bottom = 10.dp)
        )

        OutlinedTextField(
            value = firstName.value,
            onValueChange = { firstName.value = it },
            label = { Text("First Name") },
            isError = isFirstNameError.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .onFocusChanged { focusState ->
                    if (!focusState.isFocused) {
                        updateUser()
                    }
                }
        )

        OutlinedTextField(
            value = lastName.value,
            onValueChange = { lastName.value = it },
            label = { Text("Last Name") },
            isError = isLastNameError.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .onFocusChanged { focusState ->
                    if (!focusState.isFocused) {
                        updateUser()
                    }
                }
        )

        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email Address") },
            isError = isEmailError.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .onFocusChanged { focusState ->
                                if (!focusState.isFocused) {
                                    updateUser()
                                }
                }
        )



        Button(
            onClick = {
                SharedPreferencesHelper.clearSharedPreferences(navController.context)
                navController.navigate(
                    AppDestinations.onboarding,
                    navOptions = NavOptions.Builder()
                        .setPopUpTo(AppDestinations.onboarding, true)
                        .build()
                )
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Yellow,
                contentColor = Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp)
        ) {
            Text(text = "Log Out")
        }

    }
}


@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = rememberNavController())
}