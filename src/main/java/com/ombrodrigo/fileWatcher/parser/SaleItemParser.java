package com.ombrodrigo.fileWatcher.parser;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.ombrodrigo.fileWatcher.domain.SaleItem;

public class SaleItemParser {

    private final String EXTRACTION_PATTERN_ITEMS = "\\[(.*)\\]";
    private final String EXTRACTION_PATTERN_ITEM = "([0-9]+)-([0-9]+)-([0-9]*.?[0-9]+)";
    
    public List<SaleItem> parse(String line) {
        Matcher matcher = this.getMatcher(line, EXTRACTION_PATTERN_ITEMS);

        return Arrays.stream(matcher.group(1).split(","))
            .map(item -> {
                return this.builderSaleItem(this.getMatcher(item, EXTRACTION_PATTERN_ITEM));
            })
            .collect(Collectors.toList());
    }

    public SaleItem builderSaleItem(Matcher matcher) {
        return new SaleItem(
            matcher.group(1),
            matcher.group(2),
            Double.parseDouble(matcher.group(3))
        );
    }

    public boolean isValidLine(String line, String extractionPattern) {
        return this.getMatcher(line, extractionPattern).matches();
    }

    private Matcher getMatcher(String line, String extractionPattern) {
        Pattern pattern = Pattern.compile(extractionPattern);
        Matcher matcher = pattern.matcher(line);
        matcher.find();
        return matcher;
    }
}