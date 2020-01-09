package io.futurestud.retrofit1.ui;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

//    private static final String TAG = "ApiKeyAct";
    private static final String TAG = "TAG";

    private ListView listView;
    private TextView textView ;
    private static int numOfRows;
    private static int numOfCol;
    Button buttons[][];
    TableLayout table;
//    Retrofit.Builder builder;
//    Retrofit retrofit;
//    GitHubClient client;
    String previous_play = null;
    private static long game_number  = 1;
    private WGServerProxy proxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView3);

        proxy = ProxyBuilder.getProxy();


        Button button = (Button) findViewById(R.id.btnnewGame);
        setupNewGameButton(button);

        numOfRows = 3;
        numOfCol = 3;

        Game game = new Game();
        game.setDescription("first game!");

        Call<Game> call3 = proxy.postgames(game);
        ProxyBuilder.callProxy(getApplicationContext(), call3, returnedKey -> response(returnedKey));

        buttons = new Button[numOfRows][numOfCol];
        populateButtons();
    }

    private void setupNewGameButton(Button button) {
        button.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "new game", Toast.LENGTH_LONG).show();
            final Game game = new Game();
            game.setDescription("New game!");
            game_number++;


            Call<Game> newGameCall = proxy.postgames(game);
            ProxyBuilder.callProxy(getApplicationContext(), newGameCall, returnedKey -> new_game_response(returnedKey));
        });
    }

    private void populateButtons() {
        table = (TableLayout) findViewById(R.id.tableForButtons);

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

                button.setOnClickListener(view -> gridButtonClicked(finalCol, finalRow));


                tableRow.addView(button);
                buttons[row][col] = button;
            }
        }
    };

    private void gridButtonClicked(final int col, final int row) {

        final Move move = new Move();

        if(previous_play == null) {
            move.setPiece("X");
        } else {
            if(previous_play.equals("X")) {
                move.setPiece("O");
            } else {
                move.setPiece("X");
            }
        }
        move.setRow(row);
        move.setCol(col);
        Call<Move> call = proxy.makeMove(game_number, move);


        ProxyBuilder.callProxy(getApplicationContext(), call, returnedKey -> move_response(returnedKey));
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


    private void move_response(Move response) {
        if(response == null) {
            Toast.makeText(getApplicationContext(), "Game is finished", Toast.LENGTH_LONG).show();
        } else {
            Move move = response;

            previous_play = move.getPiece();

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
            ProxyBuilder.callProxy(getApplicationContext(), checkStatusCall, returnedKey -> get_game_response(returnedKey));
//                    checkStatusCall.enqueue(new Callback<Game>() {
//                        @Override
//                        public void onResponse(Call<Game> call, Response<Game> response) {
//                            if(!response.isSuccessful()) {
//                                Toast.makeText(getApplicationContext(), "Game is finished",
//                                        Toast.LENGTH_LONG).show();
//                            } else {
//                                Game game = response.body();
//                                if(!game.getGameState().equals("PLAYING")){
//                                    textView.setText(game.getGameState());
//                                }
//                            }
//
//                        }
//
//                        @Override
//                        public void onFailure(Call<Game> call, Throwable t) {
//
//                        }
//                    });
        }
    }

    private void get_game_response(Game response) {
        if((response == null)) {
            Toast.makeText(getApplicationContext(), "Game is finished",
                    Toast.LENGTH_LONG).show();
        } else {
            Game game = response;
            if(!game.getGameState().equals("PLAYING")){
                textView.setText(game.getGameState());
            }
        }
    }


    private void response(Game response) {
        notifyUserViaLogAndToast("Server replied to login request (no content was expected).");
        Game game = response;
        textView.setText("currently playing game :" + game.getDescription());
    }

    private void move_response(Response<Move> response) {

    }

    private void new_game_response(Game response) {
        notifyUserViaLogAndToast("Server replied request of starting a new game.");
        previous_play = null;
        textView.setText("new game");

        for (int row = 0; row < numOfRows; row++){
            for (int col = 0; col < numOfCol; col++){
                Button button1 = buttons [row][col];
//                lockButtonSizes();

                //Scale Image to button
                int newWidth = button1.getWidth();
                int newHeight = button1.getHeight();
                Bitmap originalBitmap;
                originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.blank);


                Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
                Resources resource = getResources();
                button1.setBackground(new BitmapDrawable(resource, scaledBitmap));
                button1.setBackground(new BitmapDrawable(resource, scaledBitmap));
            }
        }
    }

    private void response(Void returnedNothing) {
        notifyUserViaLogAndToast("Server replied to login request (no content was expected).");
    }

    private void notifyUserViaLogAndToast(String message) {
        Log.w(TAG, message);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}

