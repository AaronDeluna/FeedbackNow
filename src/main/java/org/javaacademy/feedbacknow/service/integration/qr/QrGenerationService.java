package org.javaacademy.feedbacknow.service.integration.qr;

import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.javaacademy.feedbacknow.exeption.QrGenerationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


@Service
@RequiredArgsConstructor
public class QrGenerationService {
    private final OkHttpClient okHttpClient;
    @Value("${qr.size}")
    private String qrSize;
    @Value("${qr.api}")
    private String api;

    @SneakyThrows
    public byte[] getQrCode(String header) {
        BufferedImage qrCodeImage = generateQR(header);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(qrCodeImage, "PNG", baos);
        return baos.toByteArray();
    }

    private BufferedImage generateQR(String header) throws QrGenerationException {
        Request request = buildRequest(api.formatted(header, qrSize));

        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful() && response.body() == null) {
                throw new IOException("Ошибка при получении QR-кода: " + response.message());
            }
            return ImageIO.read(response.body().byteStream());
        } catch (IOException e) {
            throw new QrGenerationException(e);
        }
    }

    private Request buildRequest(String url) {
        return new Request.Builder()
                .get()
                .url(url)
                .build();
    }
}
