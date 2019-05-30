package dominando.android.mysnsserviodeurgncia;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import dominando.android.mysnsserviodeurgncia.model.Hospital;
import dominando.android.mysnsserviodeurgncia.model.Urgencia;

public class HospitalDetalheAdapter extends RecyclerView.Adapter<HospitalDetalheAdapter.ViewHolder> {

    private Hospital mHospital;
    private OnItemListener mOnItemListener;

    public HospitalDetalheAdapter(Hospital mHospital, OnItemListener onItemListener) {
        this.mHospital = mHospital;
        this.mOnItemListener = onItemListener;
    }

    @NonNull
    @Override
    public HospitalDetalheAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_hospital_detalhe, viewGroup, false);
        return new ViewHolder(view, mOnItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalDetalheAdapter.ViewHolder viewHolder, int position) {
        viewHolder.mNome.setText(mHospital.getName());
        viewHolder.mEmail.setText(mHospital.getEmail());
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mNome;
        private TextView mTipo;
        private TextView mTelefone;
        private TextView mMorada;
        private TextView mEmail;
        private TextView mUrl;

        public ViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
            super(itemView);
            mNome = itemView.findViewById(R.id.nome_hospital);
            mTelefone = itemView.findViewById(R.id.value_contato);
            mMorada = itemView.findViewById(R.id.value_morada);
            mEmail = itemView.findViewById(R.id.value_email);
            mUrl = itemView.findViewById(R.id.value_url);

            mOnItemListener = onItemListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnItemListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnItemListener {
        void onItemClick(int position);
    }
}
