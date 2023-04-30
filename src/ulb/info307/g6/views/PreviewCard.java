package ulb.info307.g6.views;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;

public class PreviewCard {

    @FXML
    private WebView previewWebView;

    public void setPageView(String url) {
        previewWebView.getEngine().load(url);
    }
}
