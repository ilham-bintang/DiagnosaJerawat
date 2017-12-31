package io.github.nullphantom.diagnosajerawat.about;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.github.nullphantom.diagnosajerawat.R;
import io.github.nullphantom.diagnosajerawat.activity.MainActivity;

/**
 * Created by nullphantom on 31/12/17.
 */

public class DataAdapter extends ArrayAdapter<Data> {

    List<Data> dataList;
    Context context;
    private LayoutInflater mInflater;

    public DataAdapter(Context context, List<Data> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        dataList = objects;
    }

    @Nullable
    @Override
    public Data getItem(int position) {
        return dataList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.about_row_view, parent, false);
            vh = ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        Data item = getItem(position);

        vh.textViewName.setText(item.getNama());
//        vh.textViewEmail.setText(item.getEmail());

//        vh.textViewNim.setText(item.getNim());
        //g.setImageResource();
        Picasso.with(context).load(getImageId(context, item.getGambar())).placeholder(android.R.drawable.btn_plus).error(android.R.drawable.btn_default_small).into(vh.imageView);

        return vh.rootView;
    }

    private static class ViewHolder {
        public final RelativeLayout rootView;
        public final ImageView imageView;
        public final TextView textViewName;
//        public final TextView textViewEmail;
//        public final TextView textViewNim;

        private ViewHolder(RelativeLayout rootView, TextView textViewName, ImageView imageView) {
            this.rootView = rootView;
            this.imageView = imageView;
            this.textViewName = textViewName;
//            this.textViewEmail = textViewEmail;
//            this.textViewNim = textViewNim;
        }

        public static ViewHolder create(RelativeLayout rootView) {
            ImageView imageView = rootView.findViewById(R.id.imageView);
            TextView textViewName = rootView.findViewById(R.id.textViewName);
//            TextView textViewEmail = rootView.findViewById(R.id.textViewEmail);
//            TextView textViewNim = rootView.findViewById(R.id.textViewNim);
            return new ViewHolder(rootView, textViewName, imageView);
        }
    }

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }
}
