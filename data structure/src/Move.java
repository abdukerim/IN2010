public class Move {
    public static void main(String[] args) {

    }
    public static int[] computeFinalPosition(int width, int height, int[] position, int[] portalA, int[] portalB, String moves) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                for (char c : moves.toCharArray()) {
                    if(c == 'D') {
                        position[1] += 1;
                        if (position.equals(portalA)) {
                            position = portalB;
                        }
                    }
                    if(c == 'R') {
                        position[0] += 1;
                        if (position.equals(portalA)) {
                            position = portalB;
                        }
                    }
                    if(c == 'U') {
                        position[1] -= 1;
                        if (position.equals(portalA)) {
                            position = portalB;
                        }
                    }
                    if(c == 'L') {
                        position[0] -= 1;
                        if (position.equals(portalA)) {
                            position = portalB;
                        }
                    }
                }
            }
        }


        return position;
    }
}
