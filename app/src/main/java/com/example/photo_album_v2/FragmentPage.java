package com.example.photo_album_v2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentPage extends Fragment {
    String imageDescription;
    byte[] byteArray;
    public FragmentPage(String imageDes, byte[] byteArr)
    {
        imageDescription=imageDes;
        byteArray=byteArr;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        final View view;
        view= inflater.inflate(R.layout.fragment_layout,container,false);
        Bitmap bitmap= BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        ImageView image = (ImageView) view.findViewById(R.id.imageView);
        image.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250,
                250, false));
        TextView imageD=(TextView)view.findViewById(R.id.imageDescriptionTextView);
        imageD.setText(imageDescription);
        return view;
    }
}
