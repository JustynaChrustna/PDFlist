package justynachrustna.pdflist;


import android.Manifest;
import android.app.Activity;
import android.app.IntentService;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Justyna on 22.10.2017.
 */

public class DownloadService extends IntentService {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public DownloadService() {
        super(DownloadService.class.getName());
    }

    public DownloadService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.e("DownloadService", "Service Started");
        String passedURL = intent.getStringExtra("url");
        downloadFile(passedURL);
        Log.e("DownloadService", "Service Stopped");


    }

    protected void downloadFile(String url) {

        FileOutputStream fileOutputStream;
        InputStream inputStream;
        try {
            URL fileURL = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) fileURL.openConnection();
            inputStream = urlConnection.getInputStream();
            String fileName = url.substring(url.lastIndexOf('/') + 1);
            fileOutputStream = new FileOutputStream(Environment.getExternalStorageDirectory() + "/" + fileName);

            byte[] buffer = new byte[1024];
            int count;
            Log.e("DownloadService", "downloading file");
            while ((count = inputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, count);
            }
            fileOutputStream.flush();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e("DownloadService", "file not found ex");
        } catch (MalformedURLException e) {
            Log.e("DownloadService", "malformed url ex");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("DownloadService", "e");
            e.printStackTrace();
        }
    }
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}
