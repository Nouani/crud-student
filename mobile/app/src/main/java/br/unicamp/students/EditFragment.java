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

import org.json.JSONObject;

import br.unicamp.students.Retrofit.Objects.Student;
import br.unicamp.students.Retrofit.Objects.StudentEdit;
import br.unicamp.students.Retrofit.RetrofitConfig;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class EditFragment extends Fragment {
    Button editar;
    EditText edtRA, edtNome, edtEmail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.editar = (Button) getView().findViewById(R.id.btnEditar);

        this.edtRA = (EditText) getView().findViewById(R.id.edtRA);
        this.edtNome = (EditText) getView().findViewById(R.id.edtNome);
        this.edtEmail = (EditText) getView().findViewById(R.id.edtEmail);

        this.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtRA.getText().toString().isEmpty() && !edtNome.getText().toString().isEmpty() && !edtEmail.getText().toString().isEmpty()) {
                    editStudent(new StudentEdit(edtNome.getText().toString(), edtEmail.getText().toString()), edtRA.getText().toString());
                }
            }
        });
    }

    private void editStudent(StudentEdit student, String RA) {
        Call<Student> call = new RetrofitConfig().getService().update(RA, student);

        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Response<Student> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    edtRA.setText("");
                    edtNome.setText("");
                    edtEmail.setText("");

                    Toast.makeText(getActivity(), response.body().getName() + " alterado com sucesso", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getActivity(), "Ocorreu um erro na inclusão", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
