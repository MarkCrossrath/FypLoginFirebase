package com.example.login;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.Random;

public class TicketOneActivity extends AppCompatActivity {

    Button button;
    ImageView ticketImage;
    TextView code;
    TextView random;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_one);

        button = findViewById(R.id.ticket_purchase);
        ticketImage = findViewById(R.id.ticket_image);
        random= findViewById(R.id.random);


        code = findViewById(R.id.ticketPrice);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                random.setText(getRandomString(12));

                String data = random.getText().toString();
                if(data != null){
                    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                    try {


                        BitMatrix bitMatrix = multiFormatWriter.encode(data, BarcodeFormat.QR_CODE,500,500);
                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                        ticketImage.setImageBitmap(bitmap);

                    }catch (WriterException e){
                        e.printStackTrace();
                    }

                }
            }
        });

    }

    private static String getRandomString(int i ){
        final String chars = "abcdefghijklmonpqrstuvwxyz0123456789";
        StringBuilder results = new StringBuilder();
        while (i > 0) {

            Random rand = new Random();
            results.append(chars.charAt(rand.nextInt(chars.length())));
            i--;
        }
        return results.toString();
    }
}
