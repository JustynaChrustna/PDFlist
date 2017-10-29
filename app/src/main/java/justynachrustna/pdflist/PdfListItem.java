package justynachrustna.pdflist;

/**
 * Created by Justyna on 21.10.2017.
 */

public class PdfListItem {
    String title;
    String icon;
    String url;

    public PdfListItem(String title, String icon, String url) {
        this.icon = icon;
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
