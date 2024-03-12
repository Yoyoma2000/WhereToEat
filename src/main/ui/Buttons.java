package ui;

public enum Buttons {
    SEARCH("search"),
    LIST("list"),
    RANDOM("random"),
    SORT("sort"),
    INFO("info"),
    ADD("add"),
    SAVE("save"),
    LOAD("load"),
    QUIT("quit");

    private final String name;

    Buttons(String name) {
        this.name = name;
    }

    public String getValue() {
        return name;
    }
}
