package com.example.facturacionpdf.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "producto_entity")
data class ProductoEntity(
    @PrimaryKey(autoGenerate = true)
    var idProducto: Int = 0,
    var nombre: String,
    var precio: Double,
    var stock: Int
) : Serializable
