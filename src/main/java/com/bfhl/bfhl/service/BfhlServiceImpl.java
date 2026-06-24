package com.bfhl.bfhl.service;

import com.bfhl.bfhl.dto.BfhlRequest;
import com.bfhl.bfhl.dto.BfhlResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    // Replace with your actual details
    private static final String USER_ID = "vanshita_sahi_09122004";
    private static final String EMAIL = "vanshita2318.be23@chitkara.edu.in";
    private static final String ROLL_NUMBER = "2310992318";

    @Override
    public BfhlResponse processData(BfhlRequest request) {

        List<String> data = request.getData();

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialChars = new ArrayList<>();

        long sumValue = 0;

        StringBuilder allAlphaChars = new StringBuilder();

        for (String item : data) {

            // Number
            if (item.matches("-?\\d+")) {

                long num = Long.parseLong(item);

                if (Math.abs(num) % 2 == 0) {
                    evenNumbers.add(item);
                } else {
                    oddNumbers.add(item);
                }

                sumValue += num;
            }

            // Alphabet only
            else if (item.matches("[a-zA-Z]+")) {

                alphabets.add(item.toUpperCase());
                allAlphaChars.append(item);
            }

            // Mixed string
            else {

                for (char ch : item.toCharArray()) {

                    if (Character.isLetter(ch)) {

                        alphabets.add(
                                String.valueOf(Character.toUpperCase(ch))
                        );

                        allAlphaChars.append(ch);
                    }

                    else if (!Character.isDigit(ch)) {

                        specialChars.add(
                                String.valueOf(ch)
                        );
                    }
                }
            }
        }

        // Build concat_string

        String allLetters = allAlphaChars.toString();

        StringBuilder reversed =
                new StringBuilder(allLetters).reverse();

        StringBuilder concatBuilder =
                new StringBuilder();

        for (int i = 0; i < reversed.length(); i++) {

            char c = reversed.charAt(i);

            if (i % 2 == 0) {
                concatBuilder.append(
                        Character.toUpperCase(c)
                );
            } else {
                concatBuilder.append(
                        Character.toLowerCase(c)
                );
            }
        }

        // Response

        BfhlResponse response = new BfhlResponse();

        response.setSuccess(true);
        response.setUserId(USER_ID);
        response.setEmail(EMAIL);
        response.setRollNumber(ROLL_NUMBER);

        response.setOddNumbers(oddNumbers);
        response.setEvenNumbers(evenNumbers);
        response.setAlphabets(alphabets);
        response.setSpecialCharacters(specialChars);

        response.setSum(String.valueOf(sumValue));
        response.setConcatString(concatBuilder.toString());

        return response;
    }
}