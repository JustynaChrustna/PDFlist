package justynachrustna.pdflist;


import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Justyna on 19.10.2017.
 */

public class FetchData extends AsyncTask<Void, Void, Void> {


    @Override
    protected Void doInBackground(Void... voids) {
        try {
            String line;
            URL url = new URL("https://demo4534862.mockable.io/pdf");
            HttpURLConnection httpURLCOnnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLCOnnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();


            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);

            }

            JSONArray JA = new JSONArray(builder.toString());

            String title, icon, pdf;
            for (int i = 0; i < JA.length(); i++) {
                JSONObject jsonPDFarray = JA.getJSONObject(i);
                title = jsonPDFarray.getString("title");
                icon = jsonPDFarray.getString("icon");
                pdf = jsonPDFarray.getString("pdf");
                PdfListItem pdfListItem = new PdfListItem(title, icon, pdf);
                MainActivity.adapter.add(pdfListItem);


            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.pdfList.setAdapter(MainActivity.adapter);

    }
}

