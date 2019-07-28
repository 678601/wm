package com.example.demo.load.two;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarClassLoader extends ClassLoader {
	
	//jar包位置
    private String jarPath;
    
    public JarClassLoader(String jarPath) {
        
        this.jarPath = jarPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte [] classData=getData(name);
            
            if(classData==null){}
            
            else{
                //defineClass方法将字节码转化为类
                return defineClass(name,classData,0,classData.length);
            }
            
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        
        return super.findClass(name);
    }
    //返回类的字节码
    private byte[] getData(String className) throws IOException{
        InputStream in = null;
        ByteArrayOutputStream out = null;
        String path=jarPath + File.separatorChar +
                    className.replace('.',File.separatorChar)+".class";
        try {
            in=new FileInputStream(path);
            out=new ByteArrayOutputStream();
            byte[] buffer=new byte[2048];
            int len=0;
            while((len=in.read(buffer))!=-1){
                out.write(buffer,0,len);
            }
            return out.toByteArray();
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally{
            in.close();
            out.close();
        }
        return null;
    }
    /**
     * 
     * @Title: addJar   
     * @Description: 通过jar的位置获得jar文件   
     * @param: @param jarPath
     * @param: @throws IOException      
     * @return: void     
     * @author: LiWenming 
     * @Date:   2019年2月21日 上午9:04:49 
     * @throws
     */
    public void addJar(String jarPath) throws IOException{
        File file = new File(jarPath);
        if(file.exists()){
            JarFile jar = new JarFile(file);
            readJAR(jar);
        }
    }
    /**
     * 
     * @Title: readJAR   
     * @Description: 获得jar中的class  
     * @param: @param jar
     * @param: @throws IOException      
     * @return: void     
     * @author: LiWenming 
     * @Date:   2019年2月21日 上午9:04:18 
     * @throws
     */
    private void readJAR(JarFile jar) throws IOException{
        Enumeration<JarEntry> en = jar.entries();
        while (en.hasMoreElements()){
            JarEntry je = en.nextElement();
            String name = je.getName();
            if (name.endsWith(".class")){
                String clss = name.replace(".class", "").replaceAll("/", ".");
                if(this.findLoadedClass(clss) != null) continue;
                
                InputStream input = jar.getInputStream(je);
                ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
                int bufferSize = 4096; 
                byte[] buffer = new byte[bufferSize]; 
                int bytesNumRead = 0; 
                while ((bytesNumRead = input.read(buffer)) != -1) { 
                    baos.write(buffer, 0, bytesNumRead); 
                }
                byte[] cc = baos.toByteArray();
                input.close();
//                map.put(clss, cc);//暂时保存下来
            }
        }
    }
}
