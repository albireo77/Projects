package org.bucik.service;

import org.bucik.model.CalculationResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CalculationService {

    CalculationResponse calculate(MultipartFile file, String targetCurrency, String correction) throws IOException;
}
