/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.tul;

import org.springframework.data.repository.CrudRepository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;

import static java.lang.Math.abs;

public class WaterMarking {

    private int lastNEntries = 0;
    private int lastMarkHitCount = 0;
    
//secret sequence for selecting tuples (watermarked table rows, etc.)
    private int hash = "náš hash".hashCode();
    public String addWatermark(String data ) {
        if (data == null){
            return data;
        }

        String curr = data;
        curr = curr.replace("\u200b","");
        curr = curr.replace("\ufeff","");
        if (!curr.isEmpty()) {
            int currHash = abs(curr.hashCode() + hash);
            int index = currHash % curr.length();
            if (currHash % 2 == 0) {
                if (data.charAt(index) != '\u200b') {
                    data = data.substring(0, index) + '\u200b' + data.substring(index);
                }
            } else {
                if (data.charAt(index) != '\u200b') {
                    data = data.substring(0, index) + '\ufeff' + curr.substring(index);
                }
            }
        }
        return data;
    }

    public String removeWatermark(String data ) {
        if (data == null){
            return data;
        }
        String curr = data;
        StringBuilder dewatermarked = new StringBuilder(curr);

        curr = curr.replace("\u200b","");
        curr = curr.replace("\ufeff","");
        if (!curr.isEmpty()) {
            int currHash = abs(hash + curr.hashCode());
            int index = currHash % curr.length();
            if (curr.hashCode() % 2 == 0) {
                if (dewatermarked.charAt(index) == '\u200b') {
                    dewatermarked.deleteCharAt(index);
                }
            } else {
                if (dewatermarked.charAt(index) == '\ufeff') {
                    dewatermarked.deleteCharAt(index);
                }
            }
        }
        return dewatermarked.toString();
    }

    public boolean detectWatermark(String data ) {
        if (data == null){
            return true;
        }
            String curr = data;

            curr = curr.replace("\u200b", "");
            curr = curr.replace("\ufeff", "");
            if (!curr.isEmpty()) {
                int currHash = abs(hash + curr.hashCode());
                int index = currHash % curr.length();
                if (curr.hashCode() % 2 == 0) {
                    if (data.charAt(index) == '\u200b') {
                        return true;
                    }
                } else {
                    if (data.charAt(index) == '\ufeff') {
                        return true;
                    }
                }
            }
        return false;
    }


    public int getLastNEntries() {
        return lastNEntries;
    }

    public int getLastHitMissCount() {
        return lastMarkHitCount;
    }



    public boolean compareData() {
        boolean isSame = true;
        //TODO
        return isSame;
    }
}
