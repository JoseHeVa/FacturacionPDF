package com.example.facturacionpdf.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "factura_entity")
data class FacturaEntity(
    @PrimaryKey(autoGenerate = true)
    var idFactura: Int = 0,
    var clienteRnc: String,
    var productoId: Int,
    var cantidad: Int,
    var total: Double,
    var fecha: String
)
