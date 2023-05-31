package com.example.contactsapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable
import java.util.Date

@Entity(tableName = "Persons")
data class Persons(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "person_id")
    @NotNull var person_id: Int = 0,

    @ColumnInfo(name = "person_name")
    @NotNull var person_name: String = "",

    @ColumnInfo(name = "person_number")
    @NotNull var person_number: String = "",

    @ColumnInfo(name = "person_group")
    @NotNull var person_group: String = "",

    @ColumnInfo(name = "createdAt")
    @NotNull var createdAt: Date = Date()

) : Serializable

