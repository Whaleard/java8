package domain;

public class Life {
    private String status;

    public Life() {
    }

    public Life(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // @Override
    // public String toString() {
    //     return "Life{" +
    //             "status='" + status + '\'' +
    //             '}';
    // }
}
