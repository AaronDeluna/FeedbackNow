package org.javaacademy.feedbacknow.service.integration.qr;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.javaacademy.feedbacknow.exeption.QrGenerationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class QrGenerationService {
    private final OkHttpClient okHttpClient;
    @Value("${qr.size}")
    private String qrSize;
    @Value("${qr.api}")
    private String api;

    @SneakyThrows
    public byte[] getQrCode(UUID uuid) {
        BufferedImage qrCodeImage = generateQR(uuid);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(qrCodeImage, "PNG", baos);
        return baos.toByteArray();
    }

    private BufferedImage generateQR(UUID uuid) throws QrGenerationException {
        String startUrl = "http://localhost:8080/place/%s".formatted(uuid);
        Request request = buildRequest(api.formatted(startUrl, qrSize));

        try (Response response = okHttpClient.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            if (!response.isSuccessful() && responseBody == null) {
                throw new IOException("Ошибка при получении QR-кода: " + response.message());
            }
            return ImageIO.read(responseBody.byteStream());
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
