package nyc.jackcook.fox;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import nyc.jackcook.fox.util.RequestManager;

public class SellingActivity extends Activity implements NfcAdapter.CreateNdefMessageCallback {

    private NfcAdapter adapter;
    private String amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selling);

        amount = getIntent().getStringExtra("amount");

        adapter = NfcAdapter.getDefaultAdapter(this);
        adapter.setNdefPushMessageCallback(this, this);

        ImageView qrcImage = (ImageView) findViewById(R.id.qrcImage);

        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode("158VcedcMUkdadphUCuHwumgkUVfHF2q3C", BarcodeFormat.QR_CODE, 512, 512);

            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();

            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }

            qrcImage.setImageBitmap(bmp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
        Log.d("FOX", RequestManager.tokens());
        String str = "158VcedcMUkdadphUCuHwumgkUVfHF2q3C," + amount;
        NdefMessage msg = new NdefMessage(
                new NdefRecord[] {
                        NdefRecord.createMime("application/vnd.nyc.jackcook.fox.beam", str.getBytes())
                }
        );

        return msg;
    }
}
