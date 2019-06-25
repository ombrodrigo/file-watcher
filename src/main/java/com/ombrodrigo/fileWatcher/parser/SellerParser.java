package com.ombrodrigo.fileWatcher.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ombrodrigo.fileWatcher.domain.Seller;

import org.springframework.stereotype.Component;

@Component
public class SellerParser {

    private final String EXTRACTION_PATTERN = "(001)ç([0-9]{13})ç([\\s\\S]+)ç([0-9]*.?[0-9]+)";

    public Seller parse(String line) {
        return this.builderSeller(this.getMatcher(line));
    }

    public Seller builderSeller(Matcher matcher) {
        return new Seller(
            matcher.group(1),
            matcher.group(2),
            matcher.group(3),
            Double.parseDouble(matcher.group(4))
        );
    }

    public boolean isValidLine(String line) {
        return this.getMatcher(line).matches();
    }

    private Matcher getMatcher(String line) {
        Pattern pattern = Pattern.compile(EXTRACTION_PATTERN);
        Matcher matcher = pattern.matcher(line);
        matcher.find();
        return matcher;
    }
}