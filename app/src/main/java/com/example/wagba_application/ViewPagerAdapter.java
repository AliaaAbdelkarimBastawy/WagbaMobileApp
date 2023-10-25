package com.example.wagba_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPagerAdapter extends PagerAdapter {
    Context context;
    int images[]={
            R.drawable.img_1,
            R.drawable.img_1,
            R.drawable.img_1,
    };

    int headings []={
            R.string.text_2,
            R.string.text_4,
            R.string.text_6,
    };

    int descriptions []={
            R.string.text_3,
            R.string.text_5,
            R.string.text_7,
    };


    public ViewPagerAdapter(Context context)
    {
        this.context = context;
    }
    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater =
                (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout, container,false);

        ImageView image = (ImageView) view.findViewById(R.id.imageView4);
        TextView heading = (TextView) view.findViewById(R.id.textView2);
        TextView description = (TextView) view.findViewById(R.id.textView3);

        image.setImageResource(images[position]);
        heading.setText(headings[position]);
        description.setText(descriptions[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
