package com.example.techtask;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.techtask.classes.JSON;
import com.example.techtask.classes.JSONTags;
import com.example.techtask.classes.Tags;
import com.example.techtask.classes.UserAdapter;
import com.example.techtask.classes.Users;
import com.example.techtask.interfaces.ApiInterface;
import com.example.techtask.interfaces.ApiInterfaceTags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    UserAdapter userAdapter;
    List<Users> usersList = new ArrayList<>();
    List<Tags> tagsList = new ArrayList<>();

    List<Integer> reputationList = new ArrayList<>();
    List<Integer> locationList = new ArrayList<>();
    List<Integer> answersList = new ArrayList<>();
    List<Integer> tagList = new ArrayList<>();

    String text;
    int key = 0, k = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.stackexchange.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<JSON> call = apiInterface.getUsers();
        call.enqueue(new Callback<JSON>() {
            @Override
            public void onResponse(@NonNull Call<JSON> call, @NonNull Response<JSON> response) {
                JSON json = response.body();
                usersList = new ArrayList<>(Arrays.asList(json.getUsersArray()));
                ListTags(usersList);
                putData(usersList);
            }

            @Override
            public void onFailure(@NonNull Call<JSON> call, @NonNull Throwable t) {

            }
        });

    }

    public void ListTags(List<Users> userList) {
        for (int i = 0; i < userList.size(); i++) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.stackexchange.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            String url = "2.3/users/" + usersList.get(i).getUser_id() + "/tags?order=desc&sort=popular&site=stackoverflow";
            ApiInterfaceTags service = retrofit.create(ApiInterfaceTags.class);
            Call<JSONTags> call2 = service.getTags(url);
            call2.enqueue(new Callback<JSONTags>() {
                @Override
                public void onResponse(@NonNull Call<JSONTags> call, @NonNull Response<JSONTags> response) {
                    JSONTags jsonTags = response.body();
                    text = " ";
                    tagsList = new ArrayList<>(Arrays.asList(jsonTags.getTagsArray()));
                    for (int j = 0; j < tagsList.size(); j++) {
                        text += (tagsList.get(j).getNameTag() + ", ");
                    }
                    newData(text);
                    tagsList.clear();
                }

                @Override
                public void onFailure(@NonNull Call<JSONTags> call, @NonNull Throwable t) {

                }
            });
        }
    }

    public void newData(String text1) {
        usersList.get(key).setTag(text1);
        key++;
    }

    public void putData(List<Users> userList) {
        userAdapter = new UserAdapter(userList, this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(userAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.findAll:
                putData(usersList);
                k = 0;
                return true;
            case R.id.findLocation:
                firstCheck();
                k = 1;
                return true;
            case R.id.findReputation:
                secondCheck();
                k = 2;
                return true;
            case R.id.findAnswered:
                thirdCheck();
                k = 3;
                return true;
            case R.id.findTags:
                fourthCheck();
                k = 4;
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fourthCheck() {
        tagList.clear();
        checkValueTag(usersList);
        if (tagList.isEmpty()) {
            Toast.makeText(this, R.string.noTag, Toast.LENGTH_SHORT).show();
        } else {
            System.out.println(getResources().getString(R.string.yesTag));
            findNeed(tagList);
            Toast.makeText(this, R.string.yesTag, Toast.LENGTH_SHORT).show();

        }
    }

    private void thirdCheck() {
        answersList.clear();
        checkValueAnswered(usersList);
        if (answersList.isEmpty()) {
            Toast.makeText(this, R.string.noAnswered, Toast.LENGTH_SHORT).show();
        } else {
            System.out.println(getResources().getString(R.string.yesAnswered));
            findNeed(answersList);
            Toast.makeText(this, R.string.yesAnswered, Toast.LENGTH_SHORT).show();
        }
    }

    private void secondCheck() {
        reputationList.clear();
        checkValueReputation(usersList);
        if (reputationList.isEmpty()) {
            Toast.makeText(this, R.string.noReputation, Toast.LENGTH_SHORT).show();
        } else {
            System.out.println(getResources().getString(R.string.yesReputation));
            findNeed(reputationList);
            Toast.makeText(this, R.string.yesReputation, Toast.LENGTH_SHORT).show();
        }
    }

    public void firstCheck() {
        locationList.clear();
        checkValueLocation(usersList);
        if (locationList.isEmpty()) {
            Toast.makeText(this, R.string.noLocation, Toast.LENGTH_SHORT).show();
        } else {
            System.out.println(getResources().getString(R.string.yesLocation));
            findNeed(locationList);
            Toast.makeText(this, R.string.yesLocation, Toast.LENGTH_SHORT).show();
        }
    }

    public void findNeed(List<Integer> needList) {
        List<Users> locList = new ArrayList<>();
        for (int i = 0; i < needList.size(); i++) {
            Users user = new Users();
            user.setName(usersList.get(needList.get(i)).getName());
            user.setTag(usersList.get(needList.get(i)).getTag());
            user.setLocation(usersList.get(needList.get(i)).getLocation());
            user.setAnswer(usersList.get(needList.get(i)).getAnswer());
            user.setQuestion(usersList.get(needList.get(i)).getQuestion());
            user.setProfile(usersList.get(needList.get(i)).getProfile());
            user.setAvatar(usersList.get(needList.get(i)).getAvatar());
            user.setReputation(usersList.get(needList.get(i)).getReputation());
            user.setUser_id(usersList.get(needList.get(i)).getUser_id());
            locList.add(user);
            System.out.println(usersList.get(needList.get(i)).getName());
        }

        putData(locList);
    }

    public void checkValueAnswered(List<Users> newList) {
        for (int i = 0; i < newList.size(); i++) {
            int answers = newList.get(i).getAnswer();
            if (answers >= 1) {
                answersList.add(i);
            }
        }
    }

    public void checkValueReputation(List<Users> newList) {
        for (int i = 0; i < newList.size(); i++) {
            int reputation = newList.get(i).getReputation();
            if (reputation >= 233) {
                reputationList.add(i);
            }
        }
    }

    public void checkValueLocation(List<Users> newList) {
        for (int i = 0; i < newList.size(); i++) {
            String location = newList.get(i).getLocation();
            if (location != null) {
                if (location.toLowerCase().contains("romania") || location.toLowerCase().contains("moldova")) {
                    locationList.add(i);
                }
            }
        }
    }

    public void checkValueTag(List<Users> newList) {
        for (int i = 0; i < newList.size(); i++) {
            String tags = newList.get(i).getTag();
            if (tags != null) {
                if (tags.contains("java") || tags.contains(".net") || tags.contains("docker") || tags.contains("С#")) {
                    tagList.add(i);
                }
            }
        }
    }

}
