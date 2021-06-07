package com.rtm516.stackparser;


import lombok.Getter;
import lombok.ToString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@ToString
public class StackLine {
    public static Pattern LINE_PATTERN = Pattern.compile("((([\\d\\w]*\\.)*[\\d\\w]*)\\.)?([\\d\\w\\$]*)\\.([\\d\\w\\$<>]*)\\(([\\d\\w\\.\\s]*)(\\:(\\d*))?\\)");

    private String stackPackage;
    private String javaClass;
    private String method;
    private String source;
    private String line;

    public StackLine(Matcher matcher) {
        this.stackPackage = matcher.group(2);
        this.javaClass = matcher.group(4);
        this.method = matcher.group(5);
        this.source = matcher.group(6);
        this.line = matcher.group(8);
    }
}
