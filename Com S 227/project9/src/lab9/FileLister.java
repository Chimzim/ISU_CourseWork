package lab9;

import java.io.File;

public class FileLister
{
  public static void main(String[] args)
  {
    // Choose the directory you want to list.
    // If running in Eclipse, "." will just be the current project directory.
    // Use ".." to list the whole workspace directory, or enter a path to
    // some other directory.
    File rootDirectory = new File("U:\\cs227\\workspace\\project9\\src\\lab9");

    System.out.println(countfiles(rootDirectory));
    System.out.println(countPatterns(12));
  }
  
  /**
   * Print the names of all items in the hierarchy located under 
   * a given directory. If the given File object is not a directory, 
   * just prints the file's name.
   */
  public static int countPatterns(int n) {
	  if(n < 0) {
		  return 0;
	  }
	  if(n == 0) {
		  return 1;
	  }
	  return countPatterns(n-1)+countPatterns(n-3);
  }
  public static void listAllFiles(File f)
  {
    if (!f.isDirectory())
    {
      // Base case: f is a file, so just print its name
      System.out.println(f.getName());
    }
    else
    {
      // Recursive case: f is a directory, so go through the 
      // files and directories it contains, and recursively call
      // this method on each one
      System.out.println("+ " + f.getName());
      File[] files = f.listFiles();
      for (int i = 0; i < files.length; ++i)
      {
        listAllFiles(files[i]);
      }
    }
  }
  public static int countfiles(File f) {
	  int sum = 0;
	  if(f.isDirectory()) {
		  File [] files = f.listFiles();
		  for(int i = 0; i < files.length; i++) {
			 sum += countfiles(files[i]);
		  }
	  }
	  else {
		  return 1;
	  }
	return sum;
	  
  }
}