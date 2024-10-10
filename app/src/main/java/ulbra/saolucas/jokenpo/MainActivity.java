package ulbra.saolucas.jokenpo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtResultado, txtScore;
    ImageView imgmaquina;
    ImageButton ImageButton;
    int wins = 0;
    int losses = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResultado = findViewById(R.id.txtResultado);
        imgmaquina = findViewById(R.id.imgmaquina);
        txtScore = findViewById(R.id.txtScore);

        ImageButton imgpedra = findViewById(R.id.imgpedra);
        ImageButton imgpapel = findViewById(R.id.imgpapel);
        ImageButton imgtesoura = findViewById(R.id.imgtesoura);
        Button btreiniciar = findViewById(R.id.btreiniciar);

        imgpedra.setOnClickListener(view -> playGame("pedra"));
        imgpapel.setOnClickListener(view -> playGame("papel"));
        imgtesoura.setOnClickListener(view -> playGame("tesoura"));

        btreiniciar.setOnClickListener(view -> resetGame());
    }

    private void playGame(String playerChoice) {
        String[] choices = {"pedra", "papel", "tesoura"};
        String machineChoice = choices[new Random().nextInt(choices.length)];
        imgmaquina.setImageResource(getImageResource(machineChoice));

        if (playerChoice.equals(machineChoice)) {
            txtResultado.setText("Empate!");
        } else if ((playerChoice.equals("pedra") && machineChoice.equals("tesoura")) ||
                (playerChoice.equals("papel") && machineChoice.equals("pedra")) ||
                (playerChoice.equals("tesoura") && machineChoice.equals("papel"))) {
            txtResultado.setText("Você venceu!");
            wins++;
        } else {
            txtResultado.setText("Você perdeu!");
            losses++;
        }
        updateScore();
    }

    private void updateScore() {
        txtScore.setText("Vitórias: " + wins + " Derrotas: " + losses);
    }

    private void resetGame() {
        wins = 0;
        losses = 0;
        txtResultado.setText("Resultado");
        updateScore();
        imgmaquina.setImageResource(R.drawable.padrao); // Assuming this is a default image
    }

    private int getImageResource(String choice) {
        switch (choice) {
            case "pedra":
                return R.drawable.pedra_icon;
            case "papel":
                return R.drawable.papel_icon;
            case "tesoura":
                return R.drawable.tesoura_icon;
            default:
                return R.drawable.padrao; // Default image
        }
    }
}