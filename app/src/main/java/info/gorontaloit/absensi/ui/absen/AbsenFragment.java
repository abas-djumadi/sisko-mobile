package info.gorontaloit.absensi.ui.absen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import info.gorontaloit.absensi.R;
import info.gorontaloit.absensi.data.model.Jurusan;
import info.gorontaloit.absensi.data.model.Kelas;
import info.gorontaloit.absensi.data.model.Siswa;
import info.gorontaloit.absensi.ui.jadwal.JadwalFragment;
import info.gorontaloit.absensi.ui.jadwal.JadwalViewModel;
import info.gorontaloit.absensi.viewmodel.ViewModelFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class AbsenFragment extends Fragment {

    public static String JURUSAN = "JURUSAN";
    public static String KELAS = "KELAS";

    SwipeRefreshLayout srl;
    LinearLayout studentContainer;
    AbsenAdapter adapter;
    AbsenViewModel viewModel;
    RecyclerView rv_absen;
    String title;
    List<Siswa> siswaList = new ArrayList<>();

    public void setTitle(String title) {
        this.title = title;
    }

    public AbsenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_absen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() != null) {
            MaterialToolbar toolbar = view.findViewById(R.id.appbar_tool_bar);
            toolbar.setTitle(title);
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back);


            rv_absen = view.findViewById(R.id.rv_absen);
            rv_absen.setHasFixedSize(true);
            rv_absen.setLayoutManager(new LinearLayoutManager(getContext()));

            viewModel = obtainViewModel(getActivity());
            adapter = new AbsenAdapter();
            loadData();
            srl = view.findViewById(R.id.absen_refresh);
            srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    loadData();
                    srl.setRefreshing(false);
                }
            });
            rv_absen.setAdapter(adapter);
//            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (getFragmentManager() != null) {
//                        JadwalFragment jf = new JadwalFragment();
//                        jf.setTitle("Jadwal");
//                        getFragmentManager().beginTransaction()
//                                .replace(R.id.container_layout, jf, jf.getClass().getSimpleName())
//                                .addToBackStack(null)
//                                .commit();
//                    }
//                }
//            });
        }

    }

    @NonNull
    private static AbsenViewModel obtainViewModel(FragmentActivity fragmentActivity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(fragmentActivity.getApplication());
        return ViewModelProviders.of(fragmentActivity, factory).get(AbsenViewModel.class);
    }

    private void loadData() {
        if (getArguments() != null) {
            Integer jurusanId = Integer.parseInt(getArguments().getString(JURUSAN));
            Integer kelasId = Integer.parseInt(getArguments().getString(KELAS));
            Log.d("KelasId", getArguments().getString(KELAS));
            Log.d("JurusanID", getArguments().getString(JURUSAN));
            viewModel.getSiswaByJurusanAndKelas(jurusanId,kelasId).observe(this, siswas -> {
                if (siswas != null) {
                    adapter.setSiswaList(siswas);
                    siswaList = siswas;
                    adapter.notifyDataSetChanged();
                } else {
                    siswaList = null;
                }
            });
            Log.d("Siswa List", siswaList.toString());
        }
    }
}
