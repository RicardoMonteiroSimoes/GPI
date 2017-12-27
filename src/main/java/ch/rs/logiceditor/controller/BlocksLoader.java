package ch.rs.logiceditor.controller;

import ch.rs.logiceditor.controller.interfaces.LoadingScreenInterface;
import ch.rs.logiceditor.model.master.LogicBlock;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class BlocksLoader {


    public List<Class<? extends LogicBlock>> loadBlocks(String path, LoadingScreenInterface loadingScreen) throws Exception {
        List<Class<? extends LogicBlock>> list = new LinkedList<>();
        loadingScreen.setLoadingText("Looking for Blocks...");
        Path directory = Paths.get(path);

        Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (!file.toString().endsWith(".jar") || !Files.isRegularFile(file)) {
                    return FileVisitResult.CONTINUE;
                }
                String className = directory.relativize(file).toString();
                String name = className.substring(0, className.length() - ".jar".length());
                loadingScreen.setLoadingText("Found " + className + "...");

                ZipInputStream zip = new ZipInputStream(new FileInputStream(path + "\\" + className));
                for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
                    if (!entry.isDirectory() && entry.getName().endsWith(".class")) {

                        String insideClassName = entry.getName().replace('/', '.'); // including ".class"
                        Class<? extends LogicBlock> loadedClass = null;
                        insideClassName = insideClassName.replace(".class", "");
                        Path directory = Paths.get(path + "\\" + className);
                        URLClassLoader folderClassLoader = new URLClassLoader(new URL[]{directory.toAbsolutePath().toUri().toURL()});
                        try {
                            loadedClass = (Class<? extends LogicBlock>) folderClassLoader.loadClass(insideClassName);
                        } catch (Exception ex) {
                            System.err.println("Error " + ex.getMessage());
                        }
                        list.add(loadedClass);

                        /*

                        Yea
                        Or rather
                        class.newInstance
                        so e.g. Class<?> clazz = controller.forName("And");  Object instance = clazz.newInstance()
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
