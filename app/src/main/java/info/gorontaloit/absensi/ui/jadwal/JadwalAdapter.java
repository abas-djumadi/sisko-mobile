package info.gorontaloit.absensi.ui.jadwal;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import info.gorontaloit.absensi.R;
import info.gorontaloit.absensi.data.model.Schedule;
import info.gorontaloit.absensi.ui.absen.AbsenFragment;

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.JadwalViewHolder> {

    private List<Schedule> schedules = new ArrayList<>();
    private FragmentManager fm;

    public JadwalAdapter(FragmentManager fm) {
        this.fm = fm;
    }

    void setSchedules(List<Schedule> scheduleList) {
        if (schedules == null) return;
        schedules.clear();
        schedules.addAll(scheduleList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public JadwalAdapter.JadwalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jadwal, parent, false);
        return new JadwalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JadwalAdapter.JadwalViewHolder holder, int position) {
        String waktu = schedules.get(position).getMulai() + " - " + schedules.get(position).getSelesai();
        holder.tvJurusan.setText(schedules.get(position).getJurusan().getNama());
        holder.tvKelas.setText(schedules.get(position).getKelas().getNama());
        holder.tvWaktu.setText(waktu);
        holder.tvTanggal.setText(schedules.get(position).getHariTanggal());

    }

    @Override
    public int getItemCount() {
        return schedules.size();
    }

    public class JadwalViewHolder extends RecyclerView.ViewHolder{
        private final TextView tvJurusan,tvKelas,tvWaktu,tvTanggal;
        public JadwalViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJurusan = itemView.findViewById(R.id.jurusan);
            tvKelas = itemView.findViewById(R.id.kelas);
            tvWaktu = itemView.findViewById(R.id.waktu);
            tvTanggal = itemView.findViewById(R.id.tanggal);
        }
    }
}
