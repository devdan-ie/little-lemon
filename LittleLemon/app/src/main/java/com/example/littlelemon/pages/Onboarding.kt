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
fun OnboardingScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White),
    ) {
        Header(showHello = true)
        PersonalInformationForm(navController)
    }
}



@Composable
fun Header(showHello: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .height(60.dp)
                .align(CenterHorizontally)

        )
        Spacer(modifier = Modifier.height(30.dp))

        if (showHello) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Olive)
            ) {
                Text(
                    text = "Lets get to know you!",
                    color = LightGray,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp, bottom = 40.dp)
                )
            }
        }
    }
}

@Composable
fun PersonalInformationForm(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(20.dp)) {

        var firstName = remember { mutableStateOf("") }
        var lastName = remember { mutableStateOf("") }
        var email = remember { mutableStateOf("") }

        var isFirstNameError = remember { mutableStateOf(false) }
        var isLastNameError = remember { mutableStateOf(false) }
        var isEmailError = remember { mutableStateOf(false) }

        fun validateForm(): Boolean {
            isFirstNameError.value = firstName.value.isBlank()
            isLastNameError.value = lastName.value.isBlank()
            isEmailError.value = !email.value.isValidEmail()

            return !isFirstNameError.value && !isLastNameError.value && !isEmailError.value
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
                        validateForm()
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
                        validateForm()
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
                        validateForm()
                    }
                }
        )



        Button(
            onClick = {
                val isValid = validateForm()
                if (isValid) {
                    val user = User(firstName.value, lastName.value, email.value)
                    SharedPreferencesHelper.saveUserAsJson(navController.context, user)
                    navController.navigate(AppDestinations.home)
                }

            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Yellow,
                contentColor = Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp)
        ) {
            Text(text = "Register")
        }

    }
}


@Preview
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen(navController = rememberNavController())
}