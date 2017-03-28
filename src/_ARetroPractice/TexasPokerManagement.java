package _ARetroPractice;

import java.util.*;

/**
 * Created by yang on 27/03/2017.
 */
public class TexasPokerManagement {
    public enum PokerFaceType {
        HIGHCARD(0),
        ONEPAIR(1),
        TWOPAIRS(2),
        TREEOFAKIND(3),
        STRAIGHT(4),
        FLUSH(5),
        FULLHOUSE(6),
        FOUROFAKIND(7),
        STRAIGHTFLUSH(8);

        private final int value;
        PokerFaceType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    // used to determine four same or three of the same
    private static int getMaxSumOfSameElements(List collections){
        int maxReplicatesNum = 1;
        int curNum = 1;
        for(int i=1;i<collections.size();i++){
            if(collections.get(i).equals(collections.get(i-1))) {
                curNum++;
                maxReplicatesNum = Math.max(maxReplicatesNum, curNum);
            }
            else
                curNum = 1;
        }
        return maxReplicatesNum;
    }
    private static List<Integer> convertValues(String []faces){
        List<Integer>  vals = new ArrayList<>();
        for(int i=0;i< faces.length;i++){
            int val = Integer.parseInt(faces[i].substring(1));
            val = val==1? 14:val;
            vals.add(val);
        }
        Collections.sort(vals);
        return vals;
    }
    private static HashSet<String> convertColors(String []faces){
        HashSet<String> colors = new HashSet<>();
        for(String face:faces){
            colors.add(face.substring(0,1));
        }
        return colors;
    }

    private static PokerFaceType determineFaceType(String []input){
        boolean isFlush = false;
        boolean isFullHouse = false;
        boolean isFourOfAKind = false;
        boolean isStraight = false;
        boolean isThreeOfAKind = false;
        boolean isTwoPairs = false;
        boolean isOnePair = false;
        HashSet<String> colors = convertColors(input);
        List<Integer> vals = convertValues(input);
        HashSet<Integer> uniqVals = new HashSet<>(vals);

        if(colors.size()==1)    isFlush = true;

        int maxReplicatesNum = getMaxSumOfSameElements(vals);

        if(uniqVals.size()==2){
            if(maxReplicatesNum==3)
                isFullHouse = true;
            else
                isFourOfAKind = true;
        }
        else if(uniqVals.size()==3) {
            if(maxReplicatesNum==3)
                isThreeOfAKind = true;
            if(maxReplicatesNum==2)
                isTwoPairs = true;
        }
        else if(uniqVals.size()==4){
            isOnePair = true;
        }
        else if(uniqVals.size()==5){
            isStraight= true;
            for(int i=0;i<vals.size()-1;i++){
                if(-vals.get(i)+vals.get(i+1)!=1)
                    isStraight=false;
            }
        }
        if(isStraight&&isFlush)
            return  PokerFaceType.STRAIGHTFLUSH;
        if(isFourOfAKind)
            return PokerFaceType.FOUROFAKIND;
        if(isFullHouse)
            return PokerFaceType.FULLHOUSE;
        if(isFlush)
            return PokerFaceType.FLUSH;
        if(isStraight)
            return  PokerFaceType.STRAIGHT;
        if(isThreeOfAKind)
            return PokerFaceType.TREEOFAKIND;
        if(isTwoPairs)
            return PokerFaceType.TWOPAIRS;
        if(isOnePair)
            return PokerFaceType.ONEPAIR;
        return PokerFaceType.HIGHCARD;
    }

    public static TexasPokerSet generatePokerSet(String []input){
        PokerFaceType faceType = determineFaceType(input);

        return new TexasPokerSet(convertValues(input), input, faceType);
    }

    public static void main(String[] args) {
        String[] poker1 = new String[]{"A1", "B1", "C1", "D2", "D3"};
        String[] poker2 = new String[]{"A1", "B1", "C1", "D2", "D2"};
        String[] poker3 = new String[]{"A1","B2", "A3", "A4", "A5"};
        String[] poker4 = new String[]{"A1","A10", "A11", "A12", "A13"};
        String[] poker5 = new String[]{"A1","A10", "B11", "A12", "A13"};
        System.out.println(TexasPokerManagement.generatePokerSet(poker1));
        System.out.println(TexasPokerManagement.generatePokerSet(poker2));
        System.out.println(TexasPokerManagement.generatePokerSet(poker3));
        System.out.println(TexasPokerManagement.generatePokerSet(poker4));
        System.out.println(TexasPokerManagement.generatePokerSet(poker5));
    }
}