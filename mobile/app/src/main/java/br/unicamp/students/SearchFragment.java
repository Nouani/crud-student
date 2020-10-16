package br.unicamp.students;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.unicamp.students.Retrofit.Objects.Student;
import br.unicamp.students.Retrofit.RetrofitConfig;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class SearchFragment extends Fragment {
    private EditText edtSearch;
    private ImageButton searchBtn;

    private ListView resultList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.edtSearch = (EditText) getView().findViewById(R.id.edtSearch);
        this.searchBtn = (ImageButton) getView().findViewById(R.id.btnBuscar);

        this.resultList = (ListView) getView().findViewById(R.id.resultList);

        this.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtSearch.getText().toString().isEmpty())
                    findStudentByRA(edtSearch.getText().toString());
                else
                    findAllStudents();
            }
        });
    }

    private void findStudentByRA(String RA) {
        Call<Student> call = new RetrofitConfig().getService().show(RA);

        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Response<Student> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    List<Student> student = new ArrayList<>();
                    student.add(response.body());
                    showAll(student);
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getContext(), jObjError.getString("error"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(), "Falha na busca", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void findAllStudents() {
        Call<List<Student>> call = new RetrofitConfig().getService().index();

        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Response<List<Student>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    showAll(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getContext(), jObjError.getString("error"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(), "Falha na busca", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showAll(List<Student> students) {
        if (students != null) {
            ArrayList<Map<String, Object>> listaItem = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                Map<String, Object> item = new HashMap<String, Object>();
                item.put("mapaUm", "" + student.getRA() + " - " + student.getName());
                item.put("mapaDois", "" + student.getEmail());

                listaItem.add(item);

                SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), listaItem, android.R.layout.simple_list_item_2, new String[]{"mapaUm", "mapaDois"}, new int[]{android.R.id.text1, android.R.id.text2});

                this.resultList.setAdapter(simpleAdapter);
            }
        }
    }
}
