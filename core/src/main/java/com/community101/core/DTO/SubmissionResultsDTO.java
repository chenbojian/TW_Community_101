package com.community101.core.DTO;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by MiffyLiye on 23/07/2015.
 */
public class SubmissionResultsDTO {
    private long orderId;

    private List<String> errorMessages;

    public SubmissionResultsDTO() {
        errorMessages = new LinkedList<String>();
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }
}
