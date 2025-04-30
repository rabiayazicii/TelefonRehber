package com.example.telefonrehber.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.telefonrehber.ui.data.model.Person
import com.example.telefonrehber.ui.data.repository.PersonRepository

class PersonViewModel:ViewModel() {
    private val repository=PersonRepository()
//init ile sınıf her çağırıldığında init içinde yazılanlar tekrardan çalışır

    var personList=MutableLiveData<List<Person>>()

    init{
        getAllPersonData()
        personList=repository.personList //repo ile bağlantı kuruldu
    }

    fun getAllPersonData(){
        repository.getAllPerson()
    }

    fun searchPersonData(searchText:String){
        repository.searchPerson(searchText)
    }

    fun registerPersonData(
        personName: String,
        personSurname:String,
        personPhone: String
    ){
        repository.addPerson(personName,personSurname,personPhone)
    }

    fun updatePersonData(

        personId:String,
        personName:String,
        personSurname:String,
        personPhone:String
    ){
        repository.updatePerson(personId,personName,personSurname,personPhone)
    }

    fun deletePersonData(
        personId:String
    ){
        repository.deletePerson(personId)

    }

}