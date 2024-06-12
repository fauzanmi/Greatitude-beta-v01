package dev.fathoor.greatitude.presentation.auth

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.fathoor.core.data.local.entity.user.LocalUserEntity
import dev.fathoor.core.domain.model.auth.UserRegister
import dev.fathoor.core.util.UIState
import dev.fathoor.core.util.convertMillisToDate
import dev.fathoor.greatitude.R
import dev.fathoor.greatitude.presentation.theme.FontPlusJakarta
import dev.fathoor.greatitude.util.authToast
import dev.fathoor.greatitude.util.validateEmail
import dev.fathoor.greatitude.util.validateInput

@Composable
fun RegisterContent(
    context: Context = LocalContext.current,
    stateRegister: UIState<LocalUserEntity>,
    session: Long,
    onSubmit: (user: UserRegister) -> Unit,
    navigateToMain: (session: Long) -> Unit,
    navigateToLogin: () -> Unit
) {
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let { convertMillisToDate(it) } ?: ""
    var isDateModalHidden by remember { mutableStateOf(true) }
    var dob by rememberSaveable { mutableStateOf("") }
    var isEmailError by rememberSaveable { mutableStateOf(false) }
    var isPasswordError by rememberSaveable { mutableStateOf(false) }
    var isPasswordHidden by rememberSaveable { mutableStateOf(true) }

    Scaffold(
        containerColor = Color(0xFFF0F8FE)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(100.dp))
                // Logo
                Spacer(Modifier.height(30.dp))
                Text(
                    text = "Create an Account",
                    style = TextStyle(
                        color = Color(0xFF000000),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 22.sp,
                        fontFamily = FontPlusJakarta
                    )
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    text = "Sign up now to get started with an account.",
                    style = TextStyle(
                        color = Color(0x80000000),
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        fontFamily = FontPlusJakarta
                    )
                )
                Spacer(Modifier.height(35.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { input: String ->
                            name = input
                        },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            fontFamily = FontPlusJakarta
                        ),
                        label = {
                            Text(
                                text = "Full Name",
                                style = TextStyle(
                                    color = Color(0xFF49454F),
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp,
                                    fontFamily = FontPlusJakarta
                                )
                            )
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(4.dp)
                    )
                }
                Spacer(Modifier.height(15.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = email,
                        onValueChange = { input: String ->
                            email = input
                            isEmailError = validateEmail(input)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            fontFamily = FontPlusJakarta
                        ),
                        label = {
                            Text(
                                text = "Email Address",
                                style = TextStyle(
                                    color = Color(0xFF49454F),
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp,
                                    fontFamily = FontPlusJakarta
                                )
                            )
                        },
                        isError = isEmailError,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        singleLine = true,
                        shape = RoundedCornerShape(4.dp)
                    )
                }
                Spacer(Modifier.height(15.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = password,
                        onValueChange = { input: String ->
                            password = input
                            isPasswordError = validateInput(input, 5)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            fontFamily = FontPlusJakarta
                        ),
                        label = {
                            Text(
                                text = "Password",
                                style = TextStyle(
                                    color = Color(0xFF49454F),
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp,
                                    fontFamily = FontPlusJakarta
                                )
                            )
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    isPasswordHidden = !isPasswordHidden
                                }
                            ) {
                                Icon(
                                    painter = if (isPasswordHidden) {
                                        painterResource(id = R.drawable.ic_filled_eye)
                                    } else {
                                        painterResource(id = R.drawable.ic_outlined_eye)
                                    },
                                    contentDescription = null,
                                    tint = Color(0x80000000)
                                )
                            }
                        },
                        isError = isPasswordError,
                        visualTransformation = if (isPasswordHidden) {
                            PasswordVisualTransformation()
                        } else {
                            VisualTransformation.None
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        singleLine = true,
                        shape = RoundedCornerShape(4.dp)
                    )
                }
                Spacer(Modifier.height(15.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = dob,
                        onValueChange = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                isDateModalHidden = false
                            },
                        enabled = false,
                        readOnly = true,
                        textStyle = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            fontFamily = FontPlusJakarta
                        ),
                        label = {
                            Text(
                                text = "Date of Birth",
                                style = TextStyle(
                                    color = Color(0xFF49454F),
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp,
                                    fontFamily = FontPlusJakarta
                                )
                            )
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(4.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            disabledContainerColor = Color.Unspecified,
                            disabledBorderColor = Color(0x90000000),
                            disabledTextColor = Color(0xFF000000)
                        )
                    )
                }
                Spacer(Modifier.height(25.dp))
                Button(
                    onClick = {
                        if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && dob != "01/01/2000" && !isEmailError && !isPasswordError) {
                            onSubmit(UserRegister(email, password, name, dob))
                        }
                    },
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonColors(
                        containerColor = Color(0xFF2B65D8),
                        contentColor = Color(0xFFF0F8FE),
                        disabledContainerColor = Color(0xFF8A8A8E),
                        disabledContentColor = Color(0xFFFFFFFF)
                    )
                ) {
                    Text(
                        text = "Sign Up",
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            fontFamily = FontPlusJakarta
                        )
                    )
                }
                Spacer(Modifier.height(45.dp))
                Row {
                    Text(
                        text = "Already have an account?",
                        style = TextStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 13.sp,
                            fontFamily = FontPlusJakarta
                        )
                    )
                    Spacer(Modifier.width(3.dp))
                    Text(
                        modifier = Modifier.clickable {
                            navigateToLogin()
                        },
                        text = "Log in",
                        style = TextStyle(
                            color = Color(0xFF1676F3),
                            fontWeight = FontWeight.Medium,
                            fontSize = 13.sp,
                            fontFamily = FontPlusJakarta
                        )
                    )
                }
            }
        }

        AnimatedVisibility(
            visible = !isDateModalHidden,
            enter = fadeIn(
                animationSpec = spring(Spring.DampingRatioLowBouncy, Spring.StiffnessLow)
            ),
            exit = fadeOut(
                animationSpec = spring(Spring.DampingRatioLowBouncy, Spring.StiffnessLow)
            )
        ) {
            val onDismiss = { isDateModalHidden = true }
            DatePickerDialog(
                onDismissRequest = { onDismiss() },
                confirmButton = {
                    Box(modifier = Modifier
                        .padding(end = 32.dp, bottom = 24.dp)
                        .clickable {
                            dob = selectedDate
                            onDismiss()
                        }
                    ) {
                        Text(
                            text = "OK",
                            style = TextStyle(
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp,
                                fontFamily = FontPlusJakarta
                            )
                        )
                    }
                },
                dismissButton = {
                    Box(modifier = Modifier
                        .padding(end = 32.dp, bottom = 24.dp)
                        .clickable { onDismiss() }
                    ) {
                        Text(
                            text = "Cancel",
                            style = TextStyle(
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp,
                                fontFamily = FontPlusJakarta
                            )
                        )
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }

        stateRegister.let { state ->
            when (state) {
                is UIState.Success -> {
                    navigateToMain(session)
                }

                is UIState.Error -> {
                    authToast(context)
                }

                UIState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFF272727).copy(alpha = 0.25f))
                    ) {
                        LinearProgressIndicator(
                            modifier = Modifier.fillMaxWidth(),
                            color = Color(0xFF1676F3),
                            trackColor = Color(0xFFF7F7F7),
                            strokeCap = StrokeCap.Round
                        )
                    }
                }

                UIState.Empty -> {}
            }
        }
    }
}
