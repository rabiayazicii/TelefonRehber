package com.example.telefonrehber.ui.screen

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.telefonrehber.ui.data.model.Person
import com.example.telefonrehber.ui.viewmodel.PersonViewModel
import com.example.telefonrehber.ui.widget.PersonOutlinedTextField
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonDetailScreen(person: Person, navController: NavController){

    val viewModel:PersonViewModel= viewModel()

    val localFocusManager = LocalFocusManager.current

    val tfPersonName = remember { mutableStateOf("") }
    val tfPersonSurname = remember { mutableStateOf("") }
    val tfPersonPhoneNumber = remember { mutableStateOf("") }

    LaunchedEffect( //classlardaki init gibi çalışır.Sayfa açıldığında bu çalışır önce yani
        key1 = true
    ) {

        tfPersonName.value=person.personName?:""
        tfPersonSurname.value=person.personSurname?:""
        tfPersonPhoneNumber.value=person.personPhoneNumber?:""

    }


    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Contact Update", fontSize = 20.sp)
            }
            )
        }
    ) {
        contentPadding ->

        Column(
            modifier = Modifier.padding(contentPadding).padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PersonOutlinedTextField(
                valueText = tfPersonName.value,
                onValueChange = {
                    tfPersonName.value=it.trim()
                },
                labelText = "Contact Name",
                placeholderText = "",
                keyboardType = KeyboardType.Text
            )
            PersonOutlinedTextField(
                valueText = tfPersonSurname.value,
                onValueChange = {
                    tfPersonSurname.value=it.trim()
                },
                labelText = "Contact Surame",
                placeholderText = "",
                keyboardType = KeyboardType.Text
            )
            PersonOutlinedTextField(
                valueText = tfPersonPhoneNumber.value,
                onValueChange = {
                    tfPersonPhoneNumber.value=it.trim()
                },
                labelText = "Contact Phone Number",
                placeholderText = "",
                keyboardType = KeyboardType.Text
            )

            Button(onClick = {
                viewModel.updatePersonData(person.personID,tfPersonName.value,tfPersonSurname.value,tfPersonPhoneNumber.value)
                navController.popBackStack()
                localFocusManager.clearFocus()
            },
                modifier = Modifier.padding(top=20.dp).size(200.dp,50.dp)) {
                Text(text = "UPDATE")
            }


        }

    }
}