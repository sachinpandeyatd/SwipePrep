package com.swipeprep.appbackend.dto;

import com.swipeprep.appbackend.model.ReportReason;
import com.swipeprep.appbackend.model.VoteType;
import lombok.Data;

@Data
public class FeedbackRequest {
	private VoteType vote;
	private ReportReason report;
	private String reportDetails;
}
