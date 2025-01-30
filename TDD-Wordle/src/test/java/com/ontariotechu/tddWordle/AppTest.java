package com.ontariotechu.tddWordle;

import java.util.List;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Testing the selection of a random target word from a predefined list of valid words
     */
    @Test
    public void testRandomTargetWordSelection() {
        WordleGame game = new WordleGame();
        String targetWord = game.getTargetWord();
        assertNotNull(targetWord);
        assertEquals(5, targetWord.length());
        assertTrue(WordleGame.VALID_WORDS.contains(targetWord));
    }
    /**
     * Testing the accepting inputs functionalit
     */
    @Test
    public void testPlayerGuessInput() {
        WordleGame game = new WordleGame();
        String playerGuess = "CRANE";
        boolean isValidGuess = game.submitGuess(playerGuess);
        assertTrue(isValidGuess);
    }
    /**
     * Testing feedback after user guesses
     */
    @Test
    public void testFeedbackAfterGuess() {
        WordleGame game = new WordleGame();
        game.setTargetWord("CRANE");
        String playerGuess = "CRATE";
        game.submitGuess(playerGuess);
        String feedback = game.getFeedback();
        assertNotNull(feedback);
        assertEquals(5, feedback.length());
    }
    /**
     * Testing guess limit
     */
    @Test
    public void testMaximumAttempts() {
        WordleGame game = new WordleGame();
        for (int i = 0; i < 6; i++) {
            game.submitGuess("GUESS");
        }
        assertFalse(game.submitGuess("EXTRA"));
        assertTrue(game.isGameOver());
    }
    /**
     * Testing game win function
     */
    @Test
    public void testGameEndsOnCorrectGuess() {
        WordleGame game = new WordleGame();
        game.setTargetWord("CRANE");
        game.submitGuess("CRANE");
        assertTrue(game.isGameOver());
        assertTrue(game.isPlayerWinner());
    }
    /**
     * Testing game loss function
     */
    @Test
    public void testDisplayCorrectWordOnFailure() {
        WordleGame game = new WordleGame();
        game.setTargetWord("CRANE");
        for (int i = 0; i < 6; i++) {
            game.submitGuess("GUESS");
        }
        assertTrue(game.isGameOver());
        assertEquals("CRANE", game.getTargetWord());
    }
    /**
     * Testing invalid word handling
     */
    @Test
    public void testInvalidGuessRejection() {
        WordleGame game = new WordleGame();
        String invalidGuess = "ABCDE";
        boolean isValidGuess = game.submitGuess(invalidGuess);
        assertFalse(isValidGuess);
    }
}
