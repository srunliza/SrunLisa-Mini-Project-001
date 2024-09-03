package com.example.bank_app_design.configuration

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter

// Function to generate a QR code bitmap from a given text
fun generateQRCode(text: String): Bitmap {

    val size = 512
    // Set the character encoding to UTF-8
    val hints = mapOf(
        EncodeHintType.CHARACTER_SET to "UTF-8"
    )

    // Create a BitMatrix representing the QR code
    val bitMatrix: BitMatrix = QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, size, size, hints)

    // Create an empty bitmap with the specified size
    val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565)


    // Fill the bitmap with the QR code pixels (black and white)
    for (x in 0 until size) {
        for (y in 0 until size) {
            bitmap.setPixel(x, y, if (bitMatrix.get(x, y)) android.graphics.Color.BLACK else android.graphics.Color.WHITE)
        }
    }

    // Return the generated QR code bitmap
    return bitmap
}