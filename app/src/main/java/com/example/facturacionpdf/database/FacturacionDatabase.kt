package com.example.facturacionpdf.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ClienteEntity::class, ProductoEntity::class, FacturaEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FacturacionDatabase : RoomDatabase() {
    abstract fun facturacionDao(): FacturacionDao
}
