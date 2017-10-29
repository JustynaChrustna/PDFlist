package justynachrustna.pdflist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;



public class MainActivity extends AppCompatActivity {

    public  static ListView pdfList;
    public  static PdfAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FetchData process = new FetchData();
        process.execute();
        adapter = new PdfAdapter(this, R.layout.row_layout);

        pdfList = (ListView) findViewById(R.id.pdf_list);
        pdfList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PdfListItem item = (PdfListItem) pdfList.getItemAtPosition(position);
                String url = item.getUrl();

                startDownloadService(view, url);
            }
        });
    }

    public void startDownloadService(View view, String url) {
        Intent intent = new Intent(this, DownloadService.class);
        intent.putExtra("url", url);
        DownloadService.verifyStoragePermissions(this);
        this.startService(intent);

    }

}
