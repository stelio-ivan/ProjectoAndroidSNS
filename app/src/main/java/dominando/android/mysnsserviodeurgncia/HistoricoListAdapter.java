package dominando.android.mysnsserviodeurgncia;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import dominando.android.mysnsserviodeurgncia.model.Historico;

public class HistoricoListAdapter extends RecyclerView.Adapter<HistoricoListAdapter.ViewHolder> {
    private List<Historico> mHistorico;
    private HistoricoListAdapter.OnItemListener mOnItemListener;

    public HistoricoListAdapter(List<Historico> mHistorico, HistoricoListAdapter.OnItemListener onItemListener) {
        this.mHistorico = mHistorico;
        this.mOnItemListener = onItemListener;
    }

    @NonNull
    @Override
    public HistoricoListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_historico_item, viewGroup, false);
        return new HistoricoListAdapter.ViewHolder(view, mOnItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoricoListAdapter.ViewHolder viewHolder, int position) {
        viewHolder.mCheckIn.setText(new StringBuilder().append(mHistorico.get(position).getDataCheckIn()).append(", ").append(mHistorico.get(position).getHoraCheckIn()).toString());
        ////--------
        viewHolder.mCheckOut.setText(new StringBuilder().append(mHistorico.get(position).getDataCheckOut()).append(", ").append(mHistorico.get(position).getHoraCheckOut()).toString());
        ///----
        viewHolder.mNomeHospital.setText(mHistorico.get(position).getNomeHospital());
    }

    @Override
    public int getItemCount() {
        return mHistorico.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mCheckIn;
        ////--------
        public TextView mCheckOut;
        /////---------
        public TextView mNomeHospital;
        HistoricoListAdapter.OnItemListener onItemListener;

        public ViewHolder(@NonNull View itemView, HistoricoListAdapter.OnItemListener onItemListener) {
            super(itemView);
            mCheckIn = itemView.findViewById(R.id.historico_value_checkIn);
            ///---------
            mCheckOut = itemView.findViewById(R.id.historico_value_checkOut);
            ////------
            mNomeHospital = itemView.findViewById(R.id.nome_hospital);
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
