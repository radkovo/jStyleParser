/**
 * FilesUtil.java
 *
 * Created on 1. 11. 2017, 14:48:09 by burgetr
 */
package test;

import java.util.Scanner;

/**
 * 
 * @author burgetr
 */
public class FilesUtil
{

    public static String readResource(String name) {
        Scanner scanner = new Scanner(FilesUtil.class.getResourceAsStream(name), "UTF-8");
        String text = scanner.useDelimiter("\\A").next();
        scanner.close();
        return text;
    }
    
}
