package com.example.rest_api_demo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rest_api_demo.firebasecallbacks.ReadCallback;
import com.example.rest_api_demo.models.Data;
import com.example.rest_api_demo.models.Movie;
import com.example.rest_api_demo.models.MovieList;
import com.example.rest_api_demo.models.Person;
import com.example.rest_api_demo.utils.APIClient;
import com.example.rest_api_demo.utils.APIInterface;
import com.example.rest_api_demo.utils.MovieRecyclerViewAdapter;
import com.example.rest_api_demo.utils.PaginationListner;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.rest_api_demo.utils.PaginationListner.PAGE_START;


public class DashboardFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, MovieRecyclerViewAdapter.OnRecycleClickListner {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;
    TextView textView;
    FirebaseComponents firebaseComponents;
    ReadCallback readCallback;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;


    private MovieRecyclerViewAdapter adapter;

    private int currentPage = PAGE_START;

    private boolean isLastPage = false;
    private int totalPage = 50;
    private boolean isLoading = false;
    List<Movie> myMovieList;

    APIInterface apiInterface;



    public DashboardFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        getActivity().setTitle("Dashboard");

        ButterKnife.bind(view);



        NavigationView navigationView = getActivity().findViewById(R.id.navigationView);
        View headerView = navigationView.getHeaderView(0);
        textView = headerView.findViewById(R.id.headerTextView);

        firebaseComponents = new FirebaseComponents(firebaseFirestore,firebaseUser);

        firebaseComponents.readFireStore(new ReadCallback() {
            @Override
            public void onCallBack(Person person) {

                textView.setText(person.getName());
                Toast.makeText(getActivity(), person.getName(), Toast.LENGTH_SHORT).show();

            }
        });


        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MovieRecyclerViewAdapter(new ArrayList<>(),this);
        recyclerView.setAdapter(adapter);

        makeApiCall();

        recyclerView.addOnScrollListener(new PaginationListner(layoutManager) {
            @Override
            protected void loadMoreItems() {

                isLoading = true;
                currentPage++;
                makeApiCall();

            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });





    }

    public void makeApiCall()
    {
        apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<MovieList> call = apiInterface.getListByYear("year",String.valueOf(currentPage));
        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                Log.d("Dashboard Fragment",""+response.body());

                MovieList movieList = response.body();
                Data data = movieList.data;
                List<Movie> movies = data.movies;
                myMovieList = movies;

                Log.d("Dashboard Fragment","Movie Size"+movies.size());
                Log.d("Dashboard Fragment","First Movie"+movies.get(0).title);


                if (currentPage != PAGE_START) adapter.removeLoading();

                adapter.addItems(movies);
                swipeRefreshLayout.setRefreshing(false);

                if (currentPage<totalPage)
                {
                    adapter.addLoading();
                }
                else {
                    isLastPage = true;
                }
                isLoading = false;
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {

                call.cancel();
                Log.d("Dashboard Fragment",""+t.getMessage());

            }
        });
    }

    @Override
    public void onRefresh() {

        currentPage = PAGE_START;
        isLastPage = false;
        adapter.clear();
        makeApiCall();

    }


    @Override
    public void onMovieClick(Movie movie) {

        Toast.makeText(getActivity().getApplicationContext(),movie.getTitle(),Toast.LENGTH_LONG).show();

    }
}
