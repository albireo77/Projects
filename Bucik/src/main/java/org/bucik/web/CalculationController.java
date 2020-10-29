package org.bucik.web;

import org.bucik.model.CalculationResponse;
import org.bucik.service.CalculationService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Set;

@Controller
public class CalculationController {

    private static final Set<String> ACCEPTABLE_MIME_TYPES = Set.of("application/vnd.ms-excel", "text/plain", "text/csv");

    private final CalculationService calculationService;

    public CalculationController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file,
                         @RequestParam("targetCurrency") String targetCurrency,
                         @RequestParam("rateCorrection") String rateCorrection,
                         RedirectAttributes redirectAttributes) throws IOException {

        String message;
        if (file.isEmpty()) {
            message = "Brak pliku";
        } else if (!isAcceptable(file)) {
            message = "Nieakceptowalny format pliku";
        } else {
            CalculationResponse response = calculationService.calculate(file, targetCurrency, rateCorrection);
            redirectAttributes.addFlashAttribute("response", response.toString());
            message = "Plik '" + file.getOriginalFilename() + "' został poprawnie przetworzony";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:uploadStatus";
    }

    private boolean isAcceptable(MultipartFile file) {
        return ACCEPTABLE_MIME_TYPES.contains(file.getContentType());
    }


}
