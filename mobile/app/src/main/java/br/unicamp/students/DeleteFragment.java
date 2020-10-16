package br.unicamp.students;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

import br.unicamp.students.Retrofit.Objects.Message;
import br.unicamp.students.Retrofit.Objects.Student;
import br.unicamp.students.Retrofit.Objects.StudentEdit;
import br.unicamp.students.Retrofit.RetrofitConfig;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class DeleteFragment extends Fragment {
    Button remover;

    EditText edtRA;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_delete, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.remover = (Button) getView().findViewById(R.id.btnDeletar);

        this.edtRA = (EditText) getView().findViewById(R.id.edtRA);

        this.remover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtRA.getText().toString().isEmpty()) {
                    deleteStudent(edtRA.getText().toString());
                }
            }
        });
    }

    private void deleteStudent(String RA) {
        Call<Message> call = new RetrofitConfig().getService().destroy(RA);

        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Response<Message> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    edtRA.setText("");

                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
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

            }
        });
    }
}
