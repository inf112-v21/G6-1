package inf112.skeleton.app.game;

public enum GameType {
    NONE("0"),
    NETWORK_HOST("1"),
    NETWORK_JOIN("2"),
    SINGLE_PLAYER("3");

    public final String value;

    GameType(String value) {
        this.value = value;
    }
}
