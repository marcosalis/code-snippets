package com.github.marcosalis.training.snippets.concurrency.exercises;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

/**
 * Trivial web sites voting manager.
 *
 * NOT thread safe.
 *
 * Exercise: make it thread safe without modifying the public interface, keeping average
 * performances and scalability as high as possible.
 */
@SuppressWarnings("unused")
class VotingManager {

    /**
     * Get the VotingManager instance
     */
    @Nonnull
    public VotingManager getInstance() {
        if (instance == null)
            instance = new VotingManager();
        return instance;
    }

    private static VotingManager instance;

    private int votesCount;

    private Map<URI, Integer> votes;

    private VotingManager() {
        votes = new HashMap<>();
    }

    /**
     * Vote for the given URI
     */
    public void vote(URI toRate) {
        if (votes.containsKey(toRate))
            votes.put(toRate, votes.get(toRate) + 1);
        else
            votes.put(toRate, 1);
        incrementVotes();
    }

    /**
     * Get the current voting count of the given URI
     */
    public int getVotes(URI uri) {
        Integer currentVotes = votes.get(uri);
        return (currentVotes == null) ? 0 : currentVotes;
    }

    /**
     * Remove all votes for the given URI
     */
    public void delete(URI toDelete) {
        Integer deleted = votes.remove(toDelete);
        int toDecrement = (deleted == null) ? 0 : deleted;
        decrementVotes(toDecrement);
    }

    /**
     * Get an unmodifiable, unsorted view of the current votes for every URI
     */
    public Map<URI, Integer> getAllVotes() {
        return Collections.unmodifiableMap(votes);
    }

    /**
     * Print the current votes situation to the standard output
     */
    public void printVotes() {
        System.out.println(votes);
    }

    /**
     * Get the number of total votes so far
     */
    public int getVotesCount() {
        return votesCount;
    }

    private void incrementVotes() {
        votesCount++;
    }

    private void decrementVotes(int value) {
        votesCount -= value;
    }

}