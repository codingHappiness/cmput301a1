package com.masonstrong.mstrong_reflex;

import android.os.Environment;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Mason Strong on 10/4/2015.
 */
public class multiStats implements Serializable{
    private static multiStats instance;
    private String lastPlayer;
    private String numPlayers;
    private int[][] counts = {{0,0},{0,0,0},{0,0,0,0}};

    private multiStats() {
    }

    public static synchronized multiStats getInstance(){
        if (instance==null){
            instance = new multiStats();
        }
        return instance;
    }

    public void setLastPlayer(String value){
        this.lastPlayer = value;
    }

    public void setNumPlayers(String value){
        this.numPlayers = value;
    }

    public String getLastPlayer(){
        return this.lastPlayer;
    }

    public String getNumPlayers(){
        return this.numPlayers;
    }

    public void incrementCount(int i, int j){
        this.counts[i][j]+=1;
    }

    public int getCount(int i, int j){
        return this.counts[i][j];
    }

    public void clearStats(){
        for (int[] row : this.counts)
            Arrays.fill(row, 0);
    }

    public void storeValues()
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(Environment.getExternalStorageDirectory()+"/multi.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this); // write the class as an 'object'
            oos.flush(); // flush the stream to insure all of the information was written to 'save.bin'
            oos.close();// close the stream
            fos.close();
        }

        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void loadValues(){
        try{
            FileInputStream fis = new FileInputStream(Environment.getExternalStorageDirectory()+"/multi.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            instance = (multiStats)ois.readObject();
            ois.close();
            fis.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
            instance = new multiStats();
        }
    }
}
