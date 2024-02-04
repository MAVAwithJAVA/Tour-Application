package unasat.sem5.orm.hibernate.reportchain;

import unasat.sem5.orm.hibernate.reportresultset.ReportResult;

import java.util.List;

public interface Chain {

    void setNextChain(Chain nextChain);

    List<? extends ReportResult> getReport(ReportRequest request);
}
