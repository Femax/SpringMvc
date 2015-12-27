package spring;

public class HeaderInfo {

    public int getLevel() {
        return level;
    }

    public final int level;
    public final String url;
    public final String title;

    public HeaderInfo(int level, String title, String url) {
        this.level = level;
        this.url = url;
        this.title = title;
    }

    @Override
    public String toString() {
        return "# [" +title+ "]("+ url + ")";
    }

}