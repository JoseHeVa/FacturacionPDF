package com.example.facturacionpdf.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface FacturacionDao {

    // Consultas de Productos
    @Query("SELECT * FROM producto_entity WHERE idProducto = :id")
    suspend fun getProductoById(id: Int): ProductoEntity?

    @Insert
    suspend fun insertProducto(producto: ProductoEntity): Long

    // Consultas de Clientes
    @Query("SELECT * FROM cliente_entity WHERE rncCedula = :rnc")
    suspend fun getClienteByRnc(rnc: String): ClienteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCliente(cliente: ClienteEntity)

    // Registrar Factura
    @Insert
    suspend fun insertFactura(factura: FacturaEntity): Long

    @Update
    suspend fun updateProducto(producto: ProductoEntity)
}
