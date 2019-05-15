package dominando.android.mysnsserviodeurgncia;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

import dominando.android.mysnsserviodeurgncia.model.Hospital;

public class HospitalListAdapter extends RecyclerView.Adapter<HospitalListAdapter.ViewHolder> {

    private List<Hospital> mHospitais;
    private OnItemListener mOnItemListener;

    public HospitalListAdapter(List<Hospital> mHospitais, OnItemListener onItemListener) {
        this.mHospitais = mHospitais;
        this.mOnItemListener = onItemListener;
    }

    @NonNull
    @Override
    public HospitalListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_hospital_item, viewGroup, false);
        return new ViewHolder(view, mOnItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalListAdapter.ViewHolder viewHolder, int position) {
        viewHolder.mNome.setText(mHospitais.get(position).getNome());
        viewHolder.mDistancia.setText(String.valueOf(mHospitais.get(position).getDistanciaKm()));
    }

    @Override
    public int getItemCount() {
        return mHospitais.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mNome;
        public TextView mDistancia;
        OnItemListener onItemListener;

        public ViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
            super(itemView);
            mNome = itemView.findViewById(R.id.hospital_list_item_name);
            mDistancia = itemView.findViewById(R.id.hospital_list_item_distancia);
            this.onItemListener = onItemListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnItemListener {
        void onItemClick(int position);
    }
}
