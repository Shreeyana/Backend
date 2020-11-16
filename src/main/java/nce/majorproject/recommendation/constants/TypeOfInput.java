package nce.majorproject.recommendation.constants;

public enum TypeOfInput {
    VIEW(0.15),
    CART(0.35),
    CHECKOUT(0.5);
    private Double val;
    public Double getVal() {
        return val;
    }

    public void setVal(Double val) {
        this.val = val;
    }

    TypeOfInput(Double val) {
        this.val = val;
    }
}
