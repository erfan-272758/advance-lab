package com.islam;

import java.util.ArrayList;
import java.util.List;

public class VotingSystem {
    ArrayList<Voting> votingList = new ArrayList<>();

    void creatVoting(String name, int i, String question, int type) {
        Voting vote = new Voting(name, i);
        vote.makeQuestion(question, type);
        votingList.add(vote);
    }

    void vote(int i, String fname, String lname, int[] j) {
        Person person = new Person(fname, lname);
        for (Voting node : votingList) {
            if (node.id == i) {
                node.vote(person, j);
            }
        }
    }

    void printListOfVoting() {
        for (Voting node : votingList) {
            System.out.println(node.getQuestion());
            for (int i : node.getChooses().keySet()) {
                System.out.println(node.getChooses().get(i));
            }
        }

    }

    void printVoting(int c) {
        for (Voting node : votingList) {
            if (node.id == c) {
                System.out.println(node.getQuestion());
                for (int i : node.getChooses().keySet()) {
                    System.out.println(node.getChooses().get(i));
                }
            }
        }

    }

    void printResult(int c) {
        for (Voting node : votingList) {
            if (node.id == c)
                node.printResult();
        }
    }
}
