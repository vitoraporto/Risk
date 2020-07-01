package com.company.tests;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import com.company.Match;

public class MatchTest {

    @Test
    void matchTestInvalidPlayerNumber() {
        Match match = null;
        try {
            match = new Match(-1);
            fail("Should throw exception because of number of players negative");
        } catch (Exception e) {
        }
        try {
            match = new Match(1);
            fail("Should throw exception because of number of players equal to 2");
        } catch (Exception e) {
        }
        try {
            match = new Match(2);
            fail("Should throw exception because of number of players equal to 2");
        } catch (Exception e) {
        }
        try {
            match = new Match(7);
            fail("Should throw exception because of number of players bigger than 6");
        } catch (Exception e) {
        }
        try {
            match = new Match(9);
            fail("Should throw exception because of number of players bigger than 6");
        } catch (Exception e) {
        }
    }

    @Test
    void matchTestValidPlayerNumber() {
        Match match = null;
        try {
            match = new Match(3);
            assertEquals(3, match.getPlayers().size());
            assertEquals(0, match.getPlayersLost().size());
        } catch (Exception e) {
            fail("Should not throw exception because of number of players");
        }
        try {
            match = new Match(5);
            assertEquals(5, match.getPlayers().size());
            assertEquals(0, match.getPlayersLost().size());
        } catch (Exception e) {
            fail("Should not throw exception because of number of players");
        }
        try {
            match = new Match(6);
            assertEquals(6, match.getPlayers().size());
            assertEquals(0, match.getPlayersLost().size());
        } catch (Exception e) {
            fail("Should not throw exception because of number of players");
        }
    }
}
