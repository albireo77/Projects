package org.bucik.service;

import org.bucik.model.RateRequest;
import java.math.BigDecimal;

public interface RateService {

    BigDecimal getRate(RateRequest rateRequest);
}
