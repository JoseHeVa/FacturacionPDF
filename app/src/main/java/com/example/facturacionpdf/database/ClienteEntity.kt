package com.example.facturacionpdf.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "cliente_entity")
data class ClienteEntity(
    @PrimaryKey
    var rncCedula: String,
    var nombre: String,
    var direccion: String
) : Serializable
