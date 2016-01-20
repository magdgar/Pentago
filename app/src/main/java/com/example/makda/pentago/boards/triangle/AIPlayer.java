package com.example.makda.pentago.boards.triangle;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Makda on 2016-01-19.
 */
public class AIPlayer {
    TriangleBoardView board;
    List<TriangleSegmentView> segments;

    public AIPlayer(TriangleBoardView board) {
        this.board = board;
        segments = new LinkedList<>();
        segments.addAll(Arrays.asList(board.getSegmentViews()));
        segments.addAll(Arrays.asList(board.getRotatedSegmentViews()));
    }
    public void makeMoves(){
        makeAIMove();
        makeRotexMove();
    }

    private void makeAIMove(){

        Random rand =  new Random();
        int segmentId = rand.nextInt(6);
        int rectId = rand.nextInt(10);
        while(segments.get(segmentId).squares.get(rectId).isSelected()){
            segmentId = rand.nextInt(6);
            rectId = rand.nextInt(10);
        }
        segments.get(segmentId).squares.get(rectId).setColor( -16711936);
        int rotation = (rand.nextInt(2) - 1 > 0) ? 1 : -1;
        segments.get(segmentId).addToPermutationID(rotation);
        board.refreshRectangleColors();
        segments.get(segmentId).invalidate();
    }

    private void makeRotexMove(){
        Random rand =  new Random();
        int rectId =rand.nextInt(10);

        if(board.segmentView != null && !board.segmentView.squares.get(rectId).isSelected()){
            board.segmentView.squares.get(rectId).setColor( -65281);
            int rotation = (rand.nextInt(2) - 1 > 0) ? 1 : -1;
            board.segmentView.addToPermutationID(rotation);
            board.refreshRectangleColors();
            board.segmentView.invalidate();
        } else {
            int segmentId = rand.nextInt(6);
            rectId = rand.nextInt(10);
            while (segments.get(segmentId).squares.get(rectId).isSelected()) {
                segmentId = rand.nextInt(6);
                rectId = rand.nextInt(10);
            }
            segments.get(segmentId).squares.get(rectId).setColor(-65281);
            int rotation = (rand.nextInt(2) - 1 > 0) ? 1 : -1;
            segments.get(segmentId).addToPermutationID(rotation);
            board.refreshRectangleColors();
            segments.get(segmentId).invalidate();
        }
    }
}
