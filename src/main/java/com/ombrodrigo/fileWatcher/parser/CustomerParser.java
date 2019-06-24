package com.ombrodrigo.fileWatcher.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ombrodrigo.fileWatcher.domain.Customer;

public class CustomerParser {

    private final String EXTRACTION_PATTERN = "(002)ç([0-9]{16})ç([\\s\\S]+)ç([\\s\\S]+)";

    public Customer parse(String line) {
        return this.builderCustomer(this.getMatcher(line));
    }

    public Customer builderCustomer(Matcher matcher) {
        return new Customer(
            matcher.group(1),
            matcher.group(2),
            matcher.group(3),
            matcher.group(4)
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