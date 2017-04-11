package ro.pub.cs.systems.eim.lab06.singlethreadedserver.network;

import android.util.Log;
import android.widget.EditText;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import ro.pub.cs.systems.eim.lab06.singlethreadedserver.general.Constants;
import ro.pub.cs.systems.eim.lab06.singlethreadedserver.general.Utilities;

/**
 * Created by student on 11.04.2017.
 */

public class CommunicationThread extends Thread {
    Socket socket;
    EditText et;

    CommunicationThread(Socket socket, EditText et) {
        this.socket = socket;
        this.et = et;
    }

    @Override
    public void run() {
        // TODO exercise 5c
        // simulate the fact the communication routine between the server and the client takes 3 seconds
        try {
            Thread.sleep(3000);
        } catch (InterruptedException interruptedException) {
            Log.e(Constants.TAG, interruptedException.getMessage());
            if (Constants.DEBUG) {
                interruptedException.printStackTrace();
            }
        }
        try {
            PrintWriter printWriter = Utilities.getWriter(socket);
            printWriter.println(et.getText().toString());
            socket.close();
            Log.v(Constants.TAG, "Connection closed");
        }
        catch (IOException e) {
            Log.e(Constants.TAG, "An exception has occurred: " + e.getMessage());
            if (Constants.DEBUG) {
                e.printStackTrace();
            }
        }
        // TODO exercise 5d
        // move the communication routine between the server and the client on a separate thread (each)
    }
}
