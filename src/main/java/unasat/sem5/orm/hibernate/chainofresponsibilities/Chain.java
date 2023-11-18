package unasat.sem5.orm.hibernate.chainofresponsibilities;

public interface Chain {

    public void setNextChain(Chain nextChain);

    public void getReport(String request);
}
