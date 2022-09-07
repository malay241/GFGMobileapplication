package com.example.webbrowser.ui.gallery;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.webbrowser.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final WebView homeWebView = binding.idWebViewContribute;
        final ProgressBar loadingPB = binding.idPBloading;
        homeWebView.loadUrl("https://www.geeksforgeeks.org/contribute/");
        homeWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {
                super.onPageStarted(view, url,favicon);
                loadingPB.setVisibility(View.VISIBLE);
            }
            @Override
            public void onPageFinished(WebView view, String url)
            {
                super.onPageFinished(view, url);
                loadingPB.setVisibility(View.GONE);
            }
        });

        homeWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keycode, KeyEvent keyEvent) {
                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN){
                    switch(keycode){
                        case KeyEvent.KEYCODE_BACK:
                            if(homeWebView.canGoBack()){
                                homeWebView.goBack();
                            }
                    }
                }
                return false;
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}