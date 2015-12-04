package gr.dasoftware.flickrapp.Models;

import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

enum DownloadStatus {
    IDLE, PROCESSING, NOT_INITIALISED, FAILED_OR_EMPTY, OK
}

public class GetRawData {
    private String LOG_TAG = GetRawData.class.getSimpleName();
    private String mRawUrl;
    private String mData;
    private DownloadStatus mDownloadStatus;

    public GetRawData(String mRawUrl) {
        this.mRawUrl = mRawUrl;
        this.mDownloadStatus = DownloadStatus.IDLE;
    }

    public void reset() {
        this.mDownloadStatus = DownloadStatus.IDLE;
        this.mRawUrl = null;
        this.mData = null;
    }

    public String getmData() {
        return mData;
    }

    public DownloadStatus getmDownloadStatus() {
        return mDownloadStatus;
    }

    public void execute() {
        this.mDownloadStatus = DownloadStatus.PROCESSING;
        DownloadRawData task = new DownloadRawData();
        task.execute(mRawUrl);
    }

    public void setmRawUrl(String mRawUrl) {
        this.mRawUrl = mRawUrl;
    }

    public class DownloadRawData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String webData) {
            super.onPostExecute(webData);

            mData = webData;

            if (mData == null) {
                if (mRawUrl == null)
                    mDownloadStatus = DownloadStatus.NOT_INITIALISED;
                else
                    mDownloadStatus = DownloadStatus.FAILED_OR_EMPTY;
            } else
                mDownloadStatus = DownloadStatus.OK;
        }

        @Override
        protected String doInBackground(String... urls) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            if (urls == null)
                return null;
            else {
                try {
                    URL url = new URL(urls[0]);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();

                    InputStream in = connection.getInputStream();

                    if (in == null) {
                        return null;
                    }

                    StringBuffer buffer = new StringBuffer();
                    reader = new BufferedReader(new InputStreamReader(in));

                    String line;

                    while ((line = reader.readLine()) != null) {
                        buffer.append(line + "\n");
                    }

                    return buffer.toString();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "error", e);
                    return null;
                } finally {
                    if (connection != null)
                        connection.disconnect();
                    if (reader != null)
                        try {
                            reader.close();
                        } catch (final IOException e) {
                            Log.e(LOG_TAG, "error", e);
                        }
                }
            }
        }
    }
}
