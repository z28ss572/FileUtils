package com.sector572.utils.file;

import java.io.File;
import java.io.FilenameFilter;
import java.util.logging.Logger;

/**
 * This class can be used to find the newest file in a directory.
 *
 * @author Eddie N. (en@sector572.com)
 */
public class FileSearcher
{
    private static final Logger LOG = Logger.getLogger(
            FileSearcher.class.getName());

    public FileSearcher()
    {
    }

    /**
     * Searches the directory and returns the newest file's absolute path as a
     * String.
     *
     * @param directory - Represents the directory to search in.
     * @return Newest file
     */
    public File findNewest(File directory)
    {
        return findNewest(directory,
                          null);
    }
    
    /**
     * Searches the directory and returns the newest file's absolute path as a
     * String.
     *
     * @param directory - Represents the directory path to search in.
     * @return Newest file
     */
    public File findNewest(String directory)
    {
        return findNewest(directory,
                          null);
    }

    /**
     * Searches the directory and returns the newest file's absolute path as a
     * String.
     *
     * @param directory - Represents the directory to search in.
     * @return Absolute path of newest file as a String
     */
    public String findNewestAsString(File directory)
    {
        return findNewestAsString(directory,
                                  null);
    }

    /**
     * Searches the directory and returns the newest file's absolute path as a
     * String.
     *
     * @param directory - Represents the directory path to search in.
     * @return Absolute path of newest file as a String
     */
    public String findNewestAsString(String directory)
    {
        return findNewestAsString(directory,
                                  null);
    }

    /**
     * Searches the directory and returns the newest file's absolute path as a
     * String. If a filter is provided, the method will only identify newest
     * files which match the filter.
     *
     * @param directory - Represents the directory to search in.
     * @param filter - Represents a filename filter.
     * @return Absolute path of newest file as a String
     */
    public String findNewestAsString(File directory,
                                     FilenameFilter filter)
    {
        String rtn = null;

        File latest = findNewest(directory,
                                 filter);

        if(latest != null)
        {
            rtn = latest.getAbsolutePath();
        }

        return rtn;
    }

    /**
     * Searches the directory and returns the newest file's absolute path as a
     * String. If a filter is provided, the method will only identify newest
     * files which match the filter.
     *
     * @param directory - Represents the directory path to search in.
     * @param filter - Represents a filename filter.
     * @return Absolute path of newest file as a String
     */
    public String findNewestAsString(String directory,
                                     FilenameFilter filter)
    {
        String rtn = null;

        File latest = findNewest(directory,
                                 filter);

        if(latest != null)
        {
            rtn = latest.getAbsolutePath();
        }

        return rtn;
    }

    /**
     * Searches the directory and returns the newest file. If a filter is
     * provided, the method will only identify newest files which match the
     * filter.
     *
     * @param directory - Represents the directory path to search in.
     * @param filter - Represents a filename filter.
     * @return Newest file
     */
    public File findNewest(String directory,
                           FilenameFilter filter)
    {
        File rtn = null;

        File dir = new File(directory);

        rtn = findNewest(dir,
                         filter);

        return rtn;
    }

    /**
     * Searches the directory and returns the newest file. If a filter is
     * provided, the method will only identify newest files which match the
     * filter.
     *
     * @param directory - Represents the directory to search in.
     * @param filter - Represents a filename filter.
     * @return Newest file
     */
    public File findNewest(File directory,
                           FilenameFilter filter)
    {
        File rtn = null;

        if(directory != null && directory.isDirectory()
                && directory.canExecute() && directory.canRead())
        {
            File[] files = null;

            files = directory.listFiles();

            if(files != null)
            {
                for(int i = 0; i < files.length; i++)
                {
                    File tmp = files[i];

                    if(tmp.isDirectory())
                    {
                        File latest = findNewest(tmp,
                                                 filter);

                        if((latest != null)
                                && (rtn == null
                                    || latest.lastModified()
                                    > rtn.lastModified()))
                        {
                            rtn = latest;
                        }
                    }
                    else
                    {
                        if(rtn == null
                                || tmp.lastModified() > rtn.lastModified())
                        {
                            if(filter != null)
                            {
                                if(filter.accept(
                                        directory,
                                        tmp.getName()))
                                {
                                    rtn = tmp;
                                }
                            }
                            else
                            {
                                rtn = tmp;
                            }
                        }
                    }
                }
            }
        }

        return rtn;
    }
}
