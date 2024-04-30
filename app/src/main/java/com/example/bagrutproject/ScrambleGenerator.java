package com.example.bagrutproject;

public class ScrambleGenerator {

    static String[] moves3x3 = {"R", "R\'", "R2","L", "L\'", "L2","F", "F\'", "F2","B", "B\'", "B2","U", "U\'", "U2","D", "D\'", "D2"};

    public static String generateScramble3x3(){
        int scrambleLength = (int)(Math.random()*3+18);
        String randomMove;
        char moveLetter, prevMoveLetter;
        String[] scramble = new String[scrambleLength];
        for(int i=0; i<scrambleLength; i++){
            randomMove = moves3x3[(int)(Math.random()*18)];
            if(i!=0){
                moveLetter = randomMove.charAt(0);
                prevMoveLetter = scramble[i-1].charAt(0);
                while((moveLetter==prevMoveLetter) || (moveLetter=='R' && prevMoveLetter=='L') || (moveLetter=='F' && prevMoveLetter=='B') || (moveLetter=='U' && prevMoveLetter=='D')){
                    randomMove = moves3x3[(int)(Math.random()*18)];
                    moveLetter = randomMove.charAt(0);
                }
            }
            scramble[i] = randomMove;
        }
        return String.join(" ", scramble);
    }
}
