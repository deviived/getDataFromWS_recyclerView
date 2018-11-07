package news.airweb.fr.testandroidairweb;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLStreamHandler;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import news.airweb.fr.testandroidairweb.model.News;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<News> mList;
    private String url = "";
    InputStream inputStream;
    private String result = "";

    public MyAdapter(List<News> list){
        mList = list;
    }

    public void updateList(List<News> list) {
        this.mList.clear();
        System.out.println("updateList : "+list);
        this.mList.addAll(list);
        System.out.println("updateList : "+mList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mList!=null) {
            return mList.size();
        }
        else return 0;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_cell, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        News mUniqueData = mList.get(position);
        holder.display(mUniqueData);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView picture;
        private final TextView name;
        private final TextView description;

        private News uniqueData;

        public MyViewHolder(final View itemView) {
            super(itemView);

            picture = (ImageView) itemView.findViewById(R.id.picture);
            name = ((TextView) itemView.findViewById(R.id.name));
            description = ((TextView) itemView.findViewById(R.id.description));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(itemView.getContext())
                            .setTitle(uniqueData.getTitle())
                            .setMessage(uniqueData.getContent())
                            .show();
                }
            });
        }

        public void display(News news) {
            uniqueData = news;
            System.out.println("uri parse : "+Uri.parse(uniqueData.getPicture().split("\\?")[0]));
            url = uniqueData.getPicture().split("\\?")[0];

            Picasso.get().load(url).resize(150, 150).centerCrop().into(picture);
            name.setText(uniqueData.getTitle());
            description.setText(uniqueData.getContent());
        }

    }

}