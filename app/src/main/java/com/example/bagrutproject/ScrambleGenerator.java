package com.example.bagrutproject;

public class ScrambleGenerator {

    static String[][] moves3x3 = {{"R", "R\'", "R2"},{"L", "L\'", "L2"},{"F", "F\'", "F2"},{"B", "B\'", "B2"},{"U", "U\'", "U2"},{"D", "D\'", "D2"}};
    static String[][] moves2x2 = {{"R", "R\'", "R2"},{"L", "L\'", "L2"},{"F", "F\'", "F2"},{"B", "B\'", "B2"},{"U", "U\'", "U2"},{"D", "D\'", "D2"}};

//    public static String generateScramble2x2(){
//        int scrambleLength = (int)(Math.random()*3+18), coulmn, prevCoulmn=0;
//    }

    public static String generateScramble3x3(){
        int scrambleLength = (int)(Math.random()*3+18), coulmn, prevCoulmn=0;
        String randomMove;
        String[] scramble = new String[scrambleLength];
        for(int i=0; i<scrambleLength; i++){
            coulmn = (int)(Math.random()*6);
            randomMove = moves3x3[coulmn][(int)(Math.random()*3)];
            if(i!=0){
                while((coulmn==prevCoulmn) || (coulmn==0 && prevCoulmn==1) || (coulmn==2 && prevCoulmn==3) || (coulmn==4 && coulmn==5)){
                    coulmn = (int)(Math.random()*6);
                    randomMove = moves3x3[coulmn][(int)(Math.random()*3)];
                }
            }
            prevCoulmn = coulmn;
            scramble[i] = randomMove;
        }
        return String.join(" ", scramble);
    }

    public static String generateScramble2x2(){
        int scrambleLength = (int)(Math.random()*3+9), coulmn, prevCoulmn=0;
        String randomMove;
        String[] scramble = new String[scrambleLength];
        for(int i=0; i<scrambleLength; i++){
            coulmn = (int)(Math.random()*6);
            randomMove = moves2x2[coulmn][(int)(Math.random()*3)];
            if(i!=0){
                while((coulmn==prevCoulmn) || (coulmn==0 && prevCoulmn==1) || (coulmn==1 && prevCoulmn==0) || (coulmn==2 && prevCoulmn==3) || (coulmn==3 && prevCoulmn==2) || (coulmn==4 && coulmn==5) || (coulmn==5 && prevCoulmn==4)){
                    coulmn = (int)(Math.random()*6);
                    randomMove = moves2x2[coulmn][(int)(Math.random()*3)];
                }
            }
            prevCoulmn = coulmn;
            scramble[i] = randomMove;
        }
        return String.join(" ", scramble);
    }


}
