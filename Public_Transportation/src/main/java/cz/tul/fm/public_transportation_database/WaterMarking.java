/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.tul.fm.public_transportation_database;

import java.util.ArrayList;
import java.util.HashSet;

public class WaterMarking {
    
//secret sequence for selecting tuples (watermarked table rows, etc.)
    private final int[] privateSequence = {13, 55, 40, 81, 67, 37, 96, 98, 53, 34, 71, 58, 41, 46, 63, 86, 4, 43, 29, 51, 78, 68, 56, 84, 31, 15, 45, 64, 77, 11, 24, 35, 9, 89, 6, 95, 85, 50, 62};

    /**
     * Method for generating numbers of rows to be selected for watermarking
     * based on secret sequence
     *
     * @param numberOfRows stands for number of rows of table to be watermarked
     * @return uniqueList - row indices to be selected
     */
    public ArrayList<Integer> rowsToSelect(int numberOfRows) {
        ArrayList<Integer> allValues = new ArrayList<>();
        for (int i = 0; i < privateSequence.length; i++) {
            allValues.add(privateSequence[i] % numberOfRows);
        }
        ArrayList<Integer> uniqueList = new ArrayList<>(new HashSet<>(allValues));
        return uniqueList;
    }

    /**
     * Method for watermarking random numerical attributes from selected rows
     *
     * @param markableAttributes - stands for numerical attributes from selected
     * rows
     * @return markableAttributes - after watermarking
     */
    public ArrayList<ArrayList<Integer>> makeAtributeWatermark(ArrayList<ArrayList<Integer>> markableAttributes) {
        int modulation = 0;
        int selectedAttribute;
        if (markableAttributes.size() > 0) {
            modulation = markableAttributes.get(0).size();
        }
        for (int i = 0; i < markableAttributes.size(); i++) {
            int currentPrivate = privateSequence[i % privateSequence.length];
            selectedAttribute = currentPrivate % modulation;
            ArrayList<Integer> temp = markableAttributes.get(i);
            int j = temp.get(selectedAttribute);
            int marked = addWatermark(j, currentPrivate);
            temp.set(selectedAttribute, marked);
            markableAttributes.set(i, temp);
        }
        return markableAttributes;
    }

    /**
     * Method for watermarking at random bit of integer
     *
     * @param attribute - attribute to be watermarked, currenPrivate - current
     * private key for watermark
     * @return watermarkedAttr - successfully watermarked attribute
     */
    public int addWatermark(int attribute, int currentPrivate) {
        int watermarkedAttr;
        int testBinar = currentPrivate % 2;
        int randIndex = (currentPrivate % 3) + 1;
        String bin = Integer.toBinaryString(attribute);
        int lastOrPreLast = Integer.parseInt(bin.substring(bin.length() - randIndex));
        if (testBinar == lastOrPreLast) {
            StringBuilder watermarked = new StringBuilder(bin);
            watermarked.setCharAt(lastOrPreLast, '1');
            watermarkedAttr = Integer.parseInt(watermarked.toString(), 2);
        } else {
            StringBuilder watermarked = new StringBuilder(bin);
            watermarked.setCharAt(lastOrPreLast, '0');
            watermarkedAttr = Integer.parseInt(watermarked.toString(), 2);
        }
        return watermarkedAttr;
    }

    public void removeWatermark() {
        //TODO
    }

    public boolean compareData() {
        boolean isSame = true;
        //TODO
        return isSame;
    }
}
