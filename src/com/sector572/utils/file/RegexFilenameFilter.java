package com.sector572.utils.file;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * An implementation of FilenameFilter which accepts a Pattern or Regex String
 * which will be used by the accept() method to test if a file name matches the
 * pattern or not.
 *
 * @author Eddie N. (en@sector572.com)
 */
public class RegexFilenameFilter implements FilenameFilter
{
    private Pattern pattern;

    /**
     * Initializes a RegexFilenameFilter with the pattern provided.
     * 
     * @param pattern
     */
    public RegexFilenameFilter(Pattern pattern)
    {
        this.pattern = pattern;
    }
    
    /**
     * Initializes the RegexFilenameFilter with the pattern provided as String.
     * 
     * @param pattern
     * @throws PatternSyntaxException 
     */
    public RegexFilenameFilter(String pattern)
            throws PatternSyntaxException
    {
        this.pattern = Pattern.compile(pattern);
    }

    /**
     * Set's the pattern for this RegexFilenameFilter.
     * 
     * @param pattern the pattern to set
     */
    public void setPattern(Pattern pattern)
    {
        this.pattern = pattern;
    }
    
    /**
     * Set's the pattern for this RegexFilenameFilter.
     * 
     * @param pattern the pattern to set
     */
    public void setPattern(String pattern)
            throws PatternSyntaxException
    {
        this.pattern = Pattern.compile(pattern);
    }

    /**
     * Tests the file passed against the pattern provided. Returns false if no
     * pattern was provided.
     * 
     * @param dir
     * @param name
     * @return 
     */
    @Override
    public boolean accept(File dir,
                          String name)
    {
        boolean rtn = false;

        if(pattern != null)
        {
            rtn = name.matches(pattern.pattern());
        }
        
        return rtn;
    }
}
