package info.gorontaloit.absensi.ui.jadwal;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import info.gorontaloit.absensi.R;
import info.gorontaloit.absensi.data.model.Schedule;
import info.gorontaloit.absensi.ui.absen.AbsenFragment;
import info.gorontaloit.absensi.ui.login.LoginActivity;
import info.gorontaloit.absensi.ui.student.StudentActivity;
import info.gorontaloit.absensi.utils.ItemClickSupport;
import info.gorontaloit.absensi.viewmodel.ViewModelFactory;

public class JadwalFragment extends Fragment {

    SwipeRefreshLayout srl;
    JadwalAdapter adapter;
    JadwalViewModel viewModel;
    List<Schedule> scheduleList = new ArrayList<>();
    String title;
    private FragmentManager fragmentManager;
    private JadwalFragment jadwalFragment;

    public void setTitle(String title) {
        this.title = title;
    }

    public JadwalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jadwal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() != null) {
            MaterialToolbar toolbar = view.findViewById(R.id.appbar_tool_bar);
            toolbar.setTitle(title);

            RecyclerView rvSchedules = view.findViewById(R.id.rv_schedule);
            rvSchedules.setHasFixedSize(true);
            rvSchedules.setLayoutManager(new LinearLayoutManager(getContext()));

            adapter = new JadwalAdapter(getFragmentManager());
            viewModel = obtainViewModel(getActivity());
            loadSchedule();
            srl = view.findViewById(R.id.schedule_refresh);
            srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    loadSchedule();
                    srl.setRefreshing(false);
                }
            });
            rvSchedules.setAdapter(adapter);
            ItemClickSupport.addTo(rvSchedules).setOnItemCLickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    Toast.makeText(getContext(), "Anda memilih " + scheduleList.get(position).getHariTanggal(), Toast.LENGTH_SHORT).show();
                    Fragment fragment = new AbsenFragment();
                    ((AbsenFragment) fragment).setTitle("Absen");
                    Bundle bundle = new Bundle();
                    bundle.putInt(AbsenFragment.KELAS, scheduleList.get(position).getKelas().getId());
                    bundle.putInt(AbsenFragment.JURUSAN, scheduleList.get(position).getJurusan().getId());
                    fragment.setArguments(bundle);

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.jadwal_container,fragment);
                    ft.addToBackStack(null);
                    ft.commit();
//                    Intent intent = new Intent(v.getContext(), StudentActivity.class);
//                    startActivity(intent);
//
                }
            });
        }
    }

    @NonNull
    private static JadwalViewModel obtainViewModel(FragmentActivity fragmentActivity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(fragmentActivity.getApplication());
        return ViewModelProviders.of(fragmentActivity, factory).get(JadwalViewModel.class);
    }

    private void loadSchedule() {
        viewModel.getSchedules().observe(this,schedules -> {
            adapter.setSchedules(schedules);
            scheduleList = schedules;
            adapter.notifyDataSetChanged();
        });
        for (Schedule schedule : scheduleList) {
            Log.d("Jadwal ", schedule.getHariTanggal());
        }
    }
}
