package com.restaurantQueue.RestaurantQueue.controller.QRCode;

import com.google.zxing.WriterException;
import com.restaurantQueue.RestaurantQueue.config.QRCodeGenerator;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("qrcode")
public class QRCodeController {

    @GetMapping("/generate-qr")
    public ResponseEntity<byte[]> generateQRCode() {
        try {
            String url = "http://192.168.1.34:8080/queue/join";
            byte[] qrCode = QRCodeGenerator.generateQRCodeImage(url, 300, 300);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            return ResponseEntity.ok().headers(headers).body(qrCode);

        } catch (WriterException | IOException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
