package com.example.telefonrehber.ui.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.telefonrehber.ui.data.model.Person
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PersonRepository {

    private val referencePerson: DatabaseReference = FirebaseDatabase.getInstance()
        .getReference("person") //realtime db kısmındaki oluşturulmuş alan.bu yola kaydet demek
    val personList= MutableLiveData<List<Person>>() //livedata çünkü değişikliği anlık olarak görelim.

    fun getAllPerson(){
        referencePerson.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<Person>()
                snapshot.children.forEach{
                    val person = it.getValue(Person::class.java)
                    if(person != null){
                        person.personID = it.key!!
                        list.add(person)
                    }
                }
                personList.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("PersonRepository",error.message)
            }
        })
    }

    fun searchPerson(searchText: String){
        referencePerson.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<Person>()
                snapshot.children.forEach {
                    val person = it.getValue(Person::class.java)
                    val isPersonAvailable=person?.personName!!.lowercase().contains(searchText.lowercase())
                    if(isPersonAvailable){
                        person.personID=it.key!!
                        list.add(person)
                    }
                }
                personList.value=list
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("PersonRepository",error.message)
            }
        })
    }

    fun addPerson(
        newPersonName:String,
        newPersonSurname:String,
        newPersonPhoneNumber:String
    ) {
        val newPerson = Person(
            personName = newPersonName,
            personSurname = newPersonSurname,
            personPhoneNumber = newPersonPhoneNumber
        )
        referencePerson.push().setValue(newPerson).addOnSuccessListener {
            Log.d("PersonRepository", "Kişi başarıyla eklendi")
        }.addOnFailureListener { e ->
            Log.e("PersonRepository", "Kişi eklenirken hata oluştu: ${e.message}")
        }
    }

    fun updatePerson(
        updatePersonId:String,
        updatePersonName:String,
        updatePersonSurname:String,
        updatePersonPhoneNumber:String
    ){
        val updatedPerson = HashMap<String,Any>()
        updatedPerson["personName"] = updatePersonName
        updatedPerson["personSurname"] = updatePersonSurname
        updatedPerson["personPhoneNumber"] = updatePersonPhoneNumber

        referencePerson.child(updatePersonId).updateChildren(updatedPerson)
    }

    fun deletePerson(
        deletePersonId:String
    ){
        referencePerson.child(deletePersonId).removeValue()
    }
}