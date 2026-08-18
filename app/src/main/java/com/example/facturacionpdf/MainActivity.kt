package com.example.facturacionpdf

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import com.example.facturacionpdf.database.ClienteEntity
import com.example.facturacionpdf.database.FacturaEntity
import com.example.facturacionpdf.database.ProductoEntity
import com.example.facturacionpdf.pdf.PdfGenerator
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    PantallaFacturacion()
                }
            }
        }
    }
}

fun abrirPdf(context: Context, archivo: File) {
    val uri = FileProvider.getUriForFile(
        context,
        "${context.packageName}.fileprovider",
        archivo
    )
    val intent = Intent(Intent.ACTION_VIEW).apply {
        setDataAndType(uri, "application/pdf")
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_READ_URI_PERMISSION
    }
    context.startActivity(intent)
}

@Composable
fun PantallaFacturacion() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dao = remember { FacturacionApp.database.facturacionDao() }

    var rnc by remember { mutableStateOf("") }
    var nombreCliente by remember { mutableStateOf("") }
    var productoId by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }

    // Insertar producto de prueba una sola vez al abrir la pantalla
    LaunchedEffect(Unit) {
        if (dao.getProductoById(1) == null) {
            dao.insertProducto(
                ProductoEntity(
                    idProducto = 1,
                    nombre = "Laptop",
                    precio = 500.0,
                    stock = 10
                )
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Generar Factura",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = rnc,
            onValueChange = { rnc = it },
            label = { Text("RNC / Cedula Cliente") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = nombreCliente,
            onValueChange = { nombreCliente = it },
            label = { Text("Nombre del Cliente") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = productoId,
            onValueChange = { productoId = it },
            label = { Text("ID del Producto") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = cantidad,
            onValueChange = { cantidad = it },
            label = { Text("Cantidad") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (rnc.isEmpty() || nombreCliente.isEmpty() || productoId.isEmpty() || cantidad.isEmpty()) {
                    Toast.makeText(context, "Complete todos los campos", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                val prodId = productoId.toInt()
                val cant = cantidad.toInt()

                scope.launch {
                    val producto = dao.getProductoById(prodId)
                    if (producto == null) {
                        Toast.makeText(context, "Producto no encontrado", Toast.LENGTH_SHORT).show()
                        return@launch
                    }
                    if (producto.stock < cant) {
                        Toast.makeText(
                            context,
                            "Stock insuficiente (${producto.stock} disponibles)",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@launch
                    }

                    // Registrar/actualizar cliente
                    dao.insertCliente(ClienteEntity(rnc, nombreCliente, "Direccion generica"))

                    // Calcular total y actualizar stock
                    val total = producto.precio * cant
                    producto.stock -= cant
                    dao.updateProducto(producto)

                    // Registrar la factura
                    val fecha = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date())
                    val idFactura = dao.insertFactura(
                        FacturaEntity(
                            clienteRnc = rnc,
                            productoId = prodId,
                            cantidad = cant,
                            total = total,
                            fecha = fecha
                        )
                    ).toInt()

                    val clienteGuardado = dao.getClienteByRnc(rnc)!!
                    val facturaCompleta = FacturaEntity(
                        idFactura = idFactura,
                        clienteRnc = rnc,
                        productoId = prodId,
                        cantidad = cant,
                        total = total,
                        fecha = fecha
                    )

                    // Generar el recibo en PDF
                    val archivoPdf = PdfGenerator.generarRecibo(
                        context = context,
                        factura = facturaCompleta,
                        cliente = clienteGuardado,
                        producto = producto
                    )

                    Toast.makeText(context, "Factura creada. Abriendo PDF...", Toast.LENGTH_SHORT).show()
                    abrirPdf(context, archivoPdf)

                    rnc = ""
                    nombreCliente = ""
                    productoId = ""
                    cantidad = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Procesar Factura")
        }
    }
}
