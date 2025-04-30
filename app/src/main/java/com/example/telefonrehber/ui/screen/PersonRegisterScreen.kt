package com.example.telefonrehber.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.telefonrehber.ui.viewmodel.PersonViewModel
import com.example.telefonrehber.ui.widget.PersonOutlinedTextField


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonRegisterScreen(
    navController: NavController
){
    val viewModel:PersonViewModel= viewModel()
    val localfocusManager = LocalFocusManager.current

    val tfPersonName= remember { mutableStateOf("") }
    val tfPersonSurName= remember { mutableStateOf("") }
    val tfPersonPhoneNumber= remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Contact Register", fontSize = 20.sp)
            })
        }
    ) {contenPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contenPadding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PersonOutlinedTextField(
                valueText = tfPersonName.value,
                onValueChange = {
                    tfPersonName.value=it.trim() //boşlukları siler
                },
                labelText = "Contact Name",
                placeholderText ="",
                keyboardType = KeyboardType.Uri

            )
            PersonOutlinedTextField(
                valueText = tfPersonSurName.value,
                onValueChange = {
                    tfPersonSurName.value=it.trim() //boşlukları siler
                },
                labelText = "Contact Surname",
                placeholderText ="",
                keyboardType = KeyboardType.Uri

            )
            PersonOutlinedTextField(
                valueText = tfPersonPhoneNumber.value,
                onValueChange = {
                    tfPersonPhoneNumber.value=it.trim() //boşlukları siler
                },
                labelText = "Contact Phone Number",
                placeholderText ="",
                keyboardType = KeyboardType.Uri

            )

            Button(onClick = {
                viewModel.registerPersonData(tfPersonName.value,tfPersonSurName.value,tfPersonPhoneNumber.value)
                navController.popBackStack() //önceki ekrana geçer
                localfocusManager.clearFocus() //klavye vs açıksa önceki ekrana geçerken kapanır

            }) {
                Text(text = "Register")
            }

        }

    }

}