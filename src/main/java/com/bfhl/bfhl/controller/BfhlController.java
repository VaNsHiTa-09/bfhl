package com.bfhl.bfhl.controller;

import com.bfhl.bfhl.dto.BfhlRequest;
import com.bfhl.bfhl.dto.BfhlResponse;
import com.bfhl.bfhl.service.BfhlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/bfhl")
public class BfhlController {

    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }
    @PostMapping
    public ResponseEntity<?> processData(@RequestBody BfhlRequest request) {

        if (request.getData() == null || request.getData().isEmpty()) {

            return ResponseEntity.badRequest().body(
                    Map.of(
                            "is_success", false,
                            "message", "Data array cannot be empty"
                    )
            );
        }

        BfhlResponse response = bfhlService.processData(request);

        return ResponseEntity.ok(response);
    }

}