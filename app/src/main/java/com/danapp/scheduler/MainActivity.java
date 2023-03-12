package com.danapp.scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.danapp.scheduler.Models.Database.Connect;
import com.danapp.scheduler.Models.Database.Control.Schedule;
import com.danapp.scheduler.Models.Database.Control.Settings;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private EditText timeField;
    private EditText dayField;
    private TextView messageLabel;
    private Button button;
    private Handler handler = new Handler();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Connect();

        editText = findViewById(R.id.editTextTextMultiLine);
        button = findViewById(R.id.button);
        timeField = findViewById(R.id.editTextTime);
        dayField = findViewById(R.id.editTextDate);
        messageLabel = findViewById(R.id.textView4);

        LocalDateTime now = LocalDateTime.now();
        String day = now.getDayOfWeek().getValue() + "";

        String hour = now.getHour() + "";
        String minute = now.getMinute() + "";
        if (minute.length() == 1) {
            minute = "0" + minute;
        }

        String time = hour + ":" + minute;

        dayField.setText(day);
        timeField.setText(time);

        messageLabel.setText(new Settings().getMessage());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Task().execute();
            }
        });
    }

    class Task extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                LocalDateTime now = LocalDateTime.now();

                int day;
                try {
                    day = Integer.parseInt(dayField.getText().toString());
                    if (day < 1 || day > 7) {
                        day = now.getDayOfWeek().getValue();
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(MainActivity.this, "Invalid day, using actual day", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (Exception e) {
                    day = now.getDayOfWeek().getValue();
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(MainActivity.this, "Invalid day, using actual day", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                int time;
                int hour;
                int minute;

                try {
                    String[] timeSplit = timeField.getText().toString().split(":");
                    hour = Integer.parseInt(timeSplit[0]);
                    minute = Integer.parseInt(timeSplit[1]);
                } catch (Exception e) {
                    hour = now.getHour();
                    minute = now.getMinute();
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(MainActivity.this, "Invalid time, using actual time", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                if (hour == 6 && minute < 30) {
                    time = 1;
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(MainActivity.this, "Invalid time, using default time 6:30am", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (hour == 6) {
                    time = 1;
                } else if (hour == 7 && minute < 30) {
                    time = 1;
                } else if (hour == 7) {
                    time = 2;
                } else if (hour == 8 && minute < 30) {
                    time = 2;
                } else if (hour == 8) {
                    time = 3;
                } else if (hour == 9 && minute < 30) {
                    time = 3;
                } else if (hour == 9) {
                    time = 4;
                } else if (hour == 10 && minute < 30) {
                    time = 4;
                } else if (hour == 10) {
                    time = 5;
                } else if (hour == 11 && minute < 30) {
                    time = 5;
                } else if (hour == 11) {
                    time = 6;
                } else if (hour == 12 && minute < 30) {
                    time = 6;
                } else if (hour == 12) {
                    time = 7;
                } else if (hour == 13 && minute < 30) {
                    time = 7;
                } else if (hour == 13) {
                    time = 8;
                } else if (hour == 14 && minute < 30) {
                    time = 8;
                } else if (hour == 14) {
                    time = 9;
                } else if (hour == 15 && minute < 30) {
                    time = 9;
                } else if (hour == 15) {
                    time = 10;
                } else if (hour == 16 && minute < 30) {
                    time = 10;
                } else if (hour == 16) {
                    time = 11;
                } else if (hour == 17 && minute < 30) {
                    time = 11;
                } else if (hour == 17) {
                    time = 12;
                } else if (hour == 18 && minute < 30) {
                    time = 12;
                } else if (hour == 18) {
                    time = 13;
                } else if (hour == 19 && minute < 30) {
                    time = 13;
                } else if (hour == 19) {
                    time = 14;
                } else if (hour == 20 && minute < 30) {
                    time = 14;
                } else {
                    time = 1;
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(MainActivity.this, "Invalid time, using default time 6:30am", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                Schedule fs = new Schedule();
                ArrayList<String> freePeople = fs.getFreePeople(time, day, 3);
                ArrayList<String> busyPeople = fs.getBusyPeople(time, day, 3);

                String text = "";
                for (String name : freePeople) {
                    text += name + "\n";
                }
                text += "====================\n";
                for (String name : busyPeople) {
                    text += name + "\n";
                }

                String finalText = text;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        editText.setText(finalText);
                    }
                });
            } catch (Exception e) {
                try {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(MainActivity.this, "Unknown Error", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            return null;
        }
    }

}