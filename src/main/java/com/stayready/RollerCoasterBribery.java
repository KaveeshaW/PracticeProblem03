package com.stayready;

import java.util.HashMap;

public class RollerCoasterBribery {

    public String minimumBribes(String q) {
        String qWithoutSpaces = q.replaceAll(" ", "");
        int queueLength = qWithoutSpaces.length();
        String originalOrder = computeOriginalOrdering(queueLength);

        String minimumBribes = changeBribeQueueUntilItMatchesOriginalQueue(qWithoutSpaces, originalOrder);
        return minimumBribes;
    }

    private static String computeOriginalOrdering(int lengthOfQueue) {
        StringBuilder originalOrdering = new StringBuilder();
        for(int index = 1; index <= lengthOfQueue; index++) {
            originalOrdering.append(index);
        }
        return originalOrdering.toString();
    }

    private static String changeBribeQueueUntilItMatchesOriginalQueue(String bribeQueue, String originalQueue) {
        int fixedIndex = 0;
        int changingIndex = 1;
        int numBribes = 0;
        char fixedPositionTicket = bribeQueue.charAt(fixedIndex);
        char changingPositionTicket = bribeQueue.charAt(changingIndex);
        HashMap<Character, Integer> maxBribesPerPosition = new HashMap<>();
        boolean exceededLimit = false;

        while(!bribeQueue.equals(originalQueue)) {
            if(fixedPositionTicket > changingPositionTicket) {
                maxBribesPerPosition.merge(bribeQueue.charAt(changingIndex - 1), 1, Integer::sum);
                exceededLimit = maxBribesPerPosition.get(bribeQueue.charAt(changingIndex - 1)) > 2;
                bribeQueue = swapPositions(bribeQueue, changingIndex - 1, changingIndex);
                numBribes++;
                changingIndex = fixedIndex + 1;
            }
            else {
                changingIndex++;
            }
            fixedIndex = bribeQueue.charAt(fixedIndex) == originalQueue.charAt(fixedIndex) ? fixedIndex + 1 : fixedIndex;
            changingPositionTicket = bribeQueue.charAt(changingIndex);
            fixedPositionTicket = bribeQueue.charAt(fixedIndex);
        }
        return exceededLimit ? "Too Chaotic" : String.valueOf(numBribes);
    }

    private static String swapPositions(String input, int before, int after) {
        char[] positionArray = input.toCharArray();
        Character tempPosition = positionArray[before];
        positionArray[before] = positionArray[after];
        positionArray[after] = tempPosition;
        return String.valueOf(positionArray);
    }
}
