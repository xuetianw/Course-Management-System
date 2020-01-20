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
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

//    private static final String TAG = "ApiKeyAct";
    private static final String TAG = "TAG";

    private ListView listView;
    private TextView textView ;
    private static int numOfRows = 3;
    private static int numOfCol = 3;;
    Button[][] buttons;


    char previous_play;
    private static long game_number = 0;
    private WGServerProxy proxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView3);

        proxy = ProxyBuilder.getProxy();

        Button new_game_btn = (Button) findViewById(R.id.btnnewGame);
        setupNewGameButton(new_game_btn);

        buttons = new Button[numOfRows][numOfCol];
    }

    private void setupNewGameButton(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this.getApplicationContext(), "new game", Toast.LENGTH_LONG).show();
                final Game game = new Game();
                String description = String.format("%s game", game_number++);
                game.setDescription(description);

                Call<Game> newGameCall = proxy.postgames(game);
                ProxyBuilder.callProxy(MainActivity.this.getApplicationContext(), newGameCall, returnedKey -> MainActivity.this.new_game_response(returnedKey));
            }
        });
    }

    private void setupTableAndBts() {
        TableLayout table = (TableLayout) findViewById(R.id.tableForButtons);
        table.removeAllViews();

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

        if(previous_play == 'n') {
            move.setPiece('X');
        } else {
            if(previous_play == 'X') {
                move.setPiece('O');
            } else {
                move.setPiece('X');
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

            previous_play = response.getPiece();

            final int server_row = response.getRow();
            final int server_col = response.getCol();
            Button button = buttons [server_row][server_col];

            // Lock Button Sizes: before scaling the buttons
            lockButtonSizes();

            //Scale Image to button
            int newWidth = button.getWidth();
            int newHeight = button.getHeight();
            Bitmap originalBitmap;
            if(response.getPiece() =='O') {
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

        }
    }

    private void get_game_response(Game response) {
        if((response == null)) {
            Toast.makeText(getApplicationContext(), "Game is finished",
                    Toast.LENGTH_LONG).show();
        } else {
            Game game = response;
            textView.setText(game.getGameState());
        }
    }


    private void new_game_response(Game response) {
        notifyUserViaLogAndToast("Server replied request of starting a new game.");
        previous_play = 'n';
        textView.setText("new game");
        game_number = response.getId();

        setupTableAndBts();
    }

    private void notifyUserViaLogAndToast(String message) {
        Log.w(TAG, message);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}

