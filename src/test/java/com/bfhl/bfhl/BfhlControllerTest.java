package com.bfhl.bfhl;

import com.bfhl.bfhl.controller.BfhlController;
import com.bfhl.bfhl.service.BfhlServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BfhlController.class)
@Import(BfhlServiceImpl.class)
class BfhlControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;

    // ── Example A ─────────────────────────────────────────────────────────────
    @Test
    void testExampleA() throws Exception {
        String body = "{\"data\":[\"a\",\"1\",\"334\",\"4\",\"R\",\"$\"]}";
        mockMvc.perform(post("/bfhl").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.is_success").value(true))
                .andExpect(jsonPath("$.odd_numbers[0]").value("1"))
                .andExpect(jsonPath("$.even_numbers").isArray())
                .andExpect(jsonPath("$.alphabets[0]").value("A"))
                .andExpect(jsonPath("$.special_characters[0]").value("$"))
                .andExpect(jsonPath("$.sum").value("339"))
                .andExpect(jsonPath("$.concat_string").value("Ra"));
    }

    // ── Example B ─────────────────────────────────────────────────────────────
    @Test
    void testExampleB() throws Exception {
        String body = "{\"data\":[\"2\",\"a\",\"y\",\"4\",\"&\",\"-\",\"*\",\"5\",\"92\",\"b\"]}";
        mockMvc.perform(post("/bfhl").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.is_success").value(true))
                .andExpect(jsonPath("$.sum").value("103"))
                .andExpect(jsonPath("$.concat_string").value("ByA"));
    }

    // ── Example C (multi-char strings) ────────────────────────────────────────
    @Test
    void testExampleC() throws Exception {
        String body = "{\"data\":[\"A\",\"ABCD\",\"DOE\"]}";
        mockMvc.perform(post("/bfhl").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sum").value("0"))
                .andExpect(jsonPath("$.concat_string").value("EoDdCbAa"));
    }

    // ── Empty data array ───────────────────────────────────────────────────────
    @Test
    void testEmptyData() throws Exception {
        String body = "{\"data\":[]}";
        mockMvc.perform(post("/bfhl").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.is_success").value(false));
    }
}