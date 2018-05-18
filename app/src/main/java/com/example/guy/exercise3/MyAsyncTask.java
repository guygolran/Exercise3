package com.example.guy.exercise3;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

public class MyAsyncTask extends AsyncTask<Void, String, String> {

    protected final static String FINISH = "FINISH";
    protected final static String CREATED = "AsyncTask thread created";
    protected final static String CANCELED = "AsyncTask thread canceled";
    protected final static String FINISHED = "AsyncTask thread finished";
    private int counter;
    private Context context;
    private TextView textview;

    public MyAsyncTask(Context context, TextView textview) {
        this.context = context;
        this.textview = textview;
        textview.setText(CREATED);

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        counter = 0;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        textview.setText(values[0]);
    }

    @Override
    protected String doInBackground(Void... voids) {
        if (counter >= 0 || counter <= 10) {
            for (int i = 0; i < 10; i++) {
                try {
                    this.publishProgress(Integer.toString(i));
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }
        return FINISH;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        textview.setText(CANCELED);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        textview.setText(FINISHED);
    }
}
