package com.example.facturacionpdf.pdf

import android.content.Context
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import com.example.facturacionpdf.database.ClienteEntity
import com.example.facturacionpdf.database.FacturaEntity
import com.example.facturacionpdf.database.ProductoEntity
import java.io.File
import java.io.FileOutputStream

object PdfGenerator {

    fun generarRecibo(
        context: Context,
        factura: FacturaEntity,
        cliente: ClienteEntity,
        producto: ProductoEntity
    ): File {
        val documento = PdfDocument()

        // A4 aproximado en puntos (72 dpi)
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val pagina = documento.startPage(pageInfo)
        val lienzo = pagina.canvas

        val paintTitulo = Paint().apply {
            textSize = 22f
            isFakeBoldText = true
        }
        val paintTexto = Paint().apply {
            textSize = 14f
        }

        var y = 50f
        lienzo.drawText("Recibo de Factura", 40f, y, paintTitulo)

        y += 40f
        lienzo.drawText("Factura #: ${factura.idFactura}", 40f, y, paintTexto)
        y += 25f
        lienzo.drawText("Fecha: ${factura.fecha}", 40f, y, paintTexto)

        y += 40f
        lienzo.drawText("Cliente: ${cliente.nombre}", 40f, y, paintTexto)
        y += 25f
        lienzo.drawText("RNC/Cedula: ${cliente.rncCedula}", 40f, y, paintTexto)
        y += 25f
        lienzo.drawText("Direccion: ${cliente.direccion}", 40f, y, paintTexto)

        y += 40f
        lienzo.drawText("Producto: ${producto.nombre}", 40f, y, paintTexto)
        y += 25f
        lienzo.drawText("Cantidad: ${factura.cantidad}", 40f, y, paintTexto)
        y += 25f
        lienzo.drawText("Precio unitario: $${producto.precio}", 40f, y, paintTexto)

        y += 40f
        lienzo.drawText("TOTAL: $${factura.total}", 40f, y, paintTitulo)

        documento.finishPage(pagina)

        val carpeta = File(context.getExternalFilesDir(null), "recibos")
        if (!carpeta.exists()) carpeta.mkdirs()

        val archivo = File(carpeta, "recibo_${factura.idFactura}.pdf")
        documento.writeTo(FileOutputStream(archivo))
        documento.close()

        return archivo
    }
}
