package io.github.dalwadi2.spotifychallange.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.github.dalwadi2.spotifychallange.R;
import io.github.dalwadi2.spotifychallange.network.response.RespPlaylist;


public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.ViewHolder> {

    private Context mContext;
    private List<RespPlaylist.Items> modelList;

    private OnItemClickListener mItemClickListener;


    public SongsAdapter(Context context) {
        this.mContext = context;
    }

    public void updateList(List<RespPlaylist.Items> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public SongsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.row_song, viewGroup, false);

        return new SongsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongsAdapter.ViewHolder holder, int position) {
        RespPlaylist.Items model = modelList.get(position);

        holder.tvTitle.setText(model.getTrack().getName());
        holder.tvAlbum.setText(model.getTrack().getAlbum().getName());
        holder.tvDuration.setText(String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(model.getTrack().getDurationMs()),
                TimeUnit.MILLISECONDS.toSeconds(model.getTrack().getDurationMs()) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(model.getTrack().getDurationMs()))));

    }

    @Override
    public int getItemCount() {
        return modelList == null ? 0 : modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private RespPlaylist.Items getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, RespPlaylist.Items model);
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvAlbum;
        private TextView tvDuration;

        ViewHolder(final View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvAlbum = (TextView) itemView.findViewById(R.id.tv_album);
            tvDuration = (TextView) itemView.findViewById(R.id.tv_duration);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));
                }
            });
        }
    }

}

