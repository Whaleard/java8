package domain;

public class Father {
    private String face; // 长相
    private String height; // 身高
    private Life life; // 生命

    public Father() {
    }

    public Father(String face, String height, Life life) {
        this.face = face;
        this.height = height;
        this.life = life;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public Life getLife() {
        return life;
    }

    public void setLife(Life life) {
        this.life = life;
    }

    @Override
    public String toString() {
        return "Father{" +
                "face='" + face + '\'' +
                ", height='" + height + '\'' +
                ", life=" + life +
                '}';
    }
}
