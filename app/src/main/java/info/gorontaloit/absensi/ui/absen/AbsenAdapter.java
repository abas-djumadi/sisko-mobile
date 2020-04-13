package info.gorontaloit.absensi.ui.absen;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import info.gorontaloit.absensi.R;
import info.gorontaloit.absensi.data.model.Siswa;

public class AbsenAdapter extends RecyclerView.Adapter<AbsenAdapter.AbsenViewHolder> {

    List<Siswa> siswaList = new ArrayList<>();

    public void setSiswaList(List<Siswa> siswas) {
        if (siswaList == null) return;
        siswaList.clear();
        siswaList.addAll(siswas);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AbsenAdapter.AbsenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_siswa, parent, false);
        return new AbsenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AbsenAdapter.AbsenViewHolder holder, int position) {
        holder.studentName.setText(siswaList.get(position).getNama());
        holder.studentNis.setText(siswaList.get(position).getNis());
        Log.d("Nama Siswa", siswaList.get(position).getNama());
    }

    @Override
    public int getItemCount() {
        return siswaList.size();
    }

    public class AbsenViewHolder extends RecyclerView.ViewHolder {

        private final TextView studentNis,studentName;
        private final MaterialButton btnHadir,btnTidakHadir;

        public AbsenViewHolder(@NonNull View itemView) {
            super(itemView);
            studentNis = itemView.findViewById(R.id.student_nis);
            studentName = itemView.findViewById(R.id.student_name);
            btnHadir = itemView.findViewById(R.id.btnHadir);
            btnTidakHadir = itemView.findViewById(R.id.btnTidakHadir);
        }
    }
}
