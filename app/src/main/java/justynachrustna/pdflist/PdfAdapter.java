package justynachrustna.pdflist;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Justyna on 21.10.2017.
 */

public class PdfAdapter extends ArrayAdapter {
    List list = new ArrayList<>();

    public PdfAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(Object object) {
        // super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        final ItemHolder itemHolder;
        Log.d("tag", "wartosc position=" + position);
        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout, parent, false);
            itemHolder = new ItemHolder();
            itemHolder.tx_title = (TextView) row.findViewById(R.id.title);
            itemHolder.tx_icon = (ImageView) row.findViewById(R.id.icon);
            itemHolder.tx_pdf = (TextView) row.findViewById(R.id.pdf_download);
            if ((position % 2) == 0) {
                int color = Color.parseColor("#b2dfdb");
                row.setBackgroundColor(color);
            } else {
                int color = Color.parseColor("#e0f2f1");
                row.setBackgroundColor(color);
            }

            row.setTag(itemHolder);

        } else {
            itemHolder = (ItemHolder) row.getTag();

        }

        PdfListItem pdfListItem = (PdfListItem) this.getItem(position);


        itemHolder.tx_title.setText(pdfListItem.getTitle());


        new DownloadImageTask(itemHolder.tx_icon)
                .execute(pdfListItem.getIcon());


        return row;
    }

    static class ItemHolder {
        TextView tx_title, tx_pdf;
        ImageView tx_icon;

    }
}
