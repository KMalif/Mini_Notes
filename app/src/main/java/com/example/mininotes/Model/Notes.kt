package com.example.mininotes.Model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Notes: RealmObject(){

    private var id : Int? = null
    private var Title : String? = null
    private var Description : String? = null


    fun setId (id:Int){
        this.id = id
    }
    fun getId (): Int?{
        return id
    }
    fun setTitle (Title : String){
        this.Title = Title
    }
    fun getTitle (): String?{
        return Title
    }
    fun setDescription (Description : String){
        this.Description = Description
    }
    fun getDescription (): String?{
        return Description
    }

}

