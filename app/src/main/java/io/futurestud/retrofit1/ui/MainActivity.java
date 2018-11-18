package io.futurestud.retrofit1.ui;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import io.futurestud.retrofit1.R;
import io.futurestud.retrofit1.api.model.Game;
import io.futurestud.retrofit1.api.model.Move;
import io.futurestud.retrofit1.api.proxy.ProxyBuilder;
import io.futurestud.retrofit1.api.proxy.WGServerProxy;
import io.futurestud.retrofit1.api.service.GitHubClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private TextView textView;
    private static int numOfRows;
    private static int numOfCol;
    Button buttons[][];
//    Retrofit.Builder builder;
//    Retrofit retrofit;
//    GitHubClient client;
    String[] previous_play = {null};
    private static long game_number  = 1;
    private WGServerProxy proxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // Build Retrofit proxy object for server
//        retrofit = new Retrofit.Builder()
//                .baseUrl("http://10.0.2.2:8080/")
//                .addConverterFactory(GsonConverterFactory.create()).build();
//
//        client = retrofit.create(GitHubClient.class);

        proxy = ProxyBuilder.getProxy();


        Button button = (Button) findViewById(R.id.btnnewGame);
        setupNewGameButton(button);

        numOfRows = 3;
        numOfCol = 3;
        textView = (TextView)findViewById(R.id.textView3);

        Game game = new Game();
        game.setDescription("My first game!");

        Call<Game> call3 = proxy.postgames(game);
        call3.enqueue(new Callback<Game>() {
            @Override
            public void onResponse(Call<Game> call, Response<Game> response) {
                Game game = response.body();
                textView.setText("currently playing game :" + game.getDescription());
            }

            @Override
            public void onFailure(Call<Game> call, Throwable t) {

            }
        });

        buttons = new Button[numOfRows][numOfCol];
        populateButtons();
    }

    private void setupNewGameButton(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "new game", Toast.LENGTH_LONG).show();
                final Game game = new Game();
                game.setDescription("New game!");
                game_number ++;


                Call<Game> newGameCall = proxy.postgames(game);
                newGameCall.enqueue(new Callback<Game>() {
                    @Override
                    public void onResponse(Call<Game> call, Response<Game> response) {
                        previous_play[0] = null;
                        textView.setText("new game");

                        for (int row = 0; row < numOfRows; row++){
                            for (int col = 0; col < numOfCol; col++){
                                Button button = buttons [row][col];
                                lockButtonSizes();

                                //Scale Image to button
                                int newWidth = button.getWidth();
                                int newHeight = button.getHeight();
                                Bitmap originalBitmap;
                                originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.blank);


                                Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
                                Resources resource = getResources();
                                button.setBackground(new BitmapDrawable(resource, scaledBitmap));
                                button.setBackground(new BitmapDrawable(resource, scaledBitmap));
                            }
                        }

                        // Lock Button Sizes: before scaling the buttons



                    }

                    @Override
                    public void onFailure(Call<Game> call, Throwable t) {

                    }
                });

            }
        });
    }

    private void populateButtons() {
        TableLayout table = (TableLayout) findViewById(R.id.tableForButtons);

        for (int row = 0; row < numOfRows; row++){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            ));
            table.addView(tableRow);

            for (int col = 0; col < numOfCol; col++){
                final Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                //make text not clip on small buttons
                button.setPadding(0,0,0,0);
                final int finalCol = col;
                final int finalRow = row;

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gridButtonClicked(finalCol, finalRow);
                    }
                });


                tableRow.addView(button);
                buttons[row][col] = button;
            }
        }
    };

    private void gridButtonClicked(final int col, final int row) {

        final Move move = new Move();

        if(previous_play[0] == null) {
            move.setPiece("X");
        } else {
            if(previous_play[0].equals("X")) {
                move.setPiece("O");
            } else {
                move.setPiece("X");
            }
        }
        move.setRow(row);
        move.setCol(col);
        Call<Move> call = proxy.makeMove(game_number, move);
        call.enqueue(new Callback<Move>() {
            @Override
            public void onResponse(Call<Move> call, Response<Move> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Game is finished", Toast.LENGTH_LONG).show();
                } else {
                    Move move = response.body();

                    previous_play[0] = move.getPiece();

                    final int server_row = move.getRow();
                    final int server_col = move.getCol();
                    Button button = buttons [server_row][server_col];

                    // Lock Button Sizes: before scaling the buttons
                    lockButtonSizes();

                    //Scale Image to button
                    int newWidth = button.getWidth();
                    int newHeight = button.getHeight();
                    Bitmap originalBitmap;
                    if(move.getPiece().equals("O")) {
                        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.circle);
                    } else {
                        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cross);
                    }

                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
                    Resources resource = getResources();
                    button.setBackground(new BitmapDrawable(resource, scaledBitmap));
                    button.setBackground(new BitmapDrawable(resource, scaledBitmap));


                    Call<Game> checkStatusCall = proxy.getGame(game_number);
                    checkStatusCall.enqueue(new Callback<Game>() {
                        @Override
                        public void onResponse(Call<Game> call, Response<Game> response) {
                            if(!response.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Game is finished",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Game game = response.body();
                                if(!game.getGameState().equals("PLAYING")){
                                    textView.setText(game.getGameState());
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<Game> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Move> call, Throwable t) {

            }
        });

    }

    private void lockButtonSizes() {
        for (int row = 0; row < numOfRows; row++){
            for (int col = 0; col < numOfCol; col++){
                Button button = buttons[row][col];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }


}
