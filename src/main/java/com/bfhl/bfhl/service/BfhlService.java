package com.bfhl.bfhl.service;

import com.bfhl.bfhl.dto.BfhlRequest;
import com.bfhl.bfhl.dto.BfhlResponse;

public interface BfhlService {
    BfhlResponse processData(BfhlRequest request);
}