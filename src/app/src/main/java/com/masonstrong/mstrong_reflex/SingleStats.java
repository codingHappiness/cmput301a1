package com.masonstrong.mstrong_reflex;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;

/**
 * Created by Mason Strong on 9/20/2015.
 * This will be used to store an array of timings in milliseconds from single player results.
 * It can add to the array, clear the array, and determine the min, max, average and median
 * results in the entire array, and the last 10 entries to the array.
 */
public class SingleStats implements Serializable{

    private ArrayList<Double> statistics = new ArrayList<Double>();

    private static SingleStats instance;

    private SingleStats() {
    }

    public void addStat(Double value) {
        this.statistics.add(value);
    }

    public void clearStats() {
        this.statistics.clear();
    }

    public int getSize(){
        return this.statistics.size();
    }
    public Double min(int size) {
        if (statistics.size()!=0)
            return Collections.min(shortList(size));
        else
            return 0.0;
    }

    public Double max(int size){
        if (statistics.size()!=0)
            return Collections.max(shortList(size));
        else
            return 0.0;
    }

    //average from http://stackoverflow.com/a/10791597
    public Double average(int size){
        Double sum = 0.0;
        if(!this.statistics.isEmpty()) {
            for (Double time : this.statistics) {
                sum += time;
            }
            return sum / this.statistics.size();
        }
        return sum;
    }

    public Double median(int size){

        if (statistics.size()!=0){
            //code inspired by http://code.hammerpig.com/simple-compute-median-java.html
            List<Double> values  = shortList(size);
            Collections.sort(values);

            if (values.size() % 2 == 1)
                return values.get((values.size()+1)/2-1);
            else
            {
                double lower = values.get(values.size()/2-1);
                double upper = values.get(values.size()/2);

                return (lower + upper) / 2.0;
            }}
        else
            return 0.0;
    }

    public List<Double> shortList(int size) {
        List<Double> shorty;

        if (this.statistics.size() < size)
            shorty = this.statistics.subList(0, this.statistics.size());
        else
            shorty = this.statistics.subList(this.statistics.size() - size, this.statistics.size());

        return shorty;
    }

    //from https://androidresearch.wordpress.com/2012/03/22/defining-global-variables-in-android/
    public static synchronized SingleStats getInstance(){
        if (instance==null){
            instance = new SingleStats();
        }
        return instance;
    }

    //from http://www.dreamincode.net/forums/topic/248522-serialization-in-android/
    public void storeValues()
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(Environment.getExternalStorageDirectory()+"/single.bin");
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
            FileInputStream fis = new FileInputStream(Environment.getExternalStorageDirectory()+"/single.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            instance = (SingleStats)ois.readObject();
            ois.close();
            fis.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
            instance = new SingleStats();
        }
    }
}
