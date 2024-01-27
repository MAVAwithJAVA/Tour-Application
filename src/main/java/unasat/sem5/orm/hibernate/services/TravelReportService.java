package unasat.sem5.orm.hibernate.services;

import java.util.List;

public class TravelReportService {

    public List<? extends ReportResult> getReport(ReportRequest request) {
        return adminReportHandler.startHandling(request);
    }

}
