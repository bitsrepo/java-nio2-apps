/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package filesearchapp;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 *
 * @author bitmathe
 */
public class FileSearchApp {

    static class Search extends SimpleFileVisitor {
       
        private Path searchFile;
        private boolean found;
        
        public Search(Path file) {
            searchFile=file;
        }

        public void searchFile(Path file) {
            Path fileName=file.getFileName();
            if(fileName !=null && fileName.equals(searchFile)) {
                found=true;
            }else {
                found=false;
            }
        }
        @Override
        public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
            searchFile((Path)file);
            if(!found)
                return FileVisitResult.CONTINUE;
            else 
            {
                System.out.println("The file found;" +((Path)file).toString());
                return FileVisitResult.TERMINATE;
            }
            
        }
        
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
       Path rootDirectory=Paths.get("D:\\Bittu\\Study\\JavaNIO.2");
       Search fileSearch=new Search(Paths.get("pro_java_7_nio.2.pdf"));
       DirectoryStream<Path> files=Files.newDirectoryStream(rootDirectory);
       System.out.println("Starting the search");
       for(Path path: files) {
        Files.walkFileTree(rootDirectory, fileSearch);   
       }
       System.out.println("Search Ends");
    }
    
}
