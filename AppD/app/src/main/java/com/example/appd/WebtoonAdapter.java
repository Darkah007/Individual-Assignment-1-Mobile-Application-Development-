package com.example.appd;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class WebtoonAdapter extends BaseAdapter {

    private Context context;

    public WebtoonAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 6; // Replace with actual number of webtoons
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.webtoon_item, null);
        }

        ImageView imageView = view.findViewById(R.id.webtoon_image);
        TextView textView = view.findViewById(R.id.webtoon_name);

        // Set image and name for each webtoon
        switch (position) {
            case 0:
                imageView.setImageResource(R.drawable.avatar);
                textView.setText("Avatar The Last Airbender Book 1 Water");
                break;
            case 1:
                imageView.setImageResource(R.drawable.korra);
                textView.setText("Avatar : Legend of Korra ");
                break;
            case 2:
                imageView.setImageResource(R.drawable.avatar2);
                textView.setText("Avatar The Last Airbender Book 2 Earth");
                break;
            case 3:
                imageView.setImageResource(R.drawable.avatar3);
                textView.setText("Avatar The Last Airbender Book 3 Fire");
                break;
            case 4:
                imageView.setImageResource(R.drawable.op);
                textView.setText("One Peice");
                break;
            case 5:
                imageView.setImageResource(R.drawable.kdm);
                textView.setText("Kingdom");
                break;
            // Add more cases for other webtoons
            default:
                break;
        }

        return view;
    }
}