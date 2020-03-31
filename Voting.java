package com.islam;

import java.util.HashMap;
import java.util.HashSet;

public class Voting {
    public static final int ONE_MODE = 0;
    public static final int MULTI_MODE = 1;
    private int type;
    private String question;
    public String name;
    private HashMap<Integer,String> chooses;
    private HashMap<Integer,Integer> score;
    public int id;
    private HashSet<Person> voters;
    private HashMap<Person, int[]> polls;

    Voting(String name,int id){
        this.id = id;
        this.name = name;
        chooses = new HashMap<>();
        voters = new HashSet<>();
        polls = new HashMap<>();
        score = new HashMap<>();
    }
    public void addChose(String str){
        chooses.put(chooses.size()+1,str);
    }
    public boolean addPerson(Person person){
        return voters.add(person);
    }


    public HashMap<Integer, String> getChooses() {
        return chooses;
    }

    public HashSet<Person> getVoters() {
        return voters;
    }
    public void makeQuestion(String question,int type,String ... chooses){
        this.question = question;
        this.type = type;
        for (String node: chooses) {
            this.chooses.put(this.chooses.size()+1,node);
        }
    }
    public void vote(Person person,int[] chose){
        if (type==ONE_MODE && chose.length > 1)
            return;
        if (addPerson(person)){
            polls.put(person,chose);
            for (int i:chose) {
                int scores;
                if ( score.get(i)!=null){
                    scores = score.get(i);
                    score.replace(i,scores);
                }else
                score.put(i,0);
            }
        }
    }

    public String getQuestion() {
        return question;
    }

    public HashMap<Person, int[]> getPolls() {
        return polls;
    }

    public HashMap<Integer, Integer> getScore() {
        return score;
    }
    public void printResult(){
        for (int i:chooses.keySet()) {
        System.out.println(chooses.get(i) + " " + score.get(i));
        }
    }
}
