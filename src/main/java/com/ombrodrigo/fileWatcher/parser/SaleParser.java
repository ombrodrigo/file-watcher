package com.ombrodrigo.fileWatcher.parser;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ombrodrigo.fileWatcher.domain.Sale;
import com.ombrodrigo.fileWatcher.domain.SaleItem;

public class SaleParser {

    private final String EXTRACTION_PATTERN = "(003)ç([0-9]{2})ç(\\[.*\\]+)ç([\\s\\S]+)";

    public Sale parse(String line) {
        return this.builderSale(this.getMatcher(line));
    }

    public Sale builderSale(Matcher matcher) {
        return new Sale(
            matcher.group(1),
            matcher.group(2),
            this.builderItems(matcher.group(3)),
            matcher.group(4)
        );
    }

    public boolean isValidLine(String line) {
        return this.getMatcher(line).matches();
    }

    private List<SaleItem> builderItems(String items) {
        SaleItemParser saleItemParser = new SaleItemParser();
        return saleItemParser.parse(items);
    }

    private Matcher getMatcher(String line) {
        Pattern pattern = Pattern.compile(EXTRACTION_PATTERN);
        Matcher matcher = pattern.matcher(line);
        matcher.find();
        return matcher;
    }
}