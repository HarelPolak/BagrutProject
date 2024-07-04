package com.example.bagrutproject.utils;

import com.example.bagrutproject.core.MainActivity;

public class UtilScrambles {

    static String[][] moves2x2 = {{"R", "R\'", "R2"},{"F", "F\'", "F2"},{"U", "U\'", "U2"}};
    static String[][] moves3x3 = {{"R", "R\'", "R2"},{"L", "L\'", "L2"},{"F", "F\'", "F2"},{"B", "B\'", "B2"},{"U", "U\'", "U2"},{"D", "D\'", "D2"}};
    static String[][] moves4x4 = {{"R", "R\'", "R2"}, {"Rw", "Rw\'", "Rw2"}, {"L", "L\'", "L2"},{"F", "F\'", "F2"}, {"Fw", "Fw\'", "Fw2"}, {"B", "B\'", "B2"},{"U", "U\'", "U2"}, {"Uw", "Uw\'", "Uw2"}, {"D", "D\'", "D2"}};

    public static String generateScramble2x2(){
        int scrambleLength = (int)(Math.random()*3+9), coulmn, prevCoulmn=0;
        String randomMove;
        String[] scramble = new String[scrambleLength];
        for(int i=0; i<scrambleLength; i++){
            coulmn = (int)(Math.random()*3);
            randomMove = moves2x2[coulmn][(int)(Math.random()*3)];
            if(i!=0){
                while(coulmn==prevCoulmn){
                    coulmn = (int)(Math.random()*3);
                    randomMove = moves2x2[coulmn][(int)(Math.random()*3)];
                }
            }
            prevCoulmn = coulmn;
            scramble[i] = randomMove;
        }
        return String.join(" ", scramble);
    }

    public static String generateScramble3x3(){
        int scrambleLength = (int)(Math.random()*3+18), coulmn, prevCoulmn=0;
        String randomMove;
        String[] scramble = new String[scrambleLength];
        for(int i=0; i<scrambleLength; i++){
            coulmn = (int)(Math.random()*6);
            randomMove = moves3x3[coulmn][(int)(Math.random()*3)];
            if(i!=0){
                while((coulmn==prevCoulmn) || (coulmn==0 && prevCoulmn==1) || (coulmn==2 && prevCoulmn==3) || (coulmn==4 && prevCoulmn==5)){
                    coulmn = (int)(Math.random()*6);
                    randomMove = moves3x3[coulmn][(int)(Math.random()*3)];
                }
            }
            prevCoulmn = coulmn;
            scramble[i] = randomMove;
        }
        return String.join(" ", scramble);
    }

    public static String generateScramble4x4(){
        int scrambleLength = (int)(Math.random()*3+43), coulmn, prevCoulmn=0;
        String randomMove;
        String[] scramble = new String[scrambleLength];
        for(int i=0; i<scrambleLength; i++){
            coulmn = (int)(Math.random()*9);
            randomMove = moves4x4[coulmn][(int)(Math.random()*3)];
            if(i!=0){
                while((coulmn==prevCoulmn) || (coulmn==0 && prevCoulmn==2) || (coulmn==1 && prevCoulmn==2) || (coulmn==3 && prevCoulmn==5) || (coulmn==4 && prevCoulmn==5) || (coulmn==6 && prevCoulmn==8) || (coulmn==7 && prevCoulmn==8)){
                    coulmn = (int)(Math.random()*9);
                    randomMove = moves4x4[coulmn][(int)(Math.random()*3)];
                }
            }
            prevCoulmn = coulmn;
            scramble[i] = randomMove;
        }
        return String.join(" ", scramble);
    }

    public static String generateScramble(){
        String scramble="";
        if(MainActivity.cubeType == 0){
            scramble = generateScramble2x2();
        }
        else if(MainActivity.cubeType == 1){
            scramble = generateScramble3x3();
        }
        else if(MainActivity.cubeType == 2){
            scramble = generateScramble4x4();
        }
        return scramble;
    }
}
