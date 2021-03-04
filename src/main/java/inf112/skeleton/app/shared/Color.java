package inf112.skeleton.app.shared;

public enum Color {
        GREEN(0),
        GREY(1),
        ORANGE(2),
        PINK(3),
        PURPLE(4);

        public int colorValue;

        private Color(int value) {
                this.colorValue = value;
        }

        public static Color getPlayerColor(int playerNumber) {
                for (Color e : values()) {
                        if (e.colorValue == playerNumber) {
                                return e;
                        }
                }
                return null;
        }
}

