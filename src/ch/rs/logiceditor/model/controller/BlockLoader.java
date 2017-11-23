/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.model.controller;

import ch.rs.logiceditor.model.master.LogicBlock;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *
 * @author Ricardo
 */
public class BlockLoader {

    private String path = "C:\\Users\\Ricardo\\Documents\\GitHub\\SC_PGI\\blocks";

    public BlockLoader() {

    }

    public List<Class<? extends LogicBlock>> getBlocks() throws IOException {
        List<Class<? extends LogicBlock>> list = new LinkedList<>();
        Path directory = Paths.get(path);

        Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (!file.toString().endsWith(".jar") || !Files.isRegularFile(file)) {
                    return FileVisitResult.CONTINUE;
                }
//
//                try {
                String className = directory.relativize(file).toString();
                String name = className.substring(0, className.length() - ".jar".length());
//                    Class<? extends LogicBlock> loadedClass = (Class<? extends LogicBlock>) folderClassLoader.loadClass(name);
////                    System.out.println("adding class");
//                            
//                    list.add(loadedClass);

                ZipInputStream zip = new ZipInputStream(new FileInputStream(path + "\\" + className));
                for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
                    if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                        // This ZipEntry represents a class. Now, what class does it represent?
                        String insideClassName = entry.getName().replace('/', '.'); // including ".class"
                        Class<? extends LogicBlock> loadedClass = null;
                        insideClassName = insideClassName.replace(".class", "");
                        Path directory = Paths.get(path + "\\" + className);
                        URLClassLoader folderClassLoader = new URLClassLoader(new URL[]{directory.toAbsolutePath().toUri().toURL()});
                        try {
                            loadedClass = (Class<? extends LogicBlock>) folderClassLoader.loadClass(insideClassName);
                        } catch (ClassNotFoundException ex) {
                            System.err.println("Error " + ex.getMessage());
                        }
                        list.add(loadedClass);
                        
                        /*
                         
                        Yea
                        Or rather
                        class.newInstance
                        so e.g. Class<?> clazz = loader.forName("And");  Object instance = clazz.newInstance()
                        You can do the latter part as often as you'd like
                        it is equivalent to Object instance = new And();

                         */

                    }
                }

//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
                return FileVisitResult.CONTINUE;
            }
        });
        return list;
    }

}
